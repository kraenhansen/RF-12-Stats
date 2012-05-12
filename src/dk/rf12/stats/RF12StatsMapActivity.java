package dk.rf12.stats;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

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