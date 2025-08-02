package model.entity;

import java.time.LocalDateTime;

public class User {

	private int id;
    private String lName;
    private String fName;
    private String lNameKana;
    private String fNameKana;
    private String password;
    private String prefecture;
    private String city;
    private String oAddress;
    private String tel;
    private String email;
    private LocalDateTime createdAt;
    private int role;
    
	public User(int id, String lName, String fName, String lNameKana, String fNameKana, String password,
			String prefecture, String city, String oAddress, String tel, String email, LocalDateTime createdAt, int role) {
		super();
		this.id = id;
		this.lName = lName;
		this.fName = fName;
		this.lNameKana = lNameKana;
		this.fNameKana = fNameKana;
		this.password = password;
		this.prefecture = prefecture;
		this.city = city;
		this.oAddress = oAddress;
		this.tel = tel;
		this.email = email;
		this.createdAt = createdAt;
		this.role = role;
	}
	

	public User(String lName, String fName, String lNameKana, String fNameKana, String password, String prefecture,
			String city, String oAddress, String tel, String email) {
		super();
		this.lName = lName;
		this.fName = fName;
		this.lNameKana = lNameKana;
		this.fNameKana = fNameKana;
		this.password = password;
		this.prefecture = prefecture;
		this.city = city;
		this.oAddress = oAddress;
		this.tel = tel;
		this.email = email;
	}



	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlNameKana() {
		return lNameKana;
	}

	public void setlNameKana(String lNameKana) {
		this.lNameKana = lNameKana;
	}

	public String getfNameKana() {
		return fNameKana;
	}

	public void setfNameKana(String fNameKana) {
		this.fNameKana = fNameKana;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getoAddress() {
		return oAddress;
	}

	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
