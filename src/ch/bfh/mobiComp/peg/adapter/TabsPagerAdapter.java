package ch.bfh.mobiComp.peg.adapter;

import ch.bfh.mobiComp.peg.fragment.CrawlFragment;
import ch.bfh.mobiComp.peg.fragment.DoCrawlFragment;
import ch.bfh.mobiComp.peg.fragment.MapFragment;
import ch.bfh.mobiComp.peg.fragment.MyCrawlFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Crawl fragment activity
            return new CrawlFragment();
        case 1:
            // MyCrawl fragment activity
            return new MyCrawlFragment();
        case 2:
            // Map fragment activity
            return new MapFragment();
	    case 3:
	        // DoCrawl fragment activity
	        return new DoCrawlFragment();
	    }
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 4;
    }
 
}
