package ch.bfh.mobiComp.peg.data;

import java.util.Date;

/**
 * Represents an item in a Crawl list
 */
public class PubCrawlItem {

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
	@com.google.gson.annotations.SerializedName("pubid")
	private String mPubId;


	/**
	 * ToDoItem constructor
	 */
	public PubCrawlItem() {

	}

	/**
	 * Initializes a new ToDoItem
	 * 
	 * @param text
	 *            The item text
	 * @param id
	 *            The item id
	 */
	public PubCrawlItem(String id, String crawlid, String pubid ) {
		this.setId(id);
		this.setCrawlId(crawlid);
		this.setPubId(pubid);
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
	public String getPubId() {
		return mPubId;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setPubId(String pubid) {
		mPubId = pubid;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof PubCrawlItem && ((PubCrawlItem) o).mId == mId;
	}
}
