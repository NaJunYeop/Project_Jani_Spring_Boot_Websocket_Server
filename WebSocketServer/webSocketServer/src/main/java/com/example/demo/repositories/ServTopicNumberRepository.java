package com.example.demo.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.ServTopicNumberModel;
import com.sun.istack.Nullable;

public interface ServTopicNumberRepository extends CrudRepository<ServTopicNumberModel, Integer>{

	@Nullable
	public Optional<ServTopicNumberModel> findById(Integer id);
	
	

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ServTopicNumberModel stnm SET stnm.topicNumber=:topicNumber WHERE stnm.id=1")
	public void updateTopicNumber(@Param("topicNumber") int topicNumber);
}
