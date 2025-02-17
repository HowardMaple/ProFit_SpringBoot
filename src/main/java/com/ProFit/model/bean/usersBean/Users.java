package com.ProFit.model.bean.usersBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.DynamicUpdate;

import com.ProFit.model.bean.majorsBean.MajorBean;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@DynamicUpdate
@Table(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_passwordHash")
	private String userPasswordHash;

	@Column(name = "user_phoneNumber")
	private String userPhoneNumber;

	@Column(name = "user_city")
	private String userCity;

	@Column(name = "user_identity")
	private Integer userIdentity;

	@Column(name = "user_register_time", updatable = false, insertable = false)
	private String userRegisterTime;

	@Column(name = "user_pictureURL")
	private String userPictureURL;

	@Column(name = "user_balance")
	private Integer userBalance;

	@Column(name = "freelancer_location_prefer")
	private String freelancerLocationPrefer;

	@Column(name = "freelancer_exprience")
	private String freelancerExprience;

	@Column(name = "freelancer_identity")
	private String freelancerIdentity;

	@Column(name = "freelancer_profile_status")
	private Integer freelancerProfileStatus;

	@Column(name = "freelancer_description")
	private String freelancerDisc;
	
	@Column(name = "enabled")
	private Integer enabled;
	
	@Column(name = "verification_code")
	private String verificationCode;
	
	@Transient //不映射到資料庫table欄位
	private String loginTime;
	
	@JsonIgnore // 本屬性不要做 JSON 序列化
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Pwd_reset_tokens> pwd_reset_tokens = new LinkedList<Pwd_reset_tokens>();
	
	@JsonIgnore // 本屬性不要做 JSON 序列化
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Employer_application> employerApplications = new LinkedList<Employer_application>();
	
	@JsonIgnore // 本屬性不要做 JSON 序列化
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,mappedBy = "user")
	private Employer_profile employer_profile;

	 //多對多關係，中介表user_major
	@JsonIgnore // 本屬性不要做 JSON 序列化
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_major", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	@JoinColumn(name = "major_id") })
	private Set<MajorBean> majors = new LinkedHashSet<MajorBean>(0);

	public Users() {
	}

	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time,
			String user_pictureURL, Integer user_balance, String freelancer_location_prefer,
			String freelancer_exprience, String freelancer_identity, Integer freelancer_profile_status,
			String freelancer_disc, Integer enabled, String verification_code) {
		super();
		this.userId = user_id;
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userRegisterTime = user_register_time;
		this.userPictureURL = user_pictureURL;
		this.userBalance = user_balance;
		this.freelancerLocationPrefer = freelancer_location_prefer;
		this.freelancerExprience = freelancer_exprience;
		this.freelancerIdentity = freelancer_identity;
		this.freelancerProfileStatus = freelancer_profile_status;
		this.freelancerDisc = freelancer_disc;
		this.enabled = enabled;
		this.verificationCode = verification_code;
	}

	public Users(Integer user_id, String user_name, String user_email, String user_passwordHash,
			String user_phoneNumber, String user_city, Integer user_identity, String user_register_time,
			Integer user_balance, String freelancer_location_prefer, String freelancer_exprience,
			String freelancer_identity, Integer freelancer_profile_status, String freelancer_disc) {
		super();
		this.userId = user_id;
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userRegisterTime = user_register_time;
		this.userBalance = user_balance;
		this.freelancerLocationPrefer = freelancer_location_prefer;
		this.freelancerExprience = freelancer_exprience;
		this.freelancerIdentity = freelancer_identity;
		this.freelancerProfileStatus = freelancer_profile_status;
		this.freelancerDisc = freelancer_disc;
	}

	public Users(String user_name, String user_email, String user_passwordHash, String user_phoneNumber,
			String user_city, Integer user_identity, String user_register_time, Integer freelancer_profile_status) {
		super();
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userRegisterTime = user_register_time;
		this.freelancerProfileStatus = freelancer_profile_status;
	}

	public Users(String user_name, String user_email, String user_passwordHash, String user_phoneNumber,
			String user_city, Integer user_identity, Integer user_balance, Integer freelancer_profile_status,Integer enabled) {
		super();
		this.userName = user_name;
		this.userEmail = user_email;
		this.userPasswordHash = user_passwordHash;
		this.userPhoneNumber = user_phoneNumber;
		this.userCity = user_city;
		this.userIdentity = user_identity;
		this.userBalance = user_balance;
		this.freelancerProfileStatus = freelancer_profile_status;
		this.enabled = enabled;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPasswordHash() {
		return userPasswordHash;
	}

	public void setUserPasswordHash(String userPasswordHash) {
		this.userPasswordHash = userPasswordHash;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public Integer getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(Integer userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getUserRegisterTime() {
		return userRegisterTime;
	}

	public void setUserRegisterTime(String userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}

	public String getUserPictureURL() {
		return userPictureURL;
	}

	public void setUserPictureURL(String userPictureURL) {
		this.userPictureURL = userPictureURL;
	}

	public Integer getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(Integer userBalance) {
		this.userBalance = userBalance;
	}

	public String getFreelancerLocationPrefer() {
		return freelancerLocationPrefer;
	}

	public void setFreelancerLocationPrefer(String freelancerLocationPrefer) {
		this.freelancerLocationPrefer = freelancerLocationPrefer;
	}

	public String getFreelancerExprience() {
		return freelancerExprience;
	}

	public void setFreelancerExprience(String freelancerExprience) {
		this.freelancerExprience = freelancerExprience;
	}

	public String getFreelancerIdentity() {
		return freelancerIdentity;
	}

	public void setFreelancerIdentity(String freelancerIdentity) {
		this.freelancerIdentity = freelancerIdentity;
	}

	public Integer getFreelancerProfileStatus() {
		return freelancerProfileStatus;
	}

	public void setFreelancerProfileStatus(Integer freelancerProfileStatus) {
		this.freelancerProfileStatus = freelancerProfileStatus;
	}

	public String getFreelancerDisc() {
		return freelancerDisc;
	}

	public void setFreelancerDisc(String freelancerDisc) {
		this.freelancerDisc = freelancerDisc;
	}
	
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public List<Employer_application> getEmployerApplications() {
		return employerApplications;
	}
	
	public void setEmployerApplications(List<Employer_application> employerApplications) {
		this.employerApplications = employerApplications;
	}
	
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Set<MajorBean> getMajors() {
		return majors;
	}

	public void setMajors(Set<MajorBean> majors) {
		this.majors = majors;
	}
	
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String localDateTime) {
		this.loginTime = localDateTime;
	}
	
	public Employer_profile getEmployer_profile() {
		return employer_profile;
	}

	public void setEmployer_profile(Employer_profile employer_profile) {
		this.employer_profile = employer_profile;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userEmail=" + userEmail + ", userName=" + userName + ", userPasswordHash="
				+ userPasswordHash + ", userPhoneNumber=" + userPhoneNumber + ", userCity=" + userCity
				+ ", userIdentity=" + userIdentity + ", userRegisterTime=" + userRegisterTime + ", userPictureURL="
				+ userPictureURL + ", userBalance=" + userBalance + ", freelancerLocationPrefer="
				+ freelancerLocationPrefer + ", freelancerExprience=" + freelancerExprience + ", freelancerIdentity="
				+ freelancerIdentity + ", freelancerProfileStatus=" + freelancerProfileStatus + ", freelancerDisc="
				+ freelancerDisc + ", enabled=" + enabled + ", verificationCode=" + verificationCode + ", loginTime="
				+ loginTime + ", pwd_reset_tokens=" + pwd_reset_tokens + ", employerApplications="
				+ employerApplications + ", employer_profile=" + employer_profile + ", majors=" + majors + "]";
	}




}
