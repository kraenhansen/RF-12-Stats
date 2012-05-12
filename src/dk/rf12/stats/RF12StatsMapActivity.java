package dk.rf12.stats;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class RF12StatsMapActivity extends Activity {
	
	public static final String LOG_TAG = "RF12Stats";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.setContentView(R.layout.map);
    	
    	LinearLayout mapView = (LinearLayout) findViewById(R.id.map_view);
    	
    	Map map = new Map(this);
    	mapView.addView(map);
    }
    
}