package dk.rf.yesterday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class RFYesterdayQuestionaryActivity extends Activity {
	
	public static final String LOG_TAG = "RFYesterday";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.d(LOG_TAG, "onCreate called ...");
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionary);

        // Question 1: Area
        Spinner area = (Spinner) findViewById(R.id.area);
        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area.setAdapter(areaAdapter);
        
        // Question 3: Alcohol
        SeekBar alcohol = (SeekBar) this.findViewById(R.id.alcohol);
        updateAlchoholHint(alcohol);
        alcohol.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar sb) {}
			
			public void onStartTrackingTouch(SeekBar sb) {}
			
			public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
				updateAlchoholHint(sb);
			}
		});
    }
    
    public void updateAlchoholHint(SeekBar seekBar) {
    	TextView hint = (TextView) findViewById(R.id.alcohol_hint);
		hint.setText(seekBar.getProgress()+" units of alchohol.");
    }
    
    public void submit(View v) {
    	// TODO: Move this to the else case of the if
    	Intent myIntent = new Intent(this, RFYesterdayMapActivity.class);
    	this.startActivity(myIntent);

        Spinner area = (Spinner) findViewById(R.id.area);
        if(area.getSelectedItemPosition() == 0) {
        	Toast.makeText(this, R.string.pease_select_something, Toast.LENGTH_LONG).show();
        } else {
        	Gson gson = new Gson();
        	QuestionaryResponse response = new QuestionaryResponse();
        	response.read(v.getRootView(), R.id.area, R.id.fun, R.id.alcohol);
        	String json = gson.toJson(response);
        	Log.d(LOG_TAG, "json = "+json);
        }
    }
}