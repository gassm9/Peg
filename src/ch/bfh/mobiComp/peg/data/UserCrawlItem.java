package ch.bfh.mobiComp.peg.data;


/**
 * Represents an item in a Crawl list
 */
public class UserCrawlItem {

	/**
	 * Item Id
	 */
	@com.google.gson.annotations.SerializedName("id")
	private String mId;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("crawlid")
	private String mCrawlId;

	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("userid")
	private String mUserId;


	/**
	 * ToDoItem constructor
	 */
	public UserCrawlItem() {

	}

	/**
	 * Initializes a new ToDoItem
	 * 
	 * @param text
	 *            The item text
	 * @param id
	 *            The item id
	 */
	public UserCrawlItem(String id, String crawlid, String userid ) {
		this.setId(id);
		this.setCrawlId(crawlid);
		this.setUserId(userid);
	}

	/**
	 * Returns the item text
	 */
	public String getCrawlId() {
		return mCrawlId;
	}

	/**
	 * Sets the item text
	 * 
	 * @param text
	 *            text to set
	 */
	public final void setCrawlId(String crawlid) {
		mCrawlId = crawlid;
	}

	/**
	 * Returns the item id
	 */
	public String getId() {
		return mId;
	}

	/**
	 * Sets the item id
	 * 
	 * @param id
	 *            id to set
	 */
	public final void setId(String id) {
		mId = id;
	}

	/**
	 * Indicates if the item is marked as completed
	 */
	public String getUserId() {
		return mUserId;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setUserId(String pubid) {
		mUserId = pubid;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof UserCrawlItem && ((UserCrawlItem) o).mId == mId;
	}
}
