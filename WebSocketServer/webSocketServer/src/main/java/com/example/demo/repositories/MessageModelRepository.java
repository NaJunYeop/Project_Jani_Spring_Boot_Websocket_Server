package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import com.example.demo.models.MessageModel;
import com.example.demo.models.ServerMessageModel;

public interface MessageModelRepository extends CrudRepository<ServerMessageModel, Integer>{
	
	/*
	 * @Nullable public Optional<List<MessageModel>> findBySenderChatChannel(String
	 * userName);
	 */

}
