package ch.bfh.mobiComp.peg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import ch.bfh.mobiComp.peg.data.CrawlItem;

import com.example.peg.R;
import com.google.android.gms.maps.GoogleMap;

public class CrawlDetailFragment extends Fragment {

	private ListView lvPub;

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		GoogleMap map;

		View view = inflater.inflate(R.layout.crawldetail, container, false);

		// TODO: Integrate GoogleMaps
		
//		map = ((SupportMapFragment) getFragmentManager().findFragmentById(
//				R.id.map)).getMap();

		// TODO: Get PubItems by CrawlItem Id from DB

		String[] pubs = new String[] { "Pub1", "Pub2", "Pub3" };

		lvPub = (ListView) view.findViewById(R.id.lv_pubList);

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1, pubs);

		lvPub.setAdapter(adapter);

		lvPub.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String item = (String) adapter.getItem(arg2);

				Intent intent = new Intent(getActivity().getApplicationContext(), PubDetailActivity.class);
				// TODO: The ID of the clicked PubItem must be notified to
				// PubDetailActivity
				intent.putExtra("value", item);
				startActivity(intent);

			}
		});

		return view;
	}

	 public void setText(String item, int text) {
	  TextView view = (TextView) getView().findViewById(text);
	  view.setText(item);
	 }

	public void showInfo(CrawlItem crawlItem) {
		// set description,
		
		// zoom in map...
		
	}
	
	
}
