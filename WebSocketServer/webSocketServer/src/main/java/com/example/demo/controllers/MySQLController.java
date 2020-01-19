package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.demo.models.ChatRoomModel;
import com.example.demo.models.FriendModel;
import com.example.demo.models.MessageModel;
import com.example.demo.models.RegisterModel;
import com.example.demo.models.RequestModel;
import com.example.demo.models.UserInformationEntity;
import com.example.demo.repositories.ChatRoomModelRepository;
import com.example.demo.repositories.FriendModelRepository;
import com.example.demo.repositories.MessageModelRepository;
import com.example.demo.repositories.RequestModelRepository;
import com.example.demo.repositories.UserInformationRepository;
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
	private UserInformationRepository userInformationRepository;
	
	@Autowired
	private MessageModelRepository messageModelRepository;
	
	@Autowired
	private RequestModelRepository requestModelRepository;
	
	@Autowired
	private FriendModelRepository friendModelRepository;
	
	@Autowired
	private ChatRoomModelRepository chatRoomModelRepository;
	
	
	private ObjectMapper mapper = new ObjectMapper();
	//private RegisterModel registerModel;
	
	private Optional<UserInformationEntity> queryUserInformation;
	private Optional<List<UserInformationEntity>> queryUserInformationList;
	
	private Optional<RequestModel> queryRequestModel;
	private Optional<List<RequestModel>> queryRequestModelList;
	
	private Optional<ChatRoomModel> queryChatRoomModel;
	private Optional<List<ChatRoomModel>> queryChatRoomModelList;
	
	private Optional<FriendModel> queryFriendModel;
	private Optional<List<FriendModel>> queryFriendModelList;
	
	private Optional<MessageModel> queryMessageModel;
	private Optional<List<MessageModel>> queryMessageModelList;
	
	
	/*
	 * @RequestMapping(value="/user-registration", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, String> userRegistration(@RequestBody
	 * RegisterModel registerModel) { Map<String, String> ret = new HashMap<>();
	 * 
	 * queryUserInformation =
	 * userInformationRepository.findByUserName(registerModel.getUserName());
	 * 
	 * if (!queryUserInformation.isPresent()) { UserInformationEntity userInfo = new
	 * UserInformationEntity.Builder(registerModel.getUserName()) .build();
	 * userInformationRepository.save(userInfo);
	 * logger.info(registerModel.toString()); ret.put("userName",
	 * registerModel.getUserName()); ret.put("phoneNumber",
	 * registerModel.getPhoneNumber()); ret.put("email", registerModel.getEmail());
	 * } else { logger.info("Account Already Exists....\n"); ret.put("userName",
	 * "EXT"); } return ret; }
	 */
	
	@RequestMapping(value="/user-registration", method=RequestMethod.POST)
	@ResponseBody
	public RegisterModel userRegistration(@RequestBody RegisterModel registerModel) {
		RegisterModel ret = registerModel;
		
		
		queryUserInformation = userInformationRepository.findByUserName(registerModel.getUserName());
		
		if (!queryUserInformation.isPresent()) {
			UserInformationEntity userInfo = new UserInformationEntity.Builder(registerModel.getUserName())
					.build();
			userInformationRepository.save(userInfo);
			logger.info(registerModel.toString());
		}
		else {
			logger.info("Account Already Exists....\n");
			ret.setUserName("EXT");
		}
		return ret;
	}
	
	
	@RequestMapping(value="/find-user", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> findUser(@RequestBody RegisterModel registerModel) {
		Map<String, String> ret = new HashMap<>();
		
		queryUserInformation = userInformationRepository.findByUserName(registerModel.getUserName());
		
		if (queryUserInformation.isPresent()) {
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
	
	@RequestMapping(value="/get-request-model", method=RequestMethod.POST)
	@ResponseBody // Automatically make JSON
	public List<Map<String, String>> getRequestModel(@RequestBody String userName) {
		
		logger.info("/get-request-model [ userName = " + userName + " ]");
		
		List<Map<String, String>> ret = new ArrayList<>();
		
		queryRequestModelList = requestModelRepository.findByReceiverName(userName);
		
		if (queryRequestModelList.isPresent()) {
			
			List<RequestModel> result = queryRequestModelList.get();
			
			for (RequestModel rm : result) {
				Map<String, String> tmp = new HashMap<>();
				tmp.put("senderName", rm.getSenderName());
				tmp.put("receiverName", rm.getReceiverName());
				tmp.put("status", rm.getStatus());
				ret.add(tmp);
			}
		}
		else {
			Map<String, String> tmp = new HashMap<>();
			tmp.put("senderName", "NOTEXIST");
			tmp.put("receiverName", "");
			tmp.put("status", "");
			ret.add(tmp);
		}
		
		logger.info(ret.toString());
		
		return ret;
		
	}
	
	@RequestMapping(value="/get-friend-model", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getFriendModel(@RequestBody String userName) {
		
		logger.info("/get-friend-model [ userName = " + userName + " ]");
		
		List<Map<String, String>> ret = new ArrayList<>();
		
		queryFriendModelList = friendModelRepository.findByUserName(userName);
		
		if (queryFriendModelList.isPresent()) {
			
			Map<String, String> tmp;
			List<FriendModel> result = queryFriendModelList.get();
			
			for (FriendModel fm : result) {
				tmp = new HashMap<>();
				tmp.put("FriendName", fm.getFriendName());
				ret.add(tmp);
			}
		}
		else {
			Map<String, String> tmp = new HashMap<>();
			tmp.put("friendName", "NOTEXIST");
			ret.add(tmp);
		}
		
		return ret;
	}
	
	@RequestMapping(value="/get-chat-room-model", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getChatRoomModel(@RequestBody String userName) {
		
		logger.info("/get-chat-room-model [ userName = " + userName + " ]");
		
		List<Map<String, String>> ret = new ArrayList<>();
		
		queryChatRoomModelList = chatRoomModelRepository.findByUserName(userName);
		
		if (queryChatRoomModelList.isPresent()) {
			Map<String, String> tmp;
			List<ChatRoomModel> result = queryChatRoomModelList.get();
			
			for (ChatRoomModel crm : result) {
				tmp = new HashMap<>();
				tmp.put("senderChatChannel", crm.getSenderChatChannel());
				tmp.put("chatRoomName", crm.getChatRoomName());
				ret.add(tmp);
			}
		}
		else {
			Map<String, String> tmp = new HashMap<>();
			tmp.put("senderChatChannel", "NOTEXIST");
			tmp.put("chatRoomName", "");
			tmp.put("type", "");
			tmp.put("participants", "");
			tmp.put("messageModels", "");
			ret.add(tmp);
		}
		
		return ret;
	}
	
	@RequestMapping(value="/get-message-model", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getMessageModel(@RequestBody String senderChatChannel) {

		logger.info("/get-message-model [ senderChatChannel = " + senderChatChannel + " ]");
		
		List<Map<String, String>> ret = new ArrayList<>();
		
		queryMessageModelList = messageModelRepository.findBySenderChatChannel(senderChatChannel);
		
		if (queryMessageModelList.isPresent()) {
			Map<String, String> tmp;
			List<MessageModel> result = queryMessageModelList.get();
			
			for (MessageModel mm : result) {
				tmp = new HashMap<>();
				tmp.put("senderChatChannel", mm.getSenderChatChannel());
				tmp.put("senderName", mm.getSenderName());
				tmp.put("senderSideDate", mm.getSenderSideDate());
				tmp.put("contents", mm.getContents());
				ret.add(tmp);
			}
		}
		else {
			Map<String, String> tmp = new HashMap<>();
			tmp.put("senderChatChannel", "NOTEXIST");
			tmp.put("senderName", "");
			tmp.put("senderSideDate", "");
			tmp.put("participants", "");
			tmp.put("messageModels", "");
			ret.add(tmp);
		}
		
		return ret;
	}
}
