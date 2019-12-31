package com.example.demo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

public interface MessageModelRepository extends CrudRepository<MessageModel, Integer>{
	
	@Nullable
	public Optional<List<MessageModel>> findBySenderChatChannel(String userName);

}
