package com.aroc.study;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class arrayAdapter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendmessage);
		
		ListView lView=(ListView)findViewById(R.id.lv);
		lView.setAdapter(new ArrayAdapter<T>(context, resource, objects))
	}

}
