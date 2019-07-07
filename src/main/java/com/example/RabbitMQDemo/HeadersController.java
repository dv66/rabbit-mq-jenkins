package com.example.RabbitMQDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.example.entity.Person;

@RestController
@RequestMapping("/mytest/v1")
public class HeadersController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	
	
	@GetMapping("/header/{name}")
	public String getTheApi(@PathVariable("name") String name) throws IOException {
		
		Person p = new Person(1L,name);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(p);
		out.flush();
		out.close();
		
		byte[] byteArray = bos.toByteArray();
		bos.close();
		
		Message message = (Message) MessageBuilder.withBody(byteArray)
				.setHeader("item1", "antu")
				.setHeader("item2", "apu")
				.build();
		
		rabbitTemplate.send("Headers-Exchange", "", message);
		
		
		return "Success";		
		
	}
	
	

}
