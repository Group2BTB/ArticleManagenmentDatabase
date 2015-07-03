package dto;
import java.sql.Date;

import dao.UserDAO;

	/*
	 * This class is used to store all information of the User
	 * and methods to set and get value of it.
	 */

public class User {

	/*
	 *@param id is used to store id of User
	 *@param fullName is used for store title of User
	 *@param email is used for store email of each User
	 *@param username is used for store username of each User
	 *@param password is used for store password of user
	 *@param createDate is used for store date when create users
	 *@param modifiedDate is used for store date when update users
	 *@param type is used for store type of users
	 *@param acitved is used for active or deactived users
	 *
	 */
	
	private int id;
	private String fullName;
	private String email;
	private String username;
	private StringBuilder password;
	private Date createDate;
	private Date modifiedDate;
	private boolean atived;
	private int parentId;
	private String type;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public StringBuilder getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new process.Validation().PassEncrypt(password);
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public boolean isAtived() {
		return atived;
	}
	public void setAtived(boolean atived) {
		this.atived = atived;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
