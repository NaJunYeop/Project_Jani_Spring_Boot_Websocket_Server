package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.ServRegisterModel;
import com.example.demo.entities.ServTopicNumberModel;
import com.example.demo.models.ChatRoomModel;
import com.example.demo.models.FriendModel;
import com.example.demo.models.MessageModel;
import com.example.demo.models.PlainTextModel;
import com.example.demo.models.RegisterModel;
import com.example.demo.models.RequestModel;
import com.example.demo.models.UserInformationEntity;
import com.example.demo.models.UserInformationModel;
import com.example.demo.repositories.ChatRoomModelRepository;
import com.example.demo.repositories.FriendModelRepository;
import com.example.demo.repositories.MessageModelRepository;
import com.example.demo.repositories.ServRegisterModelRepository;
import com.example.demo.repositories.ServTopicNumberRepository;
import com.example.demo.repositories.UserInformationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/")
public class MySQLController {
	private static Logger logger = LoggerFactory.getLogger(MySQLController.class);
	
	@Autowired
	private ServRegisterModelRepository servRegisterModelRepository;
	
	
	///////////////topic_number 처리 YJ
	@Autowired
	private ServTopicNumberRepository servTopicNumberRepository;
	
	private Optional<ServTopicNumberModel> queryServTopicNumberModel;
	///////////////topic_number 처리 YJ
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Optional<ServRegisterModel> queryServRegisterModel;
	private Optional<List<ServRegisterModel>> queryServRegisterModelList;
	

	
	@RequestMapping(value="/user-registration", method=RequestMethod.POST)
	@ResponseBody
	public PlainTextModel userRegistration(@RequestBody RegisterModel registerModel) {
		logger.info("/user-registration : RegisterModel : User Name = " + registerModel.getRegUserName() + "Password = " + registerModel.getRegPassword());
		servRegisterModelRepository.save(new ServRegisterModel(registerModel));
		return new PlainTextModel("REG_DONE");
	}
	
	@RequestMapping(value="/duplication-check", method=RequestMethod.POST)
	@ResponseBody
	public PlainTextModel userNameDuplicationCheck(@RequestBody PlainTextModel text) {
		PlainTextModel ret = new PlainTextModel();
		queryServRegisterModel = servRegisterModelRepository.findByRegUserName(text.getText());
		
		logger.info("/duplication-check : User Name = " + text.getText());
		
		if (queryServRegisterModel.isPresent()) {
			logger.info("Duplicate User Name ...");
			ret.setText("DUP");
		}
		else {
			//ret = queryServRegisterModel.get().getRegUserName();
			logger.info("Can Make New User Account With Given User Name !!!");
			ret.setText("CHK");
		}
		return ret;
	}
	
	@RequestMapping(value="/find-user", method=RequestMethod.POST)
	@ResponseBody
	public PlainTextModel findUserInformationModel(@RequestBody PlainTextModel text) {
		PlainTextModel ret = new PlainTextModel();
		queryServRegisterModel = servRegisterModelRepository.findByRegUserName(text.getText());
		
		logger.info("/find-user : User Name = " + text.getText());
		
		if (queryServRegisterModel.isPresent()) {
			logger.info("Find the user whose name is " + queryServRegisterModel.get().getRegUserName());
			ret.setText("FOUND");
		}
		else {
			logger.info("There is no such user ...");
			ret.setText("NOT_EXIST");
		}
		return ret;
	}
	
	@RequestMapping(value="/get-user-info", method=RequestMethod.POST)
	@ResponseBody
	public UserInformationModel getUserInformationModel(@RequestBody PlainTextModel text) {
		queryServRegisterModel = servRegisterModelRepository.findByRegUserName(text.getText());
		
		logger.info("/get-user-info : User Name = " + text.getText());
		
		return new UserInformationModel(queryServRegisterModel.get());
	}
	
	
	
	///////////////topic_number 처리 YJ
	@RequestMapping(value="/get-topic-channel", method=RequestMethod.POST)
	@ResponseBody
	public PlainTextModel getTopicNumber() {
		
		PlainTextModel ret = new PlainTextModel();
		queryServTopicNumberModel = servTopicNumberRepository.findById(1);
		
		//topic_nubmer없으면 .save
		if(!queryServTopicNumberModel.isPresent()) {
			servTopicNumberRepository.save(new ServTopicNumberModel(1));
			ret.setText("0");
			logger.info("/get-topic-channel : clientTopicNumber = " + ret.getText()+ "/get-topic-channel : serverTopicNumber = 1");
			return ret;
		}
		
		//있으면 +1
		
		int clientTopicNumber = queryServTopicNumberModel.get().getTopicNumber();
		int serverTopicNumber = clientTopicNumber + 1;
		/*
		 * ServTopicNumberRepository.deleteAll(); ServTopicNumberRepository.save(new
		 * ServTopicNumberModel(serverTopicNumber));
		 */
		
		servTopicNumberRepository.updateTopicNumber(serverTopicNumber);
		
		//log찍어 보기
		logger.info("/get-topic-channel : clientTopicNumber = " + clientTopicNumber + "/get-topic-number : serverTopicNumber = " + serverTopicNumber);
		ret.setText(Integer.toString(clientTopicNumber));
		return ret;
	}
	///////////////topic_number 처리 YJ
	
	
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
	
	
	
	
	
	/*
	 * @RequestMapping(value="/user-registration", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public RegisterModel userRegistration(@RequestBody
	 * RegisterModel registerModel) { RegisterModel ret = registerModel;
	 * 
	 * queryUserInformation =
	 * userInformationRepository.findByUserName(registerModel.getUserName());
	 * 
	 * if (!queryUserInformation.isPresent()) { UserInformationEntity userInfo = new
	 * UserInformationEntity.Builder(registerModel.getUserName()) .build();
	 * userInformationRepository.save(userInfo);
	 * logger.info(registerModel.toString()); } else {
	 * logger.info("Account Already Exists....\n"); ret.setUserName("EXT"); } return
	 * ret; }
	 */
	
	
	/*
	 * @RequestMapping(value="/find-user", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, String> findUser(@RequestBody RegisterModel
	 * registerModel) { Map<String, String> ret = new HashMap<>();
	 * 
	 * queryUserInformation =
	 * userInformationRepository.findByUserName(registerModel.getUserName());
	 * 
	 * if (queryUserInformation.isPresent()) { UserInformationEntity userInfo = new
	 * UserInformationEntity.Builder(registerModel.getUserName()) .build();
	 * logger.info(registerModel.toString()); ret.put("userName",
	 * registerModel.getUserName()); ret.put("phoneNumber",
	 * registerModel.getPhoneNumber()); ret.put("email", registerModel.getEmail());
	 * } else { logger.info("Account Already Exists....\n"); ret.put("userName",
	 * "NOTEXIST"); } return ret; }
	 * 
	 * @RequestMapping(value="/get-request-model", method=RequestMethod.POST)
	 * 
	 * @ResponseBody // Automatically make JSON public List<Map<String, String>>
	 * getRequestModel(@RequestBody String userName) {
	 * 
	 * logger.info("/get-request-model [ userName = " + userName + " ]");
	 * 
	 * List<Map<String, String>> ret = new ArrayList<>();
	 * 
	 * queryRequestModelList = requestModelRepository.findByReceiverName(userName);
	 * 
	 * if (queryRequestModelList.isPresent()) {
	 * 
	 * List<RequestModel> result = queryRequestModelList.get();
	 * 
	 * for (RequestModel rm : result) { Map<String, String> tmp = new HashMap<>();
	 * tmp.put("senderName", rm.getSenderName()); tmp.put("receiverName",
	 * rm.getReceiverName()); tmp.put("status", rm.getStatus()); ret.add(tmp); } }
	 * else { Map<String, String> tmp = new HashMap<>(); tmp.put("senderName",
	 * "NOTEXIST"); tmp.put("receiverName", ""); tmp.put("status", "");
	 * ret.add(tmp); }
	 * 
	 * logger.info(ret.toString());
	 * 
	 * return ret;
	 * 
	 * }
	 * 
	 * @RequestMapping(value="/get-friend-model", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public List<Map<String, String>> getFriendModel(@RequestBody
	 * String userName) {
	 * 
	 * logger.info("/get-friend-model [ userName = " + userName + " ]");
	 * 
	 * List<Map<String, String>> ret = new ArrayList<>();
	 * 
	 * queryFriendModelList = friendModelRepository.findByUserName(userName);
	 * 
	 * if (queryFriendModelList.isPresent()) {
	 * 
	 * Map<String, String> tmp; List<FriendModel> result =
	 * queryFriendModelList.get();
	 * 
	 * for (FriendModel fm : result) { tmp = new HashMap<>(); tmp.put("FriendName",
	 * fm.getFriendName()); ret.add(tmp); } } else { Map<String, String> tmp = new
	 * HashMap<>(); tmp.put("friendName", "NOTEXIST"); ret.add(tmp); }
	 * 
	 * return ret; }
	 * 
	 * @RequestMapping(value="/get-chat-room-model", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public List<Map<String, String>> getChatRoomModel(@RequestBody
	 * String userName) {
	 * 
	 * logger.info("/get-chat-room-model [ userName = " + userName + " ]");
	 * 
	 * List<Map<String, String>> ret = new ArrayList<>();
	 * 
	 * queryChatRoomModelList = chatRoomModelRepository.findByUserName(userName);
	 * 
	 * if (queryChatRoomModelList.isPresent()) { Map<String, String> tmp;
	 * List<ChatRoomModel> result = queryChatRoomModelList.get();
	 * 
	 * for (ChatRoomModel crm : result) { tmp = new HashMap<>();
	 * tmp.put("senderChatChannel", crm.getSenderChatChannel());
	 * tmp.put("chatRoomName", crm.getChatRoomName()); ret.add(tmp); } } else {
	 * Map<String, String> tmp = new HashMap<>(); tmp.put("senderChatChannel",
	 * "NOTEXIST"); tmp.put("chatRoomName", ""); tmp.put("type", "");
	 * tmp.put("participants", ""); tmp.put("messageModels", ""); ret.add(tmp); }
	 * 
	 * return ret; }
	 * 
	 * @RequestMapping(value="/get-message-model", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public List<Map<String, String>> getMessageModel(@RequestBody
	 * String senderChatChannel) {
	 * 
	 * logger.info("/get-message-model [ senderChatChannel = " + senderChatChannel +
	 * " ]");
	 * 
	 * List<Map<String, String>> ret = new ArrayList<>();
	 * 
	 * queryMessageModelList =
	 * messageModelRepository.findBySenderChatChannel(senderChatChannel);
	 * 
	 * if (queryMessageModelList.isPresent()) { Map<String, String> tmp;
	 * List<MessageModel> result = queryMessageModelList.get();
	 * 
	 * for (MessageModel mm : result) { tmp = new HashMap<>();
	 * tmp.put("senderChatChannel", mm.getSenderChatChannel());
	 * tmp.put("senderName", mm.getSenderName()); tmp.put("senderSideDate",
	 * mm.getSenderSideDate()); tmp.put("contents", mm.getContents()); ret.add(tmp);
	 * } } else { Map<String, String> tmp = new HashMap<>();
	 * tmp.put("senderChatChannel", "NOTEXIST"); tmp.put("senderName", "");
	 * tmp.put("senderSideDate", ""); tmp.put("participants", "");
	 * tmp.put("messageModels", ""); ret.add(tmp); }
	 * 
	 * return ret; }
	 */
}
