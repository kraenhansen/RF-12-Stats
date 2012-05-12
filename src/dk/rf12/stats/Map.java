package dk.rf12.stats;

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

public class Map extends View {
	
	Paint p = new Paint();
	List<Area> areas = new LinkedList<Area>();

	public Map(Context context) {
		super(context);
		areas.add(new Area(this, "C", 0.1f, 0.3f));
		areas.add(new Area(this, "E", 0.25f, 0.6f));
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		Drawable drawable = getResources().getDrawable(R.drawable.map);
		Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
		
		Log.d(RF12StatsActivity.LOG_TAG, "Canvas rect = "+canvas.getClipBounds().toString());
		
		//canvas.drawRect(canvas.getClipBounds(), p);
		canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, this.getWidth(), this.getHeight()), p);
		
		drawAreas(canvas);
	}
	
	public void drawAreas(Canvas canvas) {
		for(Area a: areas) {
			a.draw(canvas);
		}
	}

}
