package dk.rf.yesterday;

import java.util.List;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;

public class QuestionaryResponse extends java.util.HashMap<String, Object> {
	
	private static final long serialVersionUID = 3454243141653908166L;
	
	List<Integer> fieldsIds;
	
	public QuestionaryResponse() {
		super();
	}
	
	public void read(View parentView, int ... fieldIds) {
		this.clear();
		for(int id: fieldIds) {
			String key = parentView.getResources().getResourceEntryName(id);
			Object value = getValue(parentView.findViewById(id));
			Log.d(QuestionaryActivity.LOG_TAG, "Adding "+key+" = "+value);
			put(key, value);
		}
	}
	
	public static Object getValue(View v) {
		if(v instanceof Spinner) {
			return ((Spinner)v).getSelectedItem();
		} if(ProgressBar.class.isAssignableFrom(v.getClass())) {
			return ((ProgressBar)v).getProgress();
		} else {
			throw new RuntimeException("It is not implementet how to get the value of a view of a "+v.getClass().toString());
		}
	}

}
