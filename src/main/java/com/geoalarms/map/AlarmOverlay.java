package com.geoalarms.map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

import com.geoalarms.R;
import com.geoalarms.GeoAlarms;
import com.geoalarms.model.Alarm;
import com.geoalarms.model.Coordinates;


public class AlarmOverlay extends Overlay {

	public Alarm alarm;
	private Bitmap alarmIcon;

	public AlarmOverlay(Alarm alarm) {
		this.alarm = alarm;

        // icon
        Context context = GeoAlarms.context;
        Resources res = context.getResources();
        this.alarmIcon = BitmapFactory.decodeResource(res, R.drawable.app_icon);
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		if (shadow == false) {
			// get point
            Projection projection = mapView.getProjection();
			Point center = new Point();

			// coordinates to convert
			Coordinates coords = this.alarm.coordinates;
			GeoPoint point = coords.toGeoPoint();

			// put in `center` the coordinates in pixels
			projection.toPixels(point, center);
			
			// draw radius
			float radius = projection.metersToEquatorPixels((float) this.alarm.radius);
			Paint circlePaint = new Paint();
			circlePaint.setColor(0x30000000);
			circlePaint.setStyle(Style.FILL_AND_STROKE);
			// TODO: draw the circle calculating `radius`
			canvas.drawCircle(center.x, 
			                  center.y, 
			                  (int) radius, 
			                  circlePaint);
			
			// draw alarm name
			Paint textPaint = new Paint();
			textPaint.setColor(Color.RED);
			canvas.drawText(alarm.name, center.x, center.y, textPaint);

			// draw map pin
		    canvas.drawBitmap(this.alarmIcon, 
		                      center.x - alarmIcon.getWidth() / 2, 
		                      center.y - alarmIcon.getHeight(), 
		                      null);
		
			super.draw(canvas, mapView, shadow);
		}
	}
}
