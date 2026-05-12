package com.kh.web.board.model.dto;

public class BoardResponse {

	private BoardDto board;
	private AttachmentDto attachment;
	
	public BoardResponse() {
		super();
	}

	public BoardResponse(BoardDto board, AttachmentDto attachment) {
		super();
		this.board = board;
		this.attachment = attachment;
	}

	public BoardDto getBoard() {
		return board;
	}

	public void setBoard(BoardDto board) {
		this.board = board;
	}

	public AttachmentDto getAttachment() {
		return attachment;
	}

	public void setAttachment(AttachmentDto attachment) {
		this.attachment = attachment;
	}

	@Override
	public String toString() {
		return "BoardResponse [board=" + board + ", attachment=" + attachment + "]";
	}
	
}
