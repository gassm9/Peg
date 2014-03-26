package ch.bfh.mobiComp.peg;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapFragment extends SupportMapFragment {
	
	@Override
	public void onStart() {
		super.onStart();
		
		List<LatLng> pubs = loadData();
		
		this.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(pubs.get(0), 13.0f));
		PolylineOptions line = new PolylineOptions();
		for(LatLng pub : pubs) {
			this.getMap().addMarker(new MarkerOptions().position(pub).title("Pub"));
			line.add(pub);
		}
		this.getMap().addPolyline(line);
		
	}
	
	private List<LatLng> loadData() {
		
		// TODO Load from Azure....
		List<LatLng> list = new ArrayList<LatLng>();
		list.add(new LatLng(46.96081, 7.438537));
		list.add(new LatLng(46.951281, 7.448137));
		list.add(new LatLng(46.940981, 7.410657));
		
		return list;
	}
}
