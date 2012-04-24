package com.geoalarms.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class AlarmEditor extends MapActivity {

	private MapView mapView = null;
	private List<Overlay> layers = null;
	private Spinner radioSpinner = null;
	private TextView alarmName = null;
	private TextView alarmDescription = null;
	private int[] numericalItems = null;
	private GeoPoint point = null;

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
        numericalItems = new int[] {1, 10, 20, 30, 40, 50};
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
		this.point = mapView.getMapCenter();
		PointOverlay om = new PointOverlay(this.point);
		mapView.invalidate();
		layers.add(om);
	}
	
	public void onSubmit(View v){
		if (this.point != null && !alarmName.getText().toString().equals("")){
            Intent in = new Intent();

            // radius
            int pos = radioSpinner.getSelectedItemPosition();
            int radius = numericalItems[pos];

            // coordinates
            GeoPoint center = mapView.getMapCenter();
            // TODO: convert this in custom `MapView` class?
            Coordinates coords = new Coordinates(center);

            // name and description
            String name = alarmName.getText().toString();
            String description = alarmDescription.getText().toString();

            // send data back
            in.putExtra("radius", radius);
            in.putExtra("latitude", coords.latitude);
            in.putExtra("longitude", coords.longitude);
            in.putExtra("name", name);
            in.putExtra("description", description);
            this.setResult(RESULT_OK, in);
            finish();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(), "Pin a point and Introduce name", Toast.LENGTH_SHORT);
			toast.show();
		}
	}
}
