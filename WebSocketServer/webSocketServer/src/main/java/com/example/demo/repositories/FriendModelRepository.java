package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import com.example.demo.models.FriendModel;

public interface FriendModelRepository extends CrudRepository<FriendModel, Integer> {

	@Nullable
	public Optional<List<FriendModel>> findByUserName(String userName);

}
