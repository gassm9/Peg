package ch.bfh.mobiComp.peg;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.peg.R;

public class CrawlListFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	private List<String> mValues = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	// Search EditText
	private EditText inputSearch;

	protected void loadContent() {
		for (int i = 0; i < 3; i++) {
			mValues.add("Pubcrawl " + String.valueOf(i + 1));
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) getListAdapter().getItem(position);
		CrawlDetailFragment fragment = (CrawlDetailFragment) getFragmentManager()
				.findFragmentById(R.id.detailFragment);
		if (fragment != null && fragment.isInLayout()) {
			fragment.setText(item);
		} else {
			Intent intent = new Intent(getActivity().getApplicationContext(),
					CrawlDetailFragment.class);
			intent.putExtra("value", item);
			startActivity(intent);

		}

	}

}
