package com.geoalarms.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverlay extends Overlay {
	GeoPoint point = null;

	public MapOverlay(GeoPoint p) {
		this.point = p;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Projection projection = mapView.getProjection();

		if (shadow == false) {
			Point centro = new Point();
			projection.toPixels(this.point, centro);

			// Definimos el pincel de dibujo
			Paint p = new Paint();
			p.setColor(Color.BLUE);

			// Marca Ejemplo 1: Círculo y Texto
			canvas.drawCircle(centro.x, centro.y, 5, p);
			canvas.drawText("lat:" + (int) point.getLatitudeE6() + "long" + point.getLongitudeE6(), centro.x + 10, centro.y + 5, p);

			// Marca Ejemplo 2: Bitmap
			// Bitmap bm = BitmapFactory.decodeResource(
			// mapView.getResources(),
			// R.drawable.marcador_google_maps);

			// canvas.drawBitmap(bm, centro.x,
			// centro.y - bm.getHeight(), p);
		}
	}

//	@Override
//	public boolean onTap(GeoPoint geoPoint, MapView mapView) {
////		this.draw(new Canvas(), mapView, false);
////		Projection projection = mapView.getProjection();
////		Point centro = new Point();
////		projection.toPixels(geoPoint, centro);
////		Paint p = new Paint();
////		p.setColor(Color.RED);
////		
////		Canvas canv = new Canvas();
////		canv.drawCircle(centro.x, centro.y, 5, p);
////		canv.drawText("%f,%f",geoPoint.getLatitudeE6(),geoPoint.getLongitudeE6(), centro.x + 10, centro.y + 5, p);
//		
//		
////		Context context = getApplicationContext();
////		Toast.makeText(context, "Tapped", 10);
//		return true;
//	}
}
