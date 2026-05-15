package com.kh.web.board.model.dto;

import java.util.List;

public class ImageResponse {

	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private List<AttachmentDto> files;
	
	public ImageResponse() {
		super();
	}

	public ImageResponse(Long boardNo, String boardTitle, String boardContent, List<AttachmentDto> files) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.files = files;
	}

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public List<AttachmentDto> getFiles() {
		return files;
	}

	public void setFiles(List<AttachmentDto> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "ImageResponse [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", files=" + files + "]";
	}
	
	
	
}
