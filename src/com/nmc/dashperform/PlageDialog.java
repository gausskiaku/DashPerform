package com.nmc.dashperform;

import java.util.Calendar;
import java.util.zip.Inflater;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

public class PlageDialog extends DialogFragment {
	Button bt_Begin, bt_End;
	private int mYear, mMonth, mDay, mHour, mMinute, mSecond;

@Override
@Nullable
public View onCreateView(LayoutInflater inflater,
		@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	final View v = inflater.inflate(R.layout.plage, container, false);
	
	bt_Begin = (Button) v.findViewById(R.id.bt_Begin);
	bt_End = (Button) v.findViewById(R.id.bt_End);
	
	bt_Begin.setOnClickListener( new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					bt_Begin.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
				}
            	
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
			
		}
	});
	
	
	
	return v;
	
}

}
