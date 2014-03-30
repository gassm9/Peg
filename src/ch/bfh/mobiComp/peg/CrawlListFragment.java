package ch.bfh.mobiComp.peg;


import java.net.MalformedURLException;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ch.bfh.mobiComp.peg.data.CrawlItem;

import com.example.peg.R;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

public class CrawlListFragment extends ListFragment {

	ArrayAdapter<String> adapter;
	private MobileServiceClient mClient;
	private MobileServiceTable<CrawlItem> mCrawlTable;
	private CrawlAdapter mAdapter;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		try {
			// Create the Mobile Service Client instance, using the provided
			// Mobile Service URL and key
			mClient = new MobileServiceClient(
					getResources().getString(R.string.azureUrl),
					getResources().getString(R.string.azureKey),
					this.getActivity());

			// Get the Mobile Service Table instance to use
			mCrawlTable= mClient.getTable(CrawlItem.class);

			mAdapter = new CrawlAdapter(this.getActivity(), android.R.layout.simple_list_item_1);
			setListAdapter(mAdapter);
		
			// Load the items from the Mobile Service
			refreshItemsFromTable();

		} catch (MalformedURLException e) {
//			createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
		}
		
		
	
	}
	
	/**
	 * Refresh the list with the items in the Mobile Service Table
	 */
	private void refreshItemsFromTable() {

		// Get the items that weren't marked as completed and add them in the
		// adapter
		mCrawlTable.where().execute(new TableQueryCallback<CrawlItem>() {

			public void onCompleted(List<CrawlItem> result, int count, Exception exception, ServiceFilterResponse response) {
				if (exception == null) {
					mAdapter.clear();

					for (CrawlItem item : result) {
						mAdapter.add(item);
					}

				} else {
//					createAndShowDialog(exception, "Error");
				}
			}
		});
	}



	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		CrawlItem crawlItem = mAdapter.getItem(position);
		CrawlDetailFragment crawlDetailFragment = (CrawlDetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
		
		if (crawlDetailFragment != null && crawlDetailFragment.isInLayout()) {
			// use details fragment
			crawlDetailFragment.loadPubsForCrawl(crawlItem.getId());
			
		} else {
			// Start new Activity
			Intent intent = new Intent(getActivity(), CrawlDetailActivity.class);
			intent.putExtra("CRAWLID", crawlItem.getId());
			intent.putExtra("CRAWLNAME", crawlItem.getName());
			startActivity(intent);
			
		}
		


	}

}
