package ch.bfh.mobiComp.peg;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.peg.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class CrawlDetailActivity extends Activity {
	
	private ListView lvPub;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.crawldetail);

		// Need to check if Activity has been switched to landscape mode
		// If yes, finished and go back to the start Activity
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		
		//TODO: Get PubItems by CrawlItem Id from DB
		
		String[] pubs = new String[] { "Pub1", "Pub2", "Pub3"};
		
		lvPub = (ListView) findViewById(R.id.lv_pubList);
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, pubs);
		
		lvPub.setAdapter(adapter);
		
		lvPub.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String item = (String) adapter.getItem(arg2);

					Intent intent = new Intent(getApplicationContext(),
							PubDetailActivity.class);
					//TODO: The ID of the clicked PubItem must be notified to PubDetailActivity
					intent.putExtra("value", item);
					startActivity(intent);
				
			}
		});
		
		// TODO: Integrate GoogleMaps
		
//		GoogleMap map;
//
//		SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//		map = supportMapFragment.getMap();
		

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String s = extras.getString("value");
			TextView view = (TextView) findViewById(R.id.tv_crawlName);
			view.setText(s);
		}
	}

}
