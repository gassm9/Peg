package ch.bfh.mobiComp.peg;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.peg.R;

public class CrawlFragment extends Fragment {
	public final static String ITEM = "ch.bfh.mobiComp.peg.ITEM";
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.crawl, container, false);

		// get List of Pubcrawls
		loadContent();

		final ListView listview = (ListView) view.findViewById(R.id.listView);

		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				inflater.getContext(), android.R.layout.simple_list_item_1,
				mValues);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {  
			   
			@Override 
            public void onItemClick(AdapterView<?> parent, View view,  
                    int position, long id) {    
                
                String item = (String) adapter.getItem(position);

                Intent intent = new Intent(getActivity(), CrawlDetailActivity.class);
                intent.putExtra("ITEM", item);
                startActivity(intent);


                   
                   
            }  
        });  


		inputSearch = (EditText) view.findViewById(R.id.inputSearch);
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// Do nothing
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Do nothing
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				adapter.getFilter().filter(s);
			}

		});
		return view;
	}

}
