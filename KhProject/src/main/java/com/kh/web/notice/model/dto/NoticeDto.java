package com.kh.web.notice.model.dto;

import java.sql.Date;

public class NoticeDto {

	private Long noticeNo;
	private Long userNo;
	private String noticeTitle;
	private String noticeContent;
	private Date createDate;
	private Date modifyDate;
	private int count;
	private String status;
	private String userName;
	
	public NoticeDto() {
		super();
	}
	
	public NoticeDto(Long userNo, String noticeTitle, String noticeContent, String userName) {
		super();
		this.userNo = userNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.userName = userName;
	}

	public NoticeDto(Long noticeNo, Long userNo, String noticeTitle, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.userNo = userNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	public NoticeDto(Long noticeNo, Long userNo, String noticeTitle, String noticeContent, Date createDate,
			Date modifyDate, int count, String status, String userName) {
		super();
		this.noticeNo = noticeNo;
		this.userNo = userNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.count = count;
		this.status = status;
		this.userName = userName;
	}

	public Long getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(Long noticeNo) {
		this.noticeNo = noticeNo;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "NoticeDto [noticeNo=" + noticeNo + ", userNo=" + userNo + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", count=" + count + ", status=" + status + ", userName=" + userName + "]";
	}
	
	
}
