package ch.bfh.mobiComp.peg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.peg.R;

public class CrawlListFragment extends ListFragment {

	ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//TODO: Replace with CrawlItem DB Entries
		
		String[] crawls = new String[] { "Pubcrawl1", "Pubcrawl2", "Pubcrawl3",
				"Pubcrawl4", "Pubcrawl5"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, crawls);
		
		setListAdapter(adapter);
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		String item = (String) getListAdapter().getItem(position);
		
		CrawlDetailFragment fragment = (CrawlDetailFragment) getFragmentManager()
				.findFragmentById(R.id.detailFragment);
		
		if (fragment != null && fragment.isInLayout()) {
			//TODO: The ID of the clicked CrawlItem must be notified to CrawlDetailFragment
			fragment.setText(item , R.id.tv_crawlName);
		} else {
			Intent intent = new Intent(getActivity().getApplicationContext(),
					CrawlDetailActivity.class);
			//TODO: The ID of the clicked CrawlItem must be notified to CrawlDetailActivity
			intent.putExtra("value", item);
			startActivity(intent);

		}

	}

}
