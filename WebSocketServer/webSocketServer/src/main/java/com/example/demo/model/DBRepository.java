package com.example.demo.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface DBRepository extends CrudRepository<UserInformation, Integer> {
	public List<UserInformation> findByNameAndPhoneNumber(String name, String phoneNumber);
}