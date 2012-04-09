package com.bandoleros.geoalarms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
	/** Called when the activity is first created. */
	private final int MAPACTIVITY = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
	}

	public void toMap(View v) {
		Intent intent = new Intent(HomeActivity.this,MapActivityView.class);
		this.startActivityForResult(intent, MAPACTIVITY);
	}
}
