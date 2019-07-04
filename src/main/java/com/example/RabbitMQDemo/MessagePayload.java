package com.example.RabbitMQDemo;

import java.util.List;

public class MessagePayload {
	public MessagePayload(String type, List<?> list) {
		super();
		this.type = type;
		this.payload = list;
	}
	
	public MessagePayload() {}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<?> getPayload() {
		return payload;
	}

	public void setPayload(List<?> list) {
		this.payload = list;
	}
	private String type;
	private List<?> payload;

}
