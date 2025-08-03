package model.entity;

public class Contact {

	private int id;
	private int contactItemId;
	private String item;
	private String name;
	private String prefecture;
	private String mail;
	private String message;
	
	public Contact(int id, int contactItem, String name, String prefecture, String mail, String message) {
		super();
		this.id = id;
		this.contactItemId = contactItem;
		this.name = name;
		this.prefecture = prefecture;
		this.mail = mail;
		this.message = message;
	}

	public Contact(int contactItem, String name, String prefecture, String mail, String message) {
		super();
		this.contactItemId = contactItem;
		this.name = name;
		this.prefecture = prefecture;
		this.mail = mail;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContactItem() {
		return contactItemId;
	}

	public void setContactItem(int contactItem) {
		this.contactItemId = contactItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getContactItemId() {
		return contactItemId;
	}

	public void setContactItemId(int contactItemId) {
		this.contactItemId = contactItemId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	
}
