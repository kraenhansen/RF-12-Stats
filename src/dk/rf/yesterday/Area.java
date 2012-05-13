package dk.rf.yesterday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import com.google.gson.internal.StringMap;

public class Area {
	@Override
	public String toString() {
		return "Area [label=" + label + ", labelPosition=" + labelPosition.x + ","+labelPosition.y+"]";
	}

	private String label;
	private PointF labelPosition;
	private Map map;
	private Paint backgroundPaint;
	private Paint textPaint;
	private List<PointF> polygon;
	private int color = Color.RED;
	
	public Area(Map map, String label, PointF labelPosition, int color, PointF ... areaPolygonPoints) {
		this(map, label, labelPosition, color, Arrays.asList(areaPolygonPoints));
	}
	
	public Area(Map map, String label, PointF labelPosition, int color, List<PointF> polygon) {
		setColors();
		this.map = map;
		this.label = label;
		this.labelPosition = labelPosition;
		this.polygon = polygon;
	}

	public Area(Map map, StringMap<?> area) {
		setColors();
		this.map = map;
		Object label = area.get("label");
		if(label != null && label instanceof String) {
			this.label = (String) label;
		} else {
			Log.e(QuestionaryActivity.LOG_TAG, "Map format corrupted, missing a String label.");
		}
		
		Object labelPosition = area.get("labelPosition");
		if(labelPosition != null && labelPosition instanceof ArrayList) {
			this.labelPosition = getPointF((ArrayList<?>) labelPosition);
			if(this.labelPosition == null) {
				throw new RuntimeException("Error reading a labelPosition coord.");
			}
		} else {
			Log.e(QuestionaryActivity.LOG_TAG, "Map format corrupted, missing a labelPosition.");
		}
		
		Object polygon = area.get("polygon");
		if(polygon != null && polygon instanceof ArrayList) {
			List<PointF> polygonPoints = new LinkedList<PointF>();
			for(Object pointO: (ArrayList<?>) polygon) {
				if(pointO != null && pointO instanceof ArrayList) {
					PointF point = getPointF((ArrayList<?>)pointO);
					if(point != null) {
						polygonPoints.add(point);
					} else {
						throw new RuntimeException("Error reading a polygon coord.");
					}
				} else {
					throw new RuntimeException("Error reading a polygon coord.");
				}
			}
			//Log.d(RFYesterdayQuestionaryActivity.LOG_TAG, "Read a polygon on size = "+polygonPoints.size());
			this.polygon = polygonPoints;
		} else {
			Log.e(QuestionaryActivity.LOG_TAG, "Map format corrupted, missing a polygon.");
		}
	}
	
	protected void setColors() {
		backgroundPaint = new Paint();
		backgroundPaint.setStyle(Paint.Style.FILL);
		
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
	}
	
	public static PointF getPointF(ArrayList<?> array) {
		if(array.size() == 2) {
			if(array.get(0) instanceof Double && array.get(1) instanceof Double) {
				return new PointF(new Float((Double)array.get(0)), new Float((Double)array.get(1)));
			} else if(array.get(0) instanceof Float && array.get(1) instanceof Float) {
				return new PointF((Float)array.get(0), (Float)array.get(1));
			} else {
				throw new RuntimeException("The array argument must have size 2.");
			}
		} else {
			throw new RuntimeException("The array argument must have size 2.");
		}
	}

	public void draw(Canvas canvas) {

		int width = map.getWidth();
		int height = map.getHeight();
		
		String text = label;

		Path area = new Path();
		//canvas.drawCircle(x*map.getWidth(), y*map.getHeight(), 10, backgroundPaint);
		boolean first = true;
		for(PointF p: polygon) {
			if(first) {
				area.moveTo(p.x*width, p.y*height);
				first = false;
			} else {
				area.lineTo(p.x*width, p.y*height);
			}
		}
		backgroundPaint.setColor(color);
		canvas.drawPath(area, backgroundPaint);
		
		Rect bounds = new Rect();
		textPaint.getTextBounds(text, 0, text.length(), bounds);
		
		canvas.drawText(text, labelPosition.x*width-bounds.width()/2, labelPosition.y*height+bounds.height()/2, textPaint);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
