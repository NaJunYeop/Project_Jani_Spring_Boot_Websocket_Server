package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
public interface UserInformationRepository extends CrudRepository<UserInformationEntity, Integer> {
	@Nullable
	public Optional<UserInformationEntity> findByUserNameAndPhoneNumber(String userName, String phoneNumber);
	
	@Nullable
	public Optional<UserInformationEntity> findByUserName(String userName);
}