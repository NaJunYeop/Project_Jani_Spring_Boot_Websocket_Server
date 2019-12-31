package com.example.demo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

public interface ChatRoomModelRepository extends CrudRepository<ChatRoomModel, Integer> {
	
	@Nullable
	public Optional<List<ChatRoomModel>> findByUserName(String userName);

}
