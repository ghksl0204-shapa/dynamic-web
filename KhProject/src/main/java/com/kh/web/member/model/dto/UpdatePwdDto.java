package com.kh.web.member.model.dto;

public class UpdatePwdDto {

	private String userPwd;
	private String updatePwd;
	private Long userNo;
	
	public UpdatePwdDto() {
		super();
	}
	public UpdatePwdDto(String userPwd, String updatePwd, Long userNo) {
		super();
		this.userPwd = userPwd;
		this.updatePwd = updatePwd;
		this.userNo = userNo;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUpdatePwd() {
		return updatePwd;
	}
	public void setUpdatePwd(String updatePwd) {
		this.updatePwd = updatePwd;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "UpdatePwdDto [userPwd=" + userPwd + ", updatePwd=" + updatePwd + ", userNo=" + userNo + "]";
	}
	
}
