package com.kh.web.ajax.model.dto;

public class ResponseDto {

	private String code;
	private String message;
	private Object data;
	
	public ResponseDto(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	
	
}
