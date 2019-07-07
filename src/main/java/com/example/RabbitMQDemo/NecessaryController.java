package com.example.RabbitMQDemo;

//import com.example.entity.Person;Person

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
import org.springframework.amqp.core.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.amqp.core.MessageProperties;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
public class NecessaryController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping("/person/{name}")
	public Message getTheApi(@PathVariable("name") String name){
		
		Message message = null;
		
		List<Person> personList = new ArrayList<>();
		
		personList.add(new Person(1L,name));
		System.out.println("Got The Persons Request");
		MessagePayload payload = new MessagePayload("Person",personList);
		
		 try {
	            String orderJson = new ObjectMapper().writeValueAsString(payload);
	            message = (Message) MessageBuilder
	                                .withBody(orderJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	            this.rabbitTemplate.convertAndSend("Direct-Exchange","dipto"	, message);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		
		
		return message;
		
		/*Person p = new Person(1L,name);
		
		//rabbitTemplate.convertAndSend("Direct-Exchange", "antu",p);
		//rabbitTemplate.convertAndSend("Direct-Exchange", "antu", new ObjectMapper().writeValueAsString(p));
		 try {
	            String orderJson = new ObjectMapper().writeValueAsString(p);
	            message = (Message) MessageBuilder
	                                .withBody(orderJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	            this.rabbitTemplate.convertAndSend("Direct-Exchange","antu", message);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		
		return message;	*/	
		
	}
	
	@GetMapping("/details/{name}")
	public Message getTheDetails(@PathVariable("name") String name){
		System.out.println("Got The Person Detail Request");
		Message message = null;
		
		List<Person> personList = new ArrayList<>();
		
		personList.add(new Person(1L,name));
		MessagePayload payload = new MessagePayload("PersonDetails",personList);
		
		 try {
	            String orderJson = new ObjectMapper().writeValueAsString(payload);
	            message = (Message) MessageBuilder
	                                .withBody(orderJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	            this.rabbitTemplate.convertAndSend("Direct-Exchange","antu", message);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		
		
		return message;

		
	}
	
	@GetMapping("/address/{city}")
	public Message getTheAddress(@PathVariable("city") String city) {
		Message message = null;
		
		System.out.println("Got The Address Request");
		
		Address address = new Address(city,"Bangladesh");
		
		List<Address> addressList = new ArrayList<>();
		
		addressList.add(address);
		
		MessagePayload payload = new MessagePayload("Address",addressList);
		
		 try {
	            String orderJson = new ObjectMapper().writeValueAsString(payload);
	            message = (Message) MessageBuilder
	                                .withBody(orderJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	            this.rabbitTemplate.convertAndSend("Direct-Exchange","antu", message);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		
		
		return message;
		
		
	}
	
	@GetMapping("/persons")
	public Message getThePersons() {
		Message message = null;
		List<Person> personList = new ArrayList<>();
		
		personList.add(new Person(2L,"Monir"));
		personList.add(new Person(3L,"Mizan"));
		
		MessagePayload payload = new MessagePayload("PersonDetails",personList);
		
		 try {
	            String orderJson = new ObjectMapper().writeValueAsString(payload);
	            message = (Message) MessageBuilder
	                                .withBody(orderJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	            this.rabbitTemplate.convertAndSend("Direct-Exchange","antu", message);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		
		
		return message;		
		
	}
	
	

}
