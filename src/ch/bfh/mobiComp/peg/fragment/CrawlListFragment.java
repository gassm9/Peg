package ch.bfh.mobiComp.peg.fragment;


import java.net.MalformedURLException;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import ch.bfh.mobiComp.peg.CrawlDetailActivity;
import ch.bfh.mobiComp.peg.R;
import ch.bfh.mobiComp.peg.R.id;
import ch.bfh.mobiComp.peg.R.menu;
import ch.bfh.mobiComp.peg.R.string;
import ch.bfh.mobiComp.peg.adapter.CrawlAdapter;
import ch.bfh.mobiComp.peg.data.CrawlItem;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

public class CrawlListFragment extends ListFragment implements OnQueryTextListener {

	ArrayAdapter<String> adapter;
	private MobileServiceClient mClient;
	private MobileServiceTable<CrawlItem> mCrawlTable;
	private CrawlAdapter mAdapter;
	private String grid_currentQuery = null; // holds the current query...



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    inflater.inflate(R.menu.main, menu); 
	    SearchView searchView = (SearchView)menu.findItem(R.id.grid_default_search).getActionView();
	    searchView.setOnQueryTextListener(this);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//fragment must know that there are options in the menu
		setHasOptionsMenu(true);

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

	@Override
	public boolean onQueryTextChange(String newText) {
		if (TextUtils.isEmpty(newText)) {
            getActivity().getActionBar().setSubtitle("Pubcrawls");               
            grid_currentQuery = null;
        } else {
            getActivity().getActionBar().setSubtitle("Pubcrawls - Searching for: " + newText);
            grid_currentQuery = newText;

        }   
//        getLoaderManager().restartLoader(0, null, CrawlListFragment.this); 
        return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		Toast.makeText(getActivity(), "Searching for: " + query + "...", Toast.LENGTH_SHORT).show();
		if(query != ""){
		mAdapter.getFilter().filter(query);
		}
        return false;
	}
}
