package com.manhnd.bookservice.response;

import lombok.Data;

@Data
public class ResultResponse {
	private String code;
	private String message;
	private Integer total;
	private Object data;
}
