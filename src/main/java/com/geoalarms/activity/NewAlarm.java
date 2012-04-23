package com.geoalarms.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.geoalarms.R;
import com.geoalarms.model.MapOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class NewAlarm extends MapActivity {

	private MapView mapView = null;
	private List<Overlay> layers = null;
	private Spinner radioSpinner = null;
	private TextView alarmName = null;
	private TextView alarmDescription = null;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newalarm);
        
        mapView = (MapView) this.findViewById(R.id.mapView);
        layers = mapView.getOverlays();        
        
        radioSpinner = (Spinner) this.findViewById(R.id.spinner1);
        alarmName = (TextView) this.findViewById(R.id.nameAlarmTextView);
        alarmDescription = (TextView) this.findViewById(R.id.descriptionAlarmTextView);
        
        String[] items = new String[] {"1", "10", "20", "30", "40", "50"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        radioSpinner.setAdapter(adapter);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void markCenter(View v){
		MapOverlay om = new MapOverlay(mapView.getMapCenter());
		mapView.invalidate();
		layers.add(om);
	}
	
	public void onSubmit(View v){
		Intent in = new Intent();
		String radio = radioSpinner.getSelectedItem().toString();
		String name = alarmName.getText().toString();
		String description = alarmDescription.getText().toString();
		in.putExtra("radio", radio);
		in.putExtra("name", name);
		in.putExtra("description", description);
		this.setResult(RESULT_OK, in);
		finish();
	}
}
