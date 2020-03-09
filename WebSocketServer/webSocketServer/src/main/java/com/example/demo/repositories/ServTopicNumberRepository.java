package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.ServTopicNumberModel;
import com.sun.istack.Nullable;

public interface ServTopicNumberRepository extends CrudRepository<ServTopicNumberModel, Integer>{

	@Nullable
	public Optional<ServTopicNumberModel> findById(Integer id);
	
	
}
