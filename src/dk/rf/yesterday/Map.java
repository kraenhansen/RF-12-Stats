package dk.rf.yesterday;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;

public class Map extends View {
	protected List<Area> areas = new LinkedList<Area>();

	public Map(Context context) {
		super(context);
		/*
		areas.add(new Area(this, "C", new PointF(0.1f, 0.3f), Color.BLACK,
			new PointF(0.1f-0.03f, 0.3f-0.03f),
			new PointF(0.1f+0.03f, 0.3f-0.03f),
			new PointF(0.1f+0.03f, 0.3f+0.03f),
			new PointF(0.1f-0.03f, 0.3f+0.03f)
		));
		areas.add(new Area(this, "E", new PointF(0.25f, 0.6f), Color.BLACK,
			new PointF(0.25f-0.03f, 0.6f-0.03f),
			new PointF(0.25f+0.03f, 0.6f-0.03f),
			new PointF(0.25f+0.03f, 0.6f+0.03f),
			new PointF(0.25f-0.03f, 0.6f+0.03f)
		));
		*/
	}
	
	public static Map load(Context context) {
		Map result = new Map(context);
		
		InputStreamReader isr = new InputStreamReader(context.getResources().openRawResource(R.raw.map));
		Gson gson = new Gson();
		StringMap<?> root = gson.fromJson(isr, StringMap.class);
		if(root != null) {
			Object areasO = ((StringMap<?>) root).get("areas");
			if(areasO != null && areasO instanceof List<?>) {
				List<?> areas = (List<?>) areasO;
				for(Object area: areas) {
					if(area instanceof StringMap<?>) {
						Area a = new Area(result, (StringMap<?>) area);
						result.areas.add(a);
					}
				}
			}
		}
		return result;
	}
	
	public int getAreaPolygonColor() {
		/*
		int c = Color.HSVToColor(float[]{0.0f, 1.0f, 0.5f});
		return ;
		*/
		return 0;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		Drawable drawable = getResources().getDrawable(R.drawable.map);
		Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
		
		canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, this.getWidth(), this.getHeight()), null);
		
		drawAreas(canvas);
	}
	
	public void drawAreas(Canvas canvas) {
		for(Area a: areas) {
			Log.d(QuestionaryActivity.LOG_TAG, "Drawing "+a);
			a.draw(canvas);
		}
	}

}
