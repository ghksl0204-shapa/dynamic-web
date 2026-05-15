package com.kh.web.ajax.model.dto;

import java.sql.Date;

public class ReplyDto {

	private Long replyNo;
	private Long refBno;
	private Long replyWriter;
	private String replyContent;
	private Date createDate;
	private String status;
	private String userName;
	
	public ReplyDto() {
		super();
	}

	public ReplyDto(Long replyNo, Long refBno, Long replyWriter, String replyContent, Date createDate, String status,
			String userName) {
		super();
		this.replyNo = replyNo;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.status = status;
		this.userName = userName;
	}

	public Long getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(Long replyNo) {
		this.replyNo = replyNo;
	}

	public Long getRefBno() {
		return refBno;
	}

	public void setRefBno(Long refBno) {
		this.refBno = refBno;
	}

	public Long getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(Long replyWriter) {
		this.replyWriter = replyWriter;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
		return "ReplyDto [replyNo=" + replyNo + ", refBno=" + refBno + ", replyWriter=" + replyWriter
				+ ", replyContent=" + replyContent + ", createDate=" + createDate + ", status=" + status + ", userName="
				+ userName + "]";
	}

}
