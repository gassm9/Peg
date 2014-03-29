package ch.bfh.mobiComp.peg.data;


/**
 * Represents an item in a Crawl list
 */
public class PubItem {

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
	@com.google.gson.annotations.SerializedName("imgurl")
	private String mImgUrl;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("description")
	private String mDescription;
	
	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("longitude")
	private String mLongitude;

	/**
	 * Item text
	 */
	@com.google.gson.annotations.SerializedName("latitude")
	private String mLatitude;

	/**
	 * ToDoItem constructor
	 */
	public PubItem() {

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
	public PubItem(String id, String name, String imgurl, String description, String longitude, String latitude) {
		this.setId(id);
		this.setName(name);
		this.setImgUrl(imgurl);
		this.setDescription(description);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
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
	public String getImgUrl() {
		return mImgUrl;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setImgUrl(String imgurl) {
		mImgUrl = imgurl;
	}
	
	/**
	 * Indicates if the item is marked as completed
	 */
	public String getDescription() {
		return mDescription;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setDescription(String description) {
		mDescription = description;
	}
	
	/**
	 * Indicates if the item is marked as completed
	 */
	public String getLongitude() {
		return mLongitude;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setLongitude(String longitude) {
		mLongitude = longitude;
	}
	
	/**
	 * Indicates if the item is marked as completed
	 */
	public String getLatitude() {
		return mLatitude;
	}

	/**
	 * Marks the item as completed or incompleted
	 */
	public void setLatitude(String latitude) {
		mLatitude = latitude;
	}


	@Override
	public boolean equals(Object o) {
		return o instanceof PubItem && ((PubItem) o).mId == mId;
	}
}
