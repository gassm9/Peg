package ch.bfh.mobiComp.peg;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends SupportMapFragment {
	
	final LatLng bern = new LatLng(46.951081, 7.438637);	
	
	@Override
	public void onStart() {
		super.onStart();
		this.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(bern, 10.0f));
		this.getMap().addMarker(new MarkerOptions().position(bern).title("Bern")); 
	}
}
