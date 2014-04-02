package ch.bfh.mobiComp.peg.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import ch.bfh.mobiComp.peg.R;
import ch.bfh.mobiComp.peg.R.layout;
import ch.bfh.mobiComp.peg.adapter.CrawlAdapter;
import ch.bfh.mobiComp.peg.data.CrawlItem;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

//TODO: remove this class?
public class CrawlFragment extends Fragment {
	
	/**
	 * Mobile Service Client reference
	 */
	private MobileServiceClient mClient;

	/**
	 * Mobile Service Table used to access data
	 */
	private MobileServiceTable<CrawlItem> mCrawlTable;
	
	/**
	 * Adapter to sync the items list with the view
	 */
	private CrawlAdapter mAdapter;


	public final static String ITEM = "ch.bfh.mobiComp.peg.ITEM";
	
	private List<String> mValues = new ArrayList<String>();
	
	ArrayAdapter<String> adapter;

	// Search EditText
	private EditText inputSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.crawl, container, false);

//		try {
//			// Create the Mobile Service Client instance, using the provided
//			// Mobile Service URL and key
//			mClient = new MobileServiceClient(
//					getResources().getString(R.string.azureUrl),
//					getResources().getString(R.string.azureKey),
//					this.getActivity());
//
//			// Get the Mobile Service Table instance to use
//			mCrawlTable= mClient.getTable(CrawlItem.class);
//
//			// Create an adapter to bind the items with the view
//			mAdapter = new CrawlAdapter(this.getActivity(), android.R.layout.simple_list_item_1);
//			ListView listCrawl = (ListView) view.findViewById(R.id.pubList);
//			listCrawl.setOnItemClickListener(new OnItemClickListener() {  
//				   
//				@Override 
//	            public void onItemClick(AdapterView<?> parent, View view,  
//	                    int position, long id) {    
//	                
//	                CrawlItem item = mAdapter.getItem(position);
//
//	                Intent intent = new Intent(getActivity(), CrawlDetailActivity.class);
//	                intent.putExtra("CRAWLID", item.getId());
//	                startActivity(intent);
//
//	            }  
//	        });  
//			listCrawl.setAdapter(mAdapter);
//		
//			// Load the items from the Mobile Service
//			refreshItemsFromTable();
//
//		} catch (MalformedURLException e) {
//			createAndShowDialog(new Exception("There was an error creating the Mobile Service. Verify the URL"), "Error");
//		}
//		

//		inputSearch = (EditText) view.findViewById(R.id.inputSearch);
//		inputSearch.addTextChangedListener(new TextWatcher() {
//
//			@Override
//			public void afterTextChanged(Editable arg0) {
//				// Do nothing
//			}
//
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,
//					int after) {
//				// Do nothing
//			}
//
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//				adapter.getFilter().filter(s);
//			}
//
//		});
		
		return view;
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
					createAndShowDialog(exception, "Error");
				}
			}
		});
	}
	

	/**
	 * Creates a dialog and shows it
	 * 
	 * @param exception
	 *            The exception to show in the dialog
	 * @param title
	 *            The dialog title
	 */
	private void createAndShowDialog(Exception exception, String title) {
		Throwable ex = exception;
		if(exception.getCause() != null){
			ex = exception.getCause();
		}
		createAndShowDialog(ex.getMessage(), title);
	}
	

	/**
	 * Creates a dialog and shows it
	 * 
	 * @param message
	 *            The dialog message
	 * @param title
	 *            The dialog title
	 */
	private void createAndShowDialog(String message, String title) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

		builder.setMessage(message);
		builder.setTitle(title);
		builder.create().show();
	}

}
