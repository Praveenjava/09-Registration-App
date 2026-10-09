package in.praveenit.bindings;





import java.time.LocalDate;

import lombok.Data;
@Data
public class User {
	
	
    private Integer userId;
	
	
	private String userFname;
	
	private String userLname;
	
	private String userEmail; 
	
	private Integer userPhno;
	
	private LocalDate userDob;
	
	private String userGender;
	
	private Integer userCountry;
	
    private Integer userState;
    
	private Integer userCity;
	
	private String userPwd;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserPhno() {
		return userPhno;
	}

	public void setUserPhno(Integer userPhno) {
		this.userPhno = userPhno;
	}

	public LocalDate getUserDob() {
		return userDob;
	}

	public void setUserDob(LocalDate userDob) {
		this.userDob = userDob;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Integer getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(Integer userCountry) {
		this.userCountry = userCountry;
	}

	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}

	public Integer getUserCity() {
		return userCity;
	}

	public void setUserCity(Integer userCity) {
		this.userCity = userCity;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserAccStatus() {
		return userAccStatus;
	}

	public void setUserAccStatus(String userAccStatus) {
		this.userAccStatus = userAccStatus;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	private String userAccStatus;
	
    private LocalDate createdDate;
    
	private LocalDate updatedDate;
	

	

}
