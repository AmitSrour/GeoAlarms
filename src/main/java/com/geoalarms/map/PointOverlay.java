package com.geoalarms.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PointOverlay extends Overlay {
	GeoPoint point = null;

	public PointOverlay(GeoPoint p) {
		this.point = p;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Projection projection = mapView.getProjection();

		if (shadow == false) {
			// get point
			Point center = new Point();
			projection.toPixels(this.point, center);

			// paint 
			Paint p = new Paint();
			p.setColor(Color.BLUE);

			canvas.drawCircle(center.x, center.y, 5, p);
			//canvas.drawText("lat:" + (int) point.getLatitudeE6() + "long" + point.getLongitudeE6(), center.x + 10, center.y + 5, p);
		}
	}
}
