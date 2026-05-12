package com.kh.web.board.model.dto;

import java.sql.Date;

public class AttachmentDto {

	private Long fileNo;
	private Long refBno;
	private String originName;
	private String changeName;
	private String filePath;
	private String boardType;
	private int fileLevel;
	private Date createDate;
	
	public AttachmentDto() {
		super();
	}

	public AttachmentDto(Long fileNo, Long refBno, String originName, String changeName, String filePath,
			String boardType, int fileLevel, Date createDate) {
		super();
		this.fileNo = fileNo;
		this.refBno = refBno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.boardType = boardType;
		this.fileLevel = fileLevel;
		this.createDate = createDate;
	}

	public Long getFileNo() {
		return fileNo;
	}

	public void setFileNo(Long fileNo) {
		this.fileNo = fileNo;
	}

	public Long getRefBno() {
		return refBno;
	}

	public void setRefBno(Long refBno) {
		this.refBno = refBno;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "AttachmentDto [fileNo=" + fileNo + ", refBno=" + refBno + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", boardType=" + boardType + ", fileLevel=" + fileLevel
				+ ", createDate=" + createDate + "]";
	}
	
	
}
