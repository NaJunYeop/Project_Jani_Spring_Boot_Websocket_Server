package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import com.example.demo.models.RequestModel;

public interface RequestModelRepository extends CrudRepository<RequestModel, Integer> {

	@Nullable
	public Optional<List<RequestModel>> findByReceiverName(String receiverName);
	
	@Query(value="INSERT INTO request_model (sender_name, receiver_name, status) VALUE (senderName, receiverName, status)", nativeQuery=true)
	public void insertRequestModel(String senderName, String receiverName, String status);
	
	@Nullable
	public void deleteBysenderNameAndReceiverName(String senderName, String receiverName);
	
}
