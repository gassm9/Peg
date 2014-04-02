package ch.bfh.mobiComp.peg.test.espresso;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.swipeLeft;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.swipeRight;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.*;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import ch.bfh.mobiComp.peg.CrawlTabActivity;
import ch.bfh.mobiComp.peg.R;



import com.google.android.apps.common.testing.ui.espresso.ViewInteraction;
import com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions;
/**
 * Demonstrates use of {@link ViewActions#swipeLeft()} and {@link ViewActions#swipeRight()}.
 */
@LargeTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<CrawlTabActivity> {

	  @SuppressWarnings("deprecation")
	  public MainActivityTest() {
	    // This constructor was deprecated - but we want to support lower API levels.
	    super("ch.bfh.mobiComp.peg", CrawlTabActivity.class);
	  }

	  @Override
	  protected void setUp() throws Exception {
	    super.setUp();
	    getActivity();
	  }



//extends android.test.ActivityInstrumentationTestCase2
//{
//	public MainActivityTest()
//	{
//		super(TabActivity.class);
//	}
//
//	// The standard JUnit 3 setUp method run for for every test
//	@Override
//	protected void setUp() throws Exception
//	{
//		super.setUp();
//		getActivity(); // prevent error "No activities found. Did you forget to launch the activity by calling getActivity()"
//	}

	//It uses JUnit 3 so remember to prefix your test methods
	//with the word "test"
	public void testCrawlListClicked()
	{
//	  // Find
//	  ViewInteraction item1 = onView(withId(R.id.listView)); // Find the button
//	  
//	  // Action
//	  item1.perform(click()); // Click the button
//	  onData(allOf(is(instanceOf(String.class)), is("TorTour"))).perform(click());
//	  
//	  // Check
//	  onView(withId(R.id.tv_crawlName))
//	  .check(ViewAssertions.matches(withText(containsString("TorTour"))));
	}
	
	  public void testSwipingBackAndForward() {
		    // Should be on position 0 to start with.
		    onView(withText("Position #0")).check(ViewAssertions.matches(isDisplayed()));
		    
		    // Swipe left once.
		    onView(withId(R.id.pager)).perform(swipeLeft());
		    
		    // Now position 1 should be visible.
		    onView(withText("Position #1")).check(ViewAssertions.matches(isDisplayed()));
		    
		    // Swipe back to the right.
		    onView(withId(R.id.pager)).perform(swipeRight());
		    
		    // Now position 0 should be visible again.
		    onView(withText("Position #0")).check(ViewAssertions.matches(isDisplayed()));

		    // Swipe right again.
		    onView(withId(R.id.pager)).perform(swipeRight());
		    
		    // Position 0 should still be visible as this is the first view in the pager.
		    onView(withText("Position #0")).check(ViewAssertions.matches(isDisplayed()));
		  }
	
}