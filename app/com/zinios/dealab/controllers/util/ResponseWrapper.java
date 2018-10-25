package com.zinios.dealab.controllers.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ResponseWrapper<T> {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private String message;
	private Integer statusCode;
	private T data;

	public ResponseWrapper(String message, Integer statusCode, T data) {
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public JsonNode jsonSerialize() {
		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		objectMapper.setDateFormat(df);

		return objectMapper.convertValue(this, JsonNode.class);
	}
}