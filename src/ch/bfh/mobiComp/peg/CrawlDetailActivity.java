package ch.bfh.mobiComp.peg;

import static com.microsoft.windowsazure.mobileservices.MobileServiceQueryOperations.val;

import java.net.MalformedURLException;
import java.util.List;

import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ch.bfh.mobiComp.peg.data.PubCrawlItem;
import ch.bfh.mobiComp.peg.data.PubItem;
import ch.bfh.mobiComp.peg.data.UserCrawlItem;

import com.example.peg.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

public class CrawlDetailActivity extends FragmentActivity {

	private MobileServiceClient mClient;
	private ArrayAdapter<PubItem> pubAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}

		setContentView(R.layout.crawldetail);

		TextView crawlDesc = (TextView) findViewById(R.id.tv_crawlDesc);
		// crawlDesc.setText(text)
		// TODO. crawl description

		String s = getIntent().getStringExtra("CRAWLNAME");
		TextView view = (TextView) findViewById(R.id.tv_crawlName);
		view.setText(s);

//		this.setTitle(getIntent().getStringExtra("CRAWLNAME"));

		// Create the Mobile Service Client instance, using the provided
		// Mobile Service URL and key
		try {
			mClient = new MobileServiceClient(getResources().getString(
					R.string.azureUrl), getResources().getString(
					R.string.azureKey), this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		pubAdapter = new ArrayAdapter<PubItem>(this, android.R.layout.simple_list_item_1);
		
		ListView pubList = (ListView) findViewById(R.id.lv_pubList);
		pubList.setAdapter(pubAdapter);
		
		final String crawlId = getIntent().getStringExtra("CRAWLID");
		
		Button joinButton = (Button) findViewById(R.id.btnJoinCrawl);
		joinButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				joinCrawl(crawlId);
			}
		});
		
		loadPubsForCrawl(crawlId);
		}
	
	private  void joinCrawl(String crawlId) {
		UserCrawlItem userCrawl = new UserCrawlItem();
		userCrawl.setCrawlId(crawlId);
		String userId = Secure.getString(getBaseContext().getContentResolver(),Secure.ANDROID_ID); 
		userCrawl.setUserId(userId);
		
		MobileServiceTable<UserCrawlItem> userCrawlTable = mClient.getTable(UserCrawlItem.class);
		userCrawlTable.insert(userCrawl, new TableOperationCallback<UserCrawlItem>() {
			
			@Override
			public void onCompleted(UserCrawlItem arg0, Exception ex,	ServiceFilterResponse arg2) {
				if(ex == null) {
					Toast.makeText(getApplicationContext(), "Joined", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
	}
//
//	private void loadPubsForCrawl(String crawlId) {
//		MobileServiceTable<PubCrawlItem> pubCrawlTable = mClient.getTable(PubCrawlItem.class);
//		
//		
//		pubCrawlTable.where().field("crawlid").eq(val(crawlId)).execute(new TableQueryCallback<PubCrawlItem>() {
//			int pubCounter = 0;
//		pubAdapter = new ArrayAdapter<PubItem>(this,
//				android.R.layout.simple_list_item_1);
//
//		ListView lvPub = (ListView) findViewById(R.id.lv_pubList);
//		// TODO: Get PubItems by CrawlItem Id from DB
//		String[] pubs = new String[] { "Pub1", "Pub2", "Pub3" };
//		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, pubs);
//
//		lvPub.setAdapter(adapter);
//
//		lvPub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				String item = (String) adapter.getItem(arg2);
//
//				Intent intent = new Intent(getApplicationContext(),
//						PubDetailActivity.class);
//				// TODO: The ID of the clicked PubItem must be notified to
//				// PubDetailActivity
//				intent.putExtra("value", item);
//				startActivity(intent);
//
//			}
//		});
//
//		String crawlId = getIntent().getStringExtra("CRAWLID");
//
//		loadPubsForCrawl(crawlId);
//	}

	private void loadPubsForCrawl(String crawlId) {
		MobileServiceTable<PubCrawlItem> pubCrawlTable = mClient.getTable(PubCrawlItem.class);

		pubCrawlTable.where().field("crawlid").eq(val(crawlId))
		.execute(new TableQueryCallback<PubCrawlItem>() {
			int pubCounter = 0;

			@Override
			public void onCompleted(List<PubCrawlItem> result,
					final int count, Exception ex,
					ServiceFilterResponse serviceFilterResponse) {
				for (PubCrawlItem pubCrawl : result) {
					Log.d("PUBCRAWL", pubCrawl.getPubId());
					pubCounter++;

					// Load Pubs
					MobileServiceTable<PubItem> pubTable = mClient.getTable(PubItem.class);
					pubTable.where().field("id").eq(pubCrawl.getPubId()).execute(new TableQueryCallback<PubItem>() {
						@Override
						public void onCompleted(
								List<PubItem> arg0, int arg1,
								Exception arg2,
								ServiceFilterResponse arg3) {
							for (PubItem pub : arg0) {
								pubAdapter.add(pub);
								Log.d("PUB", pub.getName());
							}

							setUpMap(pubCounter);
						}

						private void setUpMap(int count) {

							SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFrag);

							if (pubAdapter.isEmpty()) {
								return;
							}

							if (pubAdapter.getCount() < count) {
								return;
							}

							// All pubs are loaded now

							LatLng firstpub = new LatLng(Double.valueOf(pubAdapter.getItem(0).getLatitude()), Double.valueOf(pubAdapter.getItem(0).getLongitude()));
							mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(firstpub,	13.0f));

							PolylineOptions line = new PolylineOptions();
							for (int i = 0; i < pubAdapter.getCount(); i++) {
								PubItem pub = pubAdapter.getItem(i);

								LatLng pubPos = new LatLng(Double.valueOf(pub.getLatitude()), Double.valueOf(pub.getLongitude()));
								mapFragment.getMap().addMarker(new MarkerOptions().position(pubPos).title(pub.getName()));
								line.add(pubPos);
							}
							mapFragment.getMap().addPolyline(line);

						}
					});

				}
			}
		});

	}

}
