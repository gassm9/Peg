package ch.bfh.mobiComp.peg.data;


/**
 * Represents an item in a Crawl list
 */
public class ReviewItem {

	/**
	 * Item Id
	 */
	@com.google.gson.annotations.SerializedName("id")
	private String mId;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("text")
	private String mText;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("rating")
	private int mRating;

	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("pubid")
	private String mPubId;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("userid")
	private String mUserId;


	/**
	 * ToDoItem constructor
	 */
	public ReviewItem() {

	}

	/**
	 * Initializes a new ToDoItem
	 * 
	 * @param text
	 *            The item text
	 * @param id
	 *            The item id
	 */
	public ReviewItem(String id, String text, int rating, String pubid, String userid ) {
		this.setId(id);
		this.setText(text);
		this.setRating(rating);
		this.setPubId(pubid);
		this.setUserId(userid);
	}

	/**
	 * Returns the item text
	 */
	public String getText() {
		return mText;
	}
	
	/**
	 * Sets the item text
	 * 
	 * @param text
	 *            text to set
	 */
	public final void setText(String text) {
		mText = text;
	}

	/**
	 * Sets the item text
	 * 
	 * @param text
	 *            text to set
	 */
	public final void setRating(int rating) {
		mRating = rating;
	}
	
	/**
	 * Returns the item text
	 */
	public int getRating() {
		return mRating;
	}
	
	/**
	 * Returns the item text
	 */
	public String getUserId() {
		return mUserId;
	}
	
	/**
	 * Sets the item text
	 * 
	 * @param text
	 *            text to set
	 */
	public final void setUserId(String userid) {
		mUserId = userid;
	}

	/**
	 * Returns the item text
	 */
	public String getPubId() {
		return mPubId;
	}
	
	/**
	 * Sets the item text
	 * 
	 * @param text
	 *            text to set
	 */
	public final void setPubId(String pubid) {
		mPubId = pubid;
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

	@Override
	public boolean equals(Object o) {
		return o instanceof ReviewItem && ((ReviewItem) o).mId == mId;
	}
}
