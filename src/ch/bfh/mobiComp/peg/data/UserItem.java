package ch.bfh.mobiComp.peg.data;


/**
 * Represents an item in a Crawl list
 */
public class UserItem {

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
	 * ToDoItem constructor
	 */
	public UserItem() {

	}

	/**
	 * Initializes a new ToDoItem
	 * 
	 * @param text
	 *            The item text
	 * @param id
	 *            The item id
	 */
	public UserItem(String id, String name ) {
		this.setId(id);
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

	@Override
	public boolean equals(Object o) {
		return o instanceof UserItem && ((UserItem) o).mId == mId;
	}
}
