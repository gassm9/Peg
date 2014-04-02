package ch.bfh.mobiComp.peg.fragment;


import java.net.MalformedURLException;
import java.util.List;

import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.ListFragment;
import android.util.Log;
import ch.bfh.mobiComp.peg.R;
import ch.bfh.mobiComp.peg.R.string;
import ch.bfh.mobiComp.peg.adapter.CrawlAdapter;
import ch.bfh.mobiComp.peg.data.CrawlItem;
import ch.bfh.mobiComp.peg.data.UserCrawlItem;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;


public class MyCrawlFragment extends ListFragment {
	private MobileServiceClient mClient;
	private CrawlAdapter adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListShown(true);
		
		// Create the Mobile Service Client instance, using the provided
		// Mobile Service URL and key
		try {
			mClient = new MobileServiceClient(
					getResources().getString(R.string.azureUrl),
					getResources().getString(R.string.azureKey),
					this.getActivity());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adapter = new CrawlAdapter(this.getActivity(), android.R.layout.simple_list_item_1);
		this.setListAdapter(adapter);
		
		MobileServiceTable<UserCrawlItem> crawlTable = mClient.getTable(UserCrawlItem.class);
		
		String userid = Secure.getString(getActivity().getBaseContext().getContentResolver(),Secure.ANDROID_ID);
		
		crawlTable.where().field("userid").eq(userid).execute(new TableQueryCallback<UserCrawlItem>() {

			@Override
			public void onCompleted(List<UserCrawlItem> result, int arg1,	Exception ex, ServiceFilterResponse arg3) {
				for(UserCrawlItem res : result) {
					Log.d("adsf", res.getCrawlId());
					
					MobileServiceTable<CrawlItem> crawlTable = mClient.getTable(CrawlItem.class);
					crawlTable.where().field("id").eq(res.getCrawlId()).execute(new TableQueryCallback<CrawlItem>() {
						
						@Override
						public void onCompleted(List<CrawlItem> crawls, int arg1, Exception arg2, ServiceFilterResponse arg3) {
							// TODO Auto-generated method stub
							adapter.clear();
							
							for(CrawlItem item : crawls) {
								adapter.add(item);
							}
						}
					});
				}
				
			}
		
		});
		
	
	}
	
//	@Override
//	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.simple_list_item_1, container);
//		
//		return view;
//		
//		
//	}


}
