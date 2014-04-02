package ch.bfh.mobiComp.peg;

import ch.bfh.mobiComp.peg.R;
import ch.bfh.mobiComp.peg.R.id;
import ch.bfh.mobiComp.peg.R.layout;
import ch.bfh.mobiComp.peg.R.string;
import ch.bfh.mobiComp.peg.adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class CrawlTabActivity extends FragmentActivity implements
ActionBar.TabListener {
	 
private ViewPager viewPager;
private TabsPagerAdapter mAdapter;
private ActionBar actionBar;

	
	private static final String TAB_KEY_INDEX = "tab_key";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
//        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.ui_tabname_crawl))
                    .setTabListener(this));
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.ui_tabname_mycrawl))
                    .setTabListener(this));
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.ui_tabname_map))
                    .setTabListener(this));
            actionBar.addTab(actionBar.newTab().setText(getString(R.string.ui_tabname_docrawl))
                    .setTabListener(this));


		// restore to navigation
		if (savedInstanceState != null) {

			actionBar.setSelectedNavigationItem(savedInstanceState.getInt(
					TAB_KEY_INDEX, 0));
		}


		
		/**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
}
