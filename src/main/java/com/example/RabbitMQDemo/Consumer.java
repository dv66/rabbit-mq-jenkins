package com.example.RabbitMQDemo;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Consumer {
	
	
	@RabbitListener(queues= {"Queue-1"})
	public void getMessage(Message message) throws JsonParseException, JsonMappingException, IOException{
		
		JSONObject arrayObject;
		
		String str = new String(message.getBody());
		
		System.out.println("Received JSON is : " + str);
		
		JSONObject jsonObject = new JSONObject(str);
		
		String typeName = jsonObject.getString("type");
		JSONArray array = (JSONArray) jsonObject.get("payload");
		
		System.out.println("Type is : " + typeName);
		for(int i = 0; i < array.length();i++) {
			arrayObject = (JSONObject)array.get(i);
			System.out.println(arrayObject);
			
			Set<String> keys = arrayObject.keySet();
			for(String key:keys) {
				if(arrayObject.get(key) instanceof JSONObject) {
					JSONObject obj = (JSONObject)arrayObject.get(key);
					Set<String> attributes = obj.keySet();
					
					System.out.println(key + " :  ");
					
					for(String attribute:attributes) {
						
						System.out.println(" " + attribute + " : " + obj.get(attribute));
					}
					
				}else {
					
					System.out.println(key + " : "+ arrayObject.get(key));
				}
				
			}
			
		}
		
		
	}

}
