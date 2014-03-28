package ch.bfh.mobiComp.peg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.peg.R;

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
	  View view = inflater.inflate(R.layout.crawldetailactivity, container, false);
	  return view;
	 }

	 public void setText(String item) {
	  TextView view = (TextView) getView().findViewById(R.id.detailsText);
	  view.setText(item);
	 }
	
}
