package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.model.DBRepository;
import com.example.demo.model.RegisterModel;
import com.example.demo.model.UserInformationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/")
public class MySQLController {
	private static Logger logger = LoggerFactory.getLogger(MySQLController.class);
	
	@Autowired
	private DBRepository dbRepository;
	
	private ObjectMapper mapper = new ObjectMapper();
	//private RegisterModel registerModel;
	
	private Optional<UserInformationEntity> queryRegister;
	private List<UserInformationEntity> queryList;
	
	@RequestMapping(value="/user-registration", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> userRegistration(@RequestBody RegisterModel registerModel) {
		/*
		logger.info("this is my info = " + json);
		try {
			registerModel = mapper.readValue(json, RegisterModel.class);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Map<String, String> ret = new HashMap<>();
		
		queryRegister = dbRepository.findByUserName(registerModel.getUserName());
		
		if (!queryRegister.isPresent()) {
			UserInformationEntity userInfo = new UserInformationEntity.Builder(registerModel.getUserName())
					.build();
			dbRepository.save(userInfo);
			logger.info(registerModel.toString());
			ret.put("userName", registerModel.getUserName());
			ret.put("phoneNumber", registerModel.getPhoneNumber());
			ret.put("email", registerModel.getEmail());
		}
		else {
			logger.info("Account Already Exists....\n");
			ret.put("userName", "EXT");
		}
		return ret;
	}
	
	@RequestMapping(value="/find-user", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> findUser(@RequestBody RegisterModel registerModel) {
		Map<String, String> ret = new HashMap<>();
		
		queryRegister = dbRepository.findByUserName(registerModel.getUserName());
		
		if (queryRegister.isPresent()) {
			UserInformationEntity userInfo = new UserInformationEntity.Builder(registerModel.getUserName())
					.build();
			logger.info(registerModel.toString());
			ret.put("userName", registerModel.getUserName());
			ret.put("phoneNumber", registerModel.getPhoneNumber());
			ret.put("email", registerModel.getEmail());
		}
		else {
			logger.info("Account Already Exists....\n");
			ret.put("userName", "NOTEXIST");
		}
		return ret;
	}
}
