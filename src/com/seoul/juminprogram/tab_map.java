package com.seoul.juminprogram;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPOIItem.CalloutBalloonButtonType;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class tab_map extends FragmentActivity implements MapView.CurrentLocationEventListener, MapView.POIItemEventListener, MapView.MapViewEventListener {

private static final String LOG_TAG = "Map";
private final int MENU_LOCATION = Menu.FIRST;

public static tab_map map_context;

private MapView mMapView;

private boolean isUsingCustomLocationMarker = false;

Button gps;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);

setContentView(R.layout.tab_mapview);



map_context = this;

callrefresh();


}

public void callrefresh() {
// TODO Auto-generated method stub
mMapView = (MapView) findViewById(R.id.map_view);
mMapView.setDaumMapApiKey("0dfdb9ad7961971d03529f4fcefb4b5f"); // 내 api
																// key
																// 입력
mMapView.setCurrentLocationEventListener(this); // 현재 위치 이벤트 리스너
mMapView.setPOIItemEventListener(this);
// ////////////////////////////////마커
String[] arrPlaceCode = getResources()
		.getStringArray(R.array.placeCode);

for (int i = 0; i < arrPlaceCode.length; i++) {

	// parse
	String[] item = arrPlaceCode[i].split(",");

	// make point
	MapPOIItem marker = new MapPOIItem();
	marker.setItemName(item[0]);

	marker.setTag(i);

	// 위도
	String Slatitude = item[1];
	double latitude = Double.parseDouble(Slatitude);

	// 경도
	String Slongitude = item[2];
	double longitude = Double.parseDouble(Slongitude);

	MapPoint DEFAULT_MARKER_POINT = MapPoint.mapPointWithGeoCoord(
			latitude, longitude);
	marker.setMapPoint(DEFAULT_MARKER_POINT);
	marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본 마커 blue
	marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 기본 마커
																// red

	mMapView.addPOIItem(marker);

	// ////////////////////////////////마커 끝
	
	//gps = (Button)findViewById(R.id.gps);
	
	//setgps();
	
	gps = (Button)findViewById(R.id.gps);
	gps.setOnClickListener(setcurrent);
	

}}

private OnClickListener setcurrent = new OnClickListener() {
	public void onClick(View v) {
		switch (0) {
		case 0: {
			mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);

			// 현재위치에 커스텀 마커 달아주기
			if (isUsingCustomLocationMarker) {
				mMapView.setCurrentLocationRadius(0);
				mMapView.setDefaultCurrentLocationMarker();
			} else {
				mMapView.setCurrentLocationRadius(100);
				mMapView.setCurrentLocationRadiusFillColor(Color
						.argb(77, 255, 255, 0));
				mMapView.setCurrentLocationRadiusStrokeColor(Color
						.argb(77, 255, 165, 0));

				MapPOIItem.ImageOffset trackingImageAnchorPointOffset = new MapPOIItem.ImageOffset(
						16, 16);
				MapPOIItem.ImageOffset directionImageAnchorPointOffset = new MapPOIItem.ImageOffset(
						65, 65);
				MapPOIItem.ImageOffset offImageAnchorPointOffset = new MapPOIItem.ImageOffset(
						15, 15);
				mMapView.setCustomCurrentLocationMarkerTrackingImage(
						R.drawable.custom_map_present_tracking,
						trackingImageAnchorPointOffset);
				mMapView.setCustomCurrentLocationMarkerDirectionImage(
						R.drawable.custom_map_present_direction,
						directionImageAnchorPointOffset);
				mMapView.setCustomCurrentLocationMarkerImage(
						R.drawable.custom_map_present,
						offImageAnchorPointOffset);
			}
			isUsingCustomLocationMarker = !isUsingCustomLocationMarker;
		}
			break;

		}
	}
};


public void onCalloutBalloonOfPOIItemTouched(MapView mapView,
	MapPOIItem mapPOIItem) {
System.out.println(mapPOIItem.getItemName());
///강남 1
if(mapPOIItem.getItemName().equals("개포2동") || mapPOIItem.getItemName().equals("논현1동") ||
		mapPOIItem.getItemName().equals("논현2동") || mapPOIItem.getItemName().equals("대치1동") ||
		mapPOIItem.getItemName().equals("대치2동") || mapPOIItem.getItemName().equals("대치4동") ||
		mapPOIItem.getItemName().equals("대치평생학습관") || mapPOIItem.getItemName().equals("도곡1동") ||
		mapPOIItem.getItemName().equals("삼성1동") || mapPOIItem.getItemName().equals("삼성2동") ||
		mapPOIItem.getItemName().equals("세곡문화센터") || mapPOIItem.getItemName().equals("신사문화센터") ||
		mapPOIItem.getItemName().equals("압구정평생학습관") || mapPOIItem.getItemName().equals("역삼1동") ||
		mapPOIItem.getItemName().equals("역삼2동") || mapPOIItem.getItemName().equals("청담동") ||
		mapPOIItem.getItemName().equals("청담평생학습관"))
{
	if(mapPOIItem.getItemName().equals("개포2동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 0);
		
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
	   
		
	}else if(mapPOIItem.getItemName().equals("논현1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("논현2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대치1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("대치2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("대치4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("대치평생학습관")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
		tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("도곡1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
		tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("도곡2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("삼성1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("삼성2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("세곡문화센터")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("신사문화센터")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("압구정평생학습관")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("역삼1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("역삼2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else if(mapPOIItem.getItemName().equals("청담동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 1);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	}
}
//강남구 끝

//강동구 2
if(mapPOIItem.getItemName().equals("강일동") || mapPOIItem.getItemName().equals("고덕1동") ||
		mapPOIItem.getItemName().equals("고덕2동") || mapPOIItem.getItemName().equals("길동") ||
		mapPOIItem.getItemName().equals("둔촌1동") || mapPOIItem.getItemName().equals("둔촌2동") ||
		mapPOIItem.getItemName().equals("명일1동") || mapPOIItem.getItemName().equals("명일2동") ||
		mapPOIItem.getItemName().equals("상일동") || mapPOIItem.getItemName().equals("성내1동") ||
		mapPOIItem.getItemName().equals("성내2동") || mapPOIItem.getItemName().equals("성내3동") ||
		mapPOIItem.getItemName().equals("암사1동") || mapPOIItem.getItemName().equals("암사2동") ||
		mapPOIItem.getItemName().equals("암사3동") || mapPOIItem.getItemName().equals("천호1동") ||
		mapPOIItem.getItemName().equals("천호2동")|| mapPOIItem.getItemName().equals("천호3동")){
	
	if(mapPOIItem.getItemName().equals("강일동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("고덕1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("고덕2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("길동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("둔촌1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("둔촌2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("명일1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("명일2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상일동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성내1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성내2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성내3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("암사1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("암사2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("암사3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("천호1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("천호2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 2);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//강동구 끝

//강북구 시작 3
if(mapPOIItem.getItemName().equals("미아동") || mapPOIItem.getItemName().equals("번1동") ||
		mapPOIItem.getItemName().equals("번2동") || mapPOIItem.getItemName().equals("번3동") ||
		mapPOIItem.getItemName().equals("삼각산동") || mapPOIItem.getItemName().equals("삼양동(삼양관)") ||
		mapPOIItem.getItemName().equals("삼양동(솔샘관)") || mapPOIItem.getItemName().equals("송중동(송중관)") ||
		mapPOIItem.getItemName().equals("송중동(정보문화관)") || mapPOIItem.getItemName().equals("송천동(송천관)") ||
		mapPOIItem.getItemName().equals("송천동(솔샘관)") || mapPOIItem.getItemName().equals("수유1동") ||
		mapPOIItem.getItemName().equals("수유2동") || mapPOIItem.getItemName().equals("수유3동") ||
		mapPOIItem.getItemName().equals("우이동") || mapPOIItem.getItemName().equals("인수동(나눔터관)") ||
		mapPOIItem.getItemName().equals("인수동(인수봉관)")){
	
	if(mapPOIItem.getItemName().equals("미아동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("번1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("번2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("번3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼각산동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼양동(삼양관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼양동(솔샘관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송중동(송중관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송중동(정보문화관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송천동(송천관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송천동(솔샘관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("수유1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("수유2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("수유3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("우이동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("인수동(나눔터관)")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 3);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//강북구 끝

//강서구 시작 4
if(mapPOIItem.getItemName().equals("가양1동") || mapPOIItem.getItemName().equals("가양2동") ||
		mapPOIItem.getItemName().equals("가양3동") || mapPOIItem.getItemName().equals("공항동") ||
		mapPOIItem.getItemName().equals("등촌2동") || mapPOIItem.getItemName().equals("등촌2동") ||
		mapPOIItem.getItemName().equals("등촌3동") || mapPOIItem.getItemName().equals("발산1동") ||
		mapPOIItem.getItemName().equals("방화1동") || mapPOIItem.getItemName().equals("방화2동") ||
		mapPOIItem.getItemName().equals("방화3동") || mapPOIItem.getItemName().equals("염창동") ||
		mapPOIItem.getItemName().equals("우장산동") || mapPOIItem.getItemName().equals("화곡본동") ||
		mapPOIItem.getItemName().equals("화곡1동") || mapPOIItem.getItemName().equals("화곡2동") ||
		mapPOIItem.getItemName().equals("화곡3동") || mapPOIItem.getItemName().equals("화곡4동") ||
		mapPOIItem.getItemName().equals("화곡6동") || mapPOIItem.getItemName().equals("화곡8동")){
	
	if(mapPOIItem.getItemName().equals("가양1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("가양2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("가양3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("공항동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("등촌1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("등촌2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("등촌3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("발산1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방화1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방화2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방화3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("염창동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("우장산동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("화곡본동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("화곡1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("화곡2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("화곡3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("화곡4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("화곡6동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 18);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 4);
		intent.putExtra("dong", 19);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
	
}
//강서구 끝

//관악구 시작 5
if(mapPOIItem.getItemName().equals("낙성대동") || mapPOIItem.getItemName().equals("난곡동") ||
		mapPOIItem.getItemName().equals("난향동") || mapPOIItem.getItemName().equals("남현동") ||
		mapPOIItem.getItemName().equals("대학동") || mapPOIItem.getItemName().equals("미성동") ||
		mapPOIItem.getItemName().equals("보라매동") || mapPOIItem.getItemName().equals("삼성동") ||
		mapPOIItem.getItemName().equals("서림동") || mapPOIItem.getItemName().equals("서원동") ||
		mapPOIItem.getItemName().equals("성현동") || mapPOIItem.getItemName().equals("신림동") ||
		mapPOIItem.getItemName().equals("신사동") || mapPOIItem.getItemName().equals("신원동") ||
		mapPOIItem.getItemName().equals("은천동") || mapPOIItem.getItemName().equals("은헌동") ||
		mapPOIItem.getItemName().equals("조원동") || mapPOIItem.getItemName().equals("중앙동") ||
		mapPOIItem.getItemName().equals("청룡동") || mapPOIItem.getItemName().equals("청림동") ||
		mapPOIItem.getItemName().equals("행운동")){
	
	if(mapPOIItem.getItemName().equals("낙성대동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("난곡동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("난향동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("남현동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대학동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("미성동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("보라매동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼성동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서림동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서원동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성현동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신림동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신사동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신원동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("은천동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("인헌동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("조원동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중앙동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("청룡동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 18);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("청림동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 19);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 5);
		intent.putExtra("dong", 20);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//관악구 끝

//광진구 시작 6
if(mapPOIItem.getItemName().equals("광장동") || mapPOIItem.getItemName().equals("구의1동") ||
		mapPOIItem.getItemName().equals("구의2동") || mapPOIItem.getItemName().equals("구의3동") ||
		mapPOIItem.getItemName().equals("군자동") || mapPOIItem.getItemName().equals("능동") ||
		mapPOIItem.getItemName().equals("자양1동") || mapPOIItem.getItemName().equals("자양2동") ||
		mapPOIItem.getItemName().equals("자양3동") || mapPOIItem.getItemName().equals("자양4동") ||
		mapPOIItem.getItemName().equals("중곡1동") || mapPOIItem.getItemName().equals("중곡2동") ||
		mapPOIItem.getItemName().equals("중곡3동") || mapPOIItem.getItemName().equals("중곡4동") ||
		mapPOIItem.getItemName().equals("화양동")){
	
	if(mapPOIItem.getItemName().equals("광장동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("구의1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구의2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구의3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("군자동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("능동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("자양1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("자양2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("자양3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("자양4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중곡1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중곡2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중곡3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중곡4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 6);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//광진구 끝

//구로구 시작 7
if(mapPOIItem.getItemName().equals("가리봉동") || mapPOIItem.getItemName().equals("개봉1동") ||
		mapPOIItem.getItemName().equals("개봉2동") || mapPOIItem.getItemName().equals("개봉3동") ||
		mapPOIItem.getItemName().equals("고척1동") || mapPOIItem.getItemName().equals("고척2동") ||
		mapPOIItem.getItemName().equals("구로1동") || mapPOIItem.getItemName().equals("구로2동") ||
		mapPOIItem.getItemName().equals("구로3동") || mapPOIItem.getItemName().equals("구로4동") ||
		mapPOIItem.getItemName().equals("구로5동") || mapPOIItem.getItemName().equals("수궁동") ||
		mapPOIItem.getItemName().equals("신도림동") || mapPOIItem.getItemName().equals("오류1동") ||
		mapPOIItem.getItemName().equals("오류2동")){
	
	if(mapPOIItem.getItemName().equals("가리봉동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("개봉1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("개봉2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("개봉3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("고척1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("고척2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구로1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구로2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구로3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구로4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구로5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("수궁동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신도림동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("오류1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 7);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//구로구 끝

//금천구 시작 8
if(mapPOIItem.getItemName().equals("가산동") || mapPOIItem.getItemName().equals("독산1동") ||
		mapPOIItem.getItemName().equals("독산2동") || mapPOIItem.getItemName().equals("독산3동") ||
		mapPOIItem.getItemName().equals("독산4동") || mapPOIItem.getItemName().equals("시흥1동") ||
		mapPOIItem.getItemName().equals("시흥2동") || mapPOIItem.getItemName().equals("시흥3동") ||
		mapPOIItem.getItemName().equals("시흥4동") || mapPOIItem.getItemName().equals("시흥5동")){
	
	if(mapPOIItem.getItemName().equals("가산동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("독산1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("독산2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("독산3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("독산4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("시흥1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("시흥2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("시흥3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("시흥4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 8);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
}

//금천구 끝

//노원 시작 9    19개
if(mapPOIItem.getItemName().equals("공릉1동") || mapPOIItem.getItemName().equals("공릉2동") ||
		mapPOIItem.getItemName().equals("상계1동") || mapPOIItem.getItemName().equals("상계2동") ||
		mapPOIItem.getItemName().equals("상계3.4동") || mapPOIItem.getItemName().equals("상계5동") ||
		mapPOIItem.getItemName().equals("상계6.7동") || mapPOIItem.getItemName().equals("상계8동") ||
		mapPOIItem.getItemName().equals("상계9동") || mapPOIItem.getItemName().equals("상계10동") ||
		mapPOIItem.getItemName().equals("월계1동") || mapPOIItem.getItemName().equals("월계2동") ||
		mapPOIItem.getItemName().equals("월계3동") || mapPOIItem.getItemName().equals("중계본동") ||
		mapPOIItem.getItemName().equals("중계1동") || mapPOIItem.getItemName().equals("중계2.3동") ||
		mapPOIItem.getItemName().equals("중계4동") || mapPOIItem.getItemName().equals("하계1동") ||
		mapPOIItem.getItemName().equals("하계2동")){
	
	if(mapPOIItem.getItemName().equals("공릉1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("공릉2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상계1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상계2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상게3,4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상계5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상계6,7동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상계8동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상계9동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상게10동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("월계1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("월계2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("월계3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중계본동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중게1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중계2,3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중계4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("하계1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 9);
		intent.putExtra("dong", 18);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//노원구 끝

//도봉구 10  14개
if(mapPOIItem.getItemName().equals("도봉1동") || mapPOIItem.getItemName().equals("도봉2동") ||
		mapPOIItem.getItemName().equals("방학1동") || mapPOIItem.getItemName().equals("방학2동") ||
		mapPOIItem.getItemName().equals("방학3동") || mapPOIItem.getItemName().equals("쌍문1동") ||
		mapPOIItem.getItemName().equals("쌍문2동") || mapPOIItem.getItemName().equals("쌍문3동") ||
		mapPOIItem.getItemName().equals("쌍문4동") || mapPOIItem.getItemName().equals("창1동") ||
		mapPOIItem.getItemName().equals("창2동") || mapPOIItem.getItemName().equals("창3동") ||
		mapPOIItem.getItemName().equals("창4동") || mapPOIItem.getItemName().equals("창5동")){
	
	if(mapPOIItem.getItemName().equals("도봉1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("도봉2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방학1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방학2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방학3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("쌍문1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("쌍문2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("쌍문3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("쌍문4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 10);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//도봉구 끝
//동대문구 시작 11    14개
if(mapPOIItem.getItemName().equals("답십리1동") || mapPOIItem.getItemName().equals("답십리2동") ||
		mapPOIItem.getItemName().equals("용신동") || mapPOIItem.getItemName().equals("이문1동") ||
		mapPOIItem.getItemName().equals("이문2동") || mapPOIItem.getItemName().equals("장안1동") ||
		mapPOIItem.getItemName().equals("장안2동") || mapPOIItem.getItemName().equals("전농1동") ||
		mapPOIItem.getItemName().equals("전농2동") || mapPOIItem.getItemName().equals("제기동") ||
		mapPOIItem.getItemName().equals("청량리동") || mapPOIItem.getItemName().equals("회기동") ||
		mapPOIItem.getItemName().equals("휘경1동") || mapPOIItem.getItemName().equals("휘경2동")){
	
	if(mapPOIItem.getItemName().equals("답십리1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("답십리2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("용신동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("이문1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("이문2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장안1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장안2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("전농1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("전농2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("제기동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("청량리동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("회기동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("휘경1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 11);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//동대문구 끝

//동작구 시작 12     15개
if(mapPOIItem.getItemName().equals("노량진1동") || mapPOIItem.getItemName().equals("노량진2동") ||
		mapPOIItem.getItemName().equals("대방동") || mapPOIItem.getItemName().equals("사당1동") ||
		mapPOIItem.getItemName().equals("사당2동") || mapPOIItem.getItemName().equals("사당3동") ||
		mapPOIItem.getItemName().equals("사당4동") || mapPOIItem.getItemName().equals("사당5동") ||
		mapPOIItem.getItemName().equals("상도1동") || mapPOIItem.getItemName().equals("상도2동") ||
		mapPOIItem.getItemName().equals("상도3동") || mapPOIItem.getItemName().equals("상도4동") ||
		mapPOIItem.getItemName().equals("신대방1동") || mapPOIItem.getItemName().equals("신대방2동")||
		mapPOIItem.getItemName().equals("흑석동")){
	
	if(mapPOIItem.getItemName().equals("노량진1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("노량진2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대방동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사당1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사당2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사당3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사당4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사당5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상도1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상도2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상도3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상도4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신대방1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신대방2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 12);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//동작구 끝

//마포구 13    16개
if(mapPOIItem.getItemName().equals("공덕동") || mapPOIItem.getItemName().equals("대흥동") ||
		mapPOIItem.getItemName().equals("도화동") || mapPOIItem.getItemName().equals("망원1동") ||
		mapPOIItem.getItemName().equals("망원2동") || mapPOIItem.getItemName().equals("상암동") ||
		mapPOIItem.getItemName().equals("서강동") || mapPOIItem.getItemName().equals("서교동") ||
		mapPOIItem.getItemName().equals("성산1동") || mapPOIItem.getItemName().equals("성산2동") ||
		mapPOIItem.getItemName().equals("신수동") || mapPOIItem.getItemName().equals("아현동") ||
		mapPOIItem.getItemName().equals("연남동") || mapPOIItem.getItemName().equals("염리동")||
		mapPOIItem.getItemName().equals("용강동")|| mapPOIItem.getItemName().equals("합정동")){
	
	if(mapPOIItem.getItemName().equals("공덕동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("대흥동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("도화동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("망원1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("망원2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상암동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서강동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서교동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성산1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성산2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신수동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("아현동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("연남동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("염리동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("용강동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 13);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//마포구 끝

//서대문구 14   13개
if(mapPOIItem.getItemName().equals("남가좌1동") || mapPOIItem.getItemName().equals("남가좌2동") ||
		mapPOIItem.getItemName().equals("북가좌1동") || mapPOIItem.getItemName().equals("북가좌2동") ||
		mapPOIItem.getItemName().equals("신촌동") || mapPOIItem.getItemName().equals("연희동") ||
		mapPOIItem.getItemName().equals("천연동") || mapPOIItem.getItemName().equals("충현동") ||
		mapPOIItem.getItemName().equals("홍은1동") || mapPOIItem.getItemName().equals("홍은2동") ||
		mapPOIItem.getItemName().equals("홍제1동") || mapPOIItem.getItemName().equals("홍제2동") ||
		mapPOIItem.getItemName().equals("홍제3동") ){
	
	if(mapPOIItem.getItemName().equals("남가좌1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("남가좌2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("북가좌1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("북가좌2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신촌동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("연희동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("천연동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("충현동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("홍은1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("홍은2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("홍제1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("홍제2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 14);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//서대문구 끝
//서초구 시작 15  18개
if(mapPOIItem.getItemName().equals("구민정보화교실") ||mapPOIItem.getItemName().equals("내곡동") |mapPOIItem.getItemName().equals("문화체육관광과") || mapPOIItem.getItemName().equals("반포본동") ||
		mapPOIItem.getItemName().equals("반포1동") || mapPOIItem.getItemName().equals("반포2동") ||
		mapPOIItem.getItemName().equals("반포3동") || mapPOIItem.getItemName().equals("반포4동") ||
		mapPOIItem.getItemName().equals("방배본동") || mapPOIItem.getItemName().equals("방배1동") ||
		mapPOIItem.getItemName().equals("방배2동") || mapPOIItem.getItemName().equals("방배3동") ||
		mapPOIItem.getItemName().equals("방배4동") ||mapPOIItem.getItemName().equals("보건소") || mapPOIItem.getItemName().equals("서초구청") ||  mapPOIItem.getItemName().equals("서초1동") ||
		mapPOIItem.getItemName().equals("서초2동") || mapPOIItem.getItemName().equals("서초3동") ||
		mapPOIItem.getItemName().equals("서초4동") || mapPOIItem.getItemName().equals("양재1동") ||
		mapPOIItem.getItemName().equals("양재2동") || mapPOIItem.getItemName().equals("잠원동")){
	
	if(mapPOIItem.getItemName().equals("구민정보화교실")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
		tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("내곡동")){

		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
		tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("내곡동")){

		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
		tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("문화체육관광과")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("반포1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("반포2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("반포3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("반포4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방배본동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방배1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방배2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방배3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방배4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("보건소")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서초구청")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서초1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서초2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서초3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서초4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 18);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("양재1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 19);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("양재2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 20);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 15);
		intent.putExtra("dong", 21);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//서초구 끝

//성동구 시작 16 17개
if(mapPOIItem.getItemName().equals("금호1가동") || mapPOIItem.getItemName().equals("금호2-3가동") ||
		mapPOIItem.getItemName().equals("금호4가동") || mapPOIItem.getItemName().equals("마장동") ||
		mapPOIItem.getItemName().equals("사근동") || mapPOIItem.getItemName().equals("성수1가1동") ||
		mapPOIItem.getItemName().equals("성수1가2동") || mapPOIItem.getItemName().equals("송정동") ||
		mapPOIItem.getItemName().equals("옥수동") || mapPOIItem.getItemName().equals("왕십리2동") ||
		mapPOIItem.getItemName().equals("왕십리도선동") || mapPOIItem.getItemName().equals("용답동") ||
		mapPOIItem.getItemName().equals("응봉동") || mapPOIItem.getItemName().equals("행당1동") ||
		mapPOIItem.getItemName().equals("행당2동") || mapPOIItem.getItemName().equals("성수2가1동") ||
		mapPOIItem.getItemName().equals("성수2가3동") ){
	
	if(mapPOIItem.getItemName().equals("금호1가동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("금호2-3가동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("금호4가동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("마장동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사근동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성수1가1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성수1가2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성수2가1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성수2가3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송정동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("옥수동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("왕십리2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("왕십리도선동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("용답동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("응봉동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("행당1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 16);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//성동구 끝

//성북구 싲가 17  20개
if(mapPOIItem.getItemName().equals("길음1동") || mapPOIItem.getItemName().equals("길음2동") ||
		mapPOIItem.getItemName().equals("돈암1동") || mapPOIItem.getItemName().equals("돈암2동") ||
		mapPOIItem.getItemName().equals("동선동") || mapPOIItem.getItemName().equals("보문동") ||
		mapPOIItem.getItemName().equals("삼선동") || mapPOIItem.getItemName().equals("석관동") ||
		mapPOIItem.getItemName().equals("성북동") || mapPOIItem.getItemName().equals("안암동") ||
		mapPOIItem.getItemName().equals("월곡1동") || mapPOIItem.getItemName().equals("월곡2동") ||
		mapPOIItem.getItemName().equals("장위1동") || mapPOIItem.getItemName().equals("장위2동") ||
		mapPOIItem.getItemName().equals("장위3동") || mapPOIItem.getItemName().equals("정릉1동") ||
		mapPOIItem.getItemName().equals("정릉2동") || mapPOIItem.getItemName().equals("정릉3동") ||
		mapPOIItem.getItemName().equals("정릉4동") || mapPOIItem.getItemName().equals("종암동") ){
	
	if(mapPOIItem.getItemName().equals("길음1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("길음2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("돈암1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("돈암2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("동선동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("보문동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼선동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("석관동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("성북동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("암암동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("월곡1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("월곡2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장위1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장위2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장위3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("정릉1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("정릉2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("정릉3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("정릉4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 18);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 17);
		intent.putExtra("dong", 19);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//성북구 끝
//송파구 시작 18    26개
if(mapPOIItem.getItemName().equals("가락2동") || mapPOIItem.getItemName().equals("가락본동") ||
		mapPOIItem.getItemName().equals("거여1동") || mapPOIItem.getItemName().equals("거여2동") ||
		mapPOIItem.getItemName().equals("마천1동") || mapPOIItem.getItemName().equals("마천2동") ||
		mapPOIItem.getItemName().equals("문정1동") || mapPOIItem.getItemName().equals("문정2동") ||
		mapPOIItem.getItemName().equals("방이1동") || mapPOIItem.getItemName().equals("방이2동") ||
		mapPOIItem.getItemName().equals("삼전동") || mapPOIItem.getItemName().equals("석촌동") ||
		mapPOIItem.getItemName().equals("송파1동") || mapPOIItem.getItemName().equals("송파2동") ||
		mapPOIItem.getItemName().equals("오금동") || mapPOIItem.getItemName().equals("오륜동") ||
		mapPOIItem.getItemName().equals("잠실2동") || mapPOIItem.getItemName().equals("잠실3동") ||
		mapPOIItem.getItemName().equals("잠실4동") || mapPOIItem.getItemName().equals("잠실6동") ||
		mapPOIItem.getItemName().equals("잠실7동") || mapPOIItem.getItemName().equals("잠실본동") ||
		mapPOIItem.getItemName().equals("장지동") || mapPOIItem.getItemName().equals("풍납1동") ||
		mapPOIItem.getItemName().equals("풍납2동") || mapPOIItem.getItemName().equals("위례동")){
	
	if(mapPOIItem.getItemName().equals("가락2동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("가락본동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("거여1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("거여2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("마천1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("마천2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("문정1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("문정2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방이1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("방이2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼전동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("석촌동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송파1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("송파2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("오금동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("오륜동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("위례동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("잠실2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("잠실3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 18);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("잠실4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 19);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("잠실6동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 20);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("잠실7동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 21);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("잠실본동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 22);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장지동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 23);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("풍납1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 24);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 18);
		intent.putExtra("dong", 25);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//송파구 끝

//양천구시작 19   18개
if(mapPOIItem.getItemName().equals("목1동") || mapPOIItem.getItemName().equals("목2동") ||
		mapPOIItem.getItemName().equals("목3동") || mapPOIItem.getItemName().equals("목4동") ||
		mapPOIItem.getItemName().equals("목5동") || mapPOIItem.getItemName().equals("신월1동") ||
		mapPOIItem.getItemName().equals("신월2동") || mapPOIItem.getItemName().equals("신월3동") ||
		mapPOIItem.getItemName().equals("신월4동") || mapPOIItem.getItemName().equals("신월5동") ||
		mapPOIItem.getItemName().equals("신월6동") || mapPOIItem.getItemName().equals("신월7동") ||
		mapPOIItem.getItemName().equals("신정1동") || mapPOIItem.getItemName().equals("신정2동") ||
		mapPOIItem.getItemName().equals("신정3동") || mapPOIItem.getItemName().equals("신정4동") ||
		mapPOIItem.getItemName().equals("신정6동") || mapPOIItem.getItemName().equals("신정7동") ){
	
	if(mapPOIItem.getItemName().equals("목1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("목2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("목3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("목4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("목5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월6동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신월7동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신정1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신정2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신정3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신정4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신정6동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 19);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//양천구 끝

//영등포 시작 20    18개
if(mapPOIItem.getItemName().equals("당산1동") || mapPOIItem.getItemName().equals("당산2동") ||
		mapPOIItem.getItemName().equals("대림1동") || mapPOIItem.getItemName().equals("대림2동") ||
		mapPOIItem.getItemName().equals("대림3동") || mapPOIItem.getItemName().equals("도림동") ||
		mapPOIItem.getItemName().equals("문래동") || mapPOIItem.getItemName().equals("신길1동") ||
		mapPOIItem.getItemName().equals("신길3동") || mapPOIItem.getItemName().equals("신길4동") ||
		mapPOIItem.getItemName().equals("신길5동") || mapPOIItem.getItemName().equals("신길6동") ||
		mapPOIItem.getItemName().equals("신길7동") || mapPOIItem.getItemName().equals("양평1동") ||
		mapPOIItem.getItemName().equals("양평2동") || mapPOIItem.getItemName().equals("여의동") ||
		mapPOIItem.getItemName().equals("영등포동") || mapPOIItem.getItemName().equals("영등포본동") ){
	
	if(mapPOIItem.getItemName().equals("당산1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("당산2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대림1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대림12동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대림3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("도림동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("문래동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신길1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신길3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신길4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신길5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신길6동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신길7동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("양평1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("양평2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("여의동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("영등포동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 16);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else{
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 20);
		intent.putExtra("dong", 17);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//영등포 끝

//용산구 시작 21  16개
if(mapPOIItem.getItemName().equals("남영동") || mapPOIItem.getItemName().equals("보광동") ||
		mapPOIItem.getItemName().equals("서빙고동") || mapPOIItem.getItemName().equals("용문동") ||
		mapPOIItem.getItemName().equals("용산2가동") || mapPOIItem.getItemName().equals("월효로1동") ||
		mapPOIItem.getItemName().equals("원효로2동") || mapPOIItem.getItemName().equals("이촌1동") ||
		mapPOIItem.getItemName().equals("이촌2동") || mapPOIItem.getItemName().equals("이태원1동") ||
		mapPOIItem.getItemName().equals("이태원2동") || mapPOIItem.getItemName().equals("청파동") ||
		mapPOIItem.getItemName().equals("한강로동") || mapPOIItem.getItemName().equals("한남동") ||
		mapPOIItem.getItemName().equals("효창동") || mapPOIItem.getItemName().equals("후암동") ){
	
	if(mapPOIItem.getItemName().equals("남영동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("보광동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("서빙고동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("용문동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("용산2가동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("원효로1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("원효로2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("이촌1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("이촌2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("이태원1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("이태원2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("청파동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("한강로동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("한남동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("효창동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 21);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//용산 끝

//은평구 시작 22   15개
if(mapPOIItem.getItemName().equals("갈현1동") || mapPOIItem.getItemName().equals("갈현2동") ||
		mapPOIItem.getItemName().equals("구산동") || mapPOIItem.getItemName().equals("녹번동") ||
		mapPOIItem.getItemName().equals("대조동") || mapPOIItem.getItemName().equals("불광1동") ||
		mapPOIItem.getItemName().equals("불광2동") || mapPOIItem.getItemName().equals("신사1동") || mapPOIItem.getItemName().equals("신사1동") ||
		mapPOIItem.getItemName().equals("신사2동") || mapPOIItem.getItemName().equals("역촌동") ||
		mapPOIItem.getItemName().equals("응암1동") || mapPOIItem.getItemName().equals("음암2동") ||
		mapPOIItem.getItemName().equals("응암3동") || mapPOIItem.getItemName().equals("증산동") ||
		mapPOIItem.getItemName().equals("진관동")){
	
	if(mapPOIItem.getItemName().equals("갈현1동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("갈현2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("구산동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("녹번동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("대조동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("불광1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("불광2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("수색동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신사1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신사2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("역촌동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("응암1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("응암2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("응암3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("증산동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 22);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//은평구 끝 

//종로구 시작 23  16개
if(mapPOIItem.getItemName().equals("가회동") || mapPOIItem.getItemName().equals("교남동") ||
		mapPOIItem.getItemName().equals("무악동") || mapPOIItem.getItemName().equals("부암동") ||
		mapPOIItem.getItemName().equals("사직동") || mapPOIItem.getItemName().equals("숭인1동") ||
		mapPOIItem.getItemName().equals("숭인2동") || mapPOIItem.getItemName().equals("삼청동") ||
		mapPOIItem.getItemName().equals("종로1.2.3.4가동") || mapPOIItem.getItemName().equals("종로5.6가동") ||
		mapPOIItem.getItemName().equals("창신1동") || mapPOIItem.getItemName().equals("창신2동") ||
		mapPOIItem.getItemName().equals("창신3동") || mapPOIItem.getItemName().equals("청운효자동") ||
		mapPOIItem.getItemName().equals("평창동") || mapPOIItem.getItemName().equals("혜화동")){
	
	if(mapPOIItem.getItemName().equals("가회동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("교남동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("무악동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("부암동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("사직동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("숭인1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("숭인2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("삼청동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("종로1.2.3.4가동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("종로5.6가동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창신1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창신2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("창신3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("청운효자동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("평창동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 23);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//종로구 끝

//중구 시작 24   15개
if(mapPOIItem.getItemName().equals("광희동") || mapPOIItem.getItemName().equals("다산동") ||
		mapPOIItem.getItemName().equals("동화동") || mapPOIItem.getItemName().equals("명동") ||
		mapPOIItem.getItemName().equals("소공동") || mapPOIItem.getItemName().equals("신당5동") ||
		mapPOIItem.getItemName().equals("신당동") || mapPOIItem.getItemName().equals("약수동") ||
		mapPOIItem.getItemName().equals("을지로동") || mapPOIItem.getItemName().equals("장충동") ||
		mapPOIItem.getItemName().equals("중림동") || mapPOIItem.getItemName().equals("청구동") ||
		mapPOIItem.getItemName().equals("필동") || mapPOIItem.getItemName().equals("황학동") ||
		mapPOIItem.getItemName().equals("회현동")){
	
	if(mapPOIItem.getItemName().equals("광희동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("다산동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("동화동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("명동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("소공동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신당5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신당동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("약수동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("을지로동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("장충동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중림동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("청구동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("필동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("황학동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 24);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}
//중구 끝 

//중랑구 25  16개
if(mapPOIItem.getItemName().equals("망우본동") || mapPOIItem.getItemName().equals("망우3동") ||
		mapPOIItem.getItemName().equals("면목본동") || mapPOIItem.getItemName().equals("면목2동") ||
		mapPOIItem.getItemName().equals("면목3,8동") || mapPOIItem.getItemName().equals("면목4동") ||
		mapPOIItem.getItemName().equals("면목5동") || mapPOIItem.getItemName().equals("면목7동") ||
		mapPOIItem.getItemName().equals("묵1동") || mapPOIItem.getItemName().equals("묵2동") ||
		mapPOIItem.getItemName().equals("상봉1동") || mapPOIItem.getItemName().equals("상봉2동") ||
		mapPOIItem.getItemName().equals("신내1동") || mapPOIItem.getItemName().equals("신내2동") ||
		mapPOIItem.getItemName().equals("중화1동") || mapPOIItem.getItemName().equals("중화2동")){
	
	if(mapPOIItem.getItemName().equals("망우본동")){
		
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 0);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
		
		
	}else if(mapPOIItem.getItemName().equals("망우3동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 1);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("면목본동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 2);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("면목2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 3);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("면목3,8동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 4);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("면목4동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 5);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("면목5동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 6);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("면목7동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 7);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("묵1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 8);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("묵2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 9);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상봉1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 10);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("상봉2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 11);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신내1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 12);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("신내2동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 13);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else if(mapPOIItem.getItemName().equals("중화1동")){
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 14);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}else {
		Intent intent = new Intent(tab_map.this, tab_map_program.class);
		intent.putExtra("gu", 25);
		intent.putExtra("dong", 15);
		
		
		View view = tab_map_tabhost.MapTabHGroup.getLocalActivityManager()
	              .startActivity("tab_map_program", intent
	              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	          
	          tab_map_tabhost.MapTabHGroup.replaceView(view);
	
	}
	
}


//중랑구 끝~~~~~~!

}

@Override
public void onCalloutBalloonOfPOIItemTouched(MapView arg0, MapPOIItem arg1,
	CalloutBalloonButtonType arg2) {
// TODO Auto-generated method stub

}

@Override
public void onDraggablePOIItemMoved(MapView arg0, MapPOIItem arg1,
	MapPoint arg2) {
// TODO Auto-generated method stub

}

@Override
public void onPOIItemSelected(MapView arg0, MapPOIItem arg1) {
// TODO Auto-generated method stub

}

// /현재 위치 찍기

@Override
public void onCurrentLocationUpdate(MapView mapView,
	MapPoint currentLocation, float accuracyInMeters) {

MapPoint.GeoCoordinate mapPointGeo = currentLocation
		.getMapPointGeoCoord();
Log.i(LOG_TAG, String.format(
		"MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)",
		mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));

}




@Override
public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

}

@Override
public void onCurrentLocationUpdateFailed(MapView mapView) {

}

@Override
public void onCurrentLocationUpdateCancelled(MapView mapView) {

}

// //////////////현재위치표시끝

// mapvieweventlistener시작
@Override
public void onMapViewCenterPointMoved(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewDoubleTapped(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewDragEnded(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewDragStarted(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewInitialized(MapView arg0) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewLongPressed(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewMoveFinished(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewSingleTapped(MapView arg0, MapPoint arg1) {
// TODO Auto-generated method stub

}

@Override
public void onMapViewZoomLevelChanged(MapView arg0, int arg1) {
// TODO Auto-generated method stub

}

// mapvieweventlistener끝

}

