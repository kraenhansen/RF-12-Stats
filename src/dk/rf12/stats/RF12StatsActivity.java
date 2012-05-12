package dk.rf12.stats;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class RF12StatsActivity extends Activity {
	
	public static final String LOG_TAG = "RF12Stats";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionary);
        
        Spinner question0 = (Spinner) findViewById(R.id.question_0_value);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.areas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        question0.setAdapter(adapter);
        
        SeekBar question2 = (SeekBar) this.findViewById(R.id.question_2_value);
        
        updateQuestion2(0);
        
        question2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar sb) {}
			
			public void onStartTrackingTouch(SeekBar sb) {}
			
			public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
				if(fromUser) {
					updateQuestion2(progress);
				}
			}
		});
    }
    
    public void updateQuestion2(int progress) {
    	TextView hint = (TextView) findViewById(R.id.question_2_hint);
		hint.setText(progress+" units of alchohol.");
    }
    
    public void submit(View v) {
        Spinner question0 = (Spinner) findViewById(R.id.question_0_value);
        RatingBar question1 = (RatingBar) findViewById(R.id.question_1_value);
        SeekBar question2 = (SeekBar) findViewById(R.id.question_2_value);
        
        if(question0.getSelectedItemPosition() == 0) {
        	Toast.makeText(this, R.string.pease_select_something, Toast.LENGTH_LONG).show();
        } else {
	        String area = (String) question0.getSelectedItem();
	        Log.d(LOG_TAG, "Submitting area = "+area+", fun = "+question1.getProgress()+", alc = "+question2.getProgress());
        }
    }
}