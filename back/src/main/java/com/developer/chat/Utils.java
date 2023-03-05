package com.developer.chat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	private static final ObjectMapper objectMapepr = new ObjectMapper();
	
	private Utils() {
		
	}
	
	public static Message getObject(final String message) throws Exception{
		return objectMapepr.readValue(message, Message.class);
	}
	
	public static String getString(final Message message) throws Exception{
		return objectMapepr.writeValueAsString(message);
	}

}
