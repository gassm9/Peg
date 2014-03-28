package ch.bfh.mobiComp.peg;

import static com.microsoft.windowsazure.mobileservices.MobileServiceQueryOperations.val;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ch.bfh.mobiComp.peg.data.PubCrawlItem;
import ch.bfh.mobiComp.peg.data.PubItem;

import com.example.peg.R;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

public class CrawlDetailActivity extends Activity{
	

	private MobileServiceClient mClient;
	private ArrayAdapter<PubItem> pubAdapter;
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.crawldetail);
		
	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(CrawlFragment.ITEM);

	    // Create the text view
//	    TextView textView = new TextView(this);
//	    textView.setTextSize(40);
//	    textView.setText(message);
//
//	    // Set the text view as the activity layout
//	    setContentView(textView);
	    
	    String crawlId = getIntent().getStringExtra("CRAWLID");
	    
		// Create the Mobile Service Client instance, using the provided
		// Mobile Service URL and key
		try {
			mClient = new MobileServiceClient(
					getResources().getString(R.string.azureUrl),
					getResources().getString(R.string.azureKey),
					this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		pubAdapter = new ArrayAdapter<PubItem>(this, android.R.layout.simple_list_item_1);
		
		ListView pubList = (ListView) findViewById(R.id.pubList);
		pubList.setAdapter(pubAdapter);
		
		for(String pubId : loadPubCrawls(crawlId)) {
			
			MobileServiceTable<PubItem> pubTable = mClient.getTable(PubItem.class);
			pubTable.where().field("id").eq(pubId).execute(new TableQueryCallback<PubItem>() {
				@Override
				public void onCompleted(List<PubItem> arg0, int arg1, Exception arg2, ServiceFilterResponse arg3) {
					// TODO Auto-generated method stub
					for(PubItem pub : arg0) {
						pubAdapter.add(pub);
						Log.d("PUB", pub.getName());
					}
				}
			});
			
		}
		
		}
	
	private List<String> loadPubCrawls(String crawlId) {
		// Get the Mobile Service Table instance to use
		MobileServiceTable<PubCrawlItem> pubCrawlTable = mClient.getTable(PubCrawlItem.class);
		
		
		
		final List<String> pubPudIds = new ArrayList<String>(); 
		pubCrawlTable.where().field("crawlid").eq(val(crawlId)).execute(new TableQueryCallback<PubCrawlItem>() {
			
			@Override
			public void onCompleted(List<PubCrawlItem> result, int count, Exception ex,	ServiceFilterResponse serviceFilterResponse) {
				for(PubCrawlItem pubCrawl : result) {
					pubPudIds.add(pubCrawl.getPubId());
					Log.d("PUBCRAWL", pubCrawl.getPubId());
				
				MobileServiceTable<PubItem> pubTable = mClient.getTable(PubItem.class);
				pubTable.where().field("id").eq(pubCrawl.getPubId()).execute(new TableQueryCallback<PubItem>() {
					@Override
					public void onCompleted(List<PubItem> arg0, int arg1, Exception arg2, ServiceFilterResponse arg3) {
						// TODO Auto-generated method stub
						for(PubItem pub : arg0) {
							pubAdapter.add(pub);
							Log.d("PUB", pub.getName());
						}
					}
				});
				
				}
			}
		});
		
		return pubPudIds;
		
	}
	
	
	


}
