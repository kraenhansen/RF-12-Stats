package dk.rf.yesterday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RFYesterdayMapActivity extends Activity {
	
	public static final String LOG_TAG = "RF12Stats";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.setContentView(R.layout.map);
    	
    	LinearLayout mapView = (LinearLayout) findViewById(R.id.map_view);
    	
    	Map map = Map.load(this);
    	mapView.addView(map);
    }
    
    public void IGotFriends(View v) {
    	Intent i = new Intent(this, RFYesterdayQuestionaryActivity.class);
    	i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
    	Toast.makeText(this, R.string.i_got_friends_message, Toast.LENGTH_LONG).show();
    	this.startActivity(i);
    }
    
}