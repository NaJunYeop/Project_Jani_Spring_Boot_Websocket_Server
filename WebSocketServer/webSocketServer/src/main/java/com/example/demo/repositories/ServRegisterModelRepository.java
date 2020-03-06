package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import com.example.demo.entities.ServRegisterModel;

public interface ServRegisterModelRepository extends CrudRepository<ServRegisterModel, Integer> {
	
	@Nullable
	public Optional<ServRegisterModel> findByRegUserName(String regUserName); 
}
