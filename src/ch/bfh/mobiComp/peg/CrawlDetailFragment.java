package ch.bfh.mobiComp.peg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peg.R;
import com.google.android.gms.maps.MapView;

public class CrawlDetailFragment extends Fragment{

	@Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  Log.e("Test", "hello");
	 }

	 @Override
	 public void onActivityCreated(Bundle savedInstanceState) {
	  super.onActivityCreated(savedInstanceState);

	 }

	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
		 MapView m;
		 
	  View view = inflater.inflate(R.layout.crawldetail, container, false);
	  m = (MapView) view.findViewById(R.id.mapView);
	          m.onCreate(savedInstanceState);

	  return view;
	 }

	 public void setText(String item, int text) {
	  TextView view = (TextView) getView().findViewById(text);
	  view.setText(item);
	 }
	
}
