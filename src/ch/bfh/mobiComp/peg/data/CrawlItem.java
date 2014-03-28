package ch.bfh.mobiComp.peg.data;

import java.util.Date;

/**
 * Represents an item in a Crawl list
 */
public class CrawlItem {

	/**
	 * Item Id
	 */
	@com.google.gson.annotations.SerializedName("id")
	private String mId;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("name")
	private String mName;

	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("date")
	private Date mDate;


	/**
	 * ToDoItem constructor
	 */
	public CrawlItem() {

	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Initializes a new ToDoItem
	 * 
	 * @param text
	 *            The item text
	 * @param id
	 *            The item id
	 */
	public CrawlItem(String id, String name, Date date ) {
		this.setId(id);
		this.setDate(date);
		this.setName(name);
	}

	/**
	 * Returns the item text
	 */
	public String getName() {
		return mName;
	}

	/**
	 * Sets the item text
	 * 
	 * @param text
	 *            text to set
	 */
	public final void setName(String name) {
		mName = name;
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
	public Date getDate() {
		return mDate;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setDate(Date date) {
		mDate = date;
	}

	@Override
	public boolean equals(Object o) {
		return o instanceof CrawlItem && ((CrawlItem) o).mId == mId;
	}
}
