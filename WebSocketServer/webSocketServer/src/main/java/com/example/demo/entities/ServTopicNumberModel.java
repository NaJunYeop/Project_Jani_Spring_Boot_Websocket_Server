package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="topic_number_entity")
public class ServTopicNumberModel {
	// 최초 DB 생성시 딱!!!! 무조건!!!! 한 번만 0으로 초기화 되어있어야 한다.
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="topic_number")
	private Integer topicNumber;
	
	public ServTopicNumberModel() {
		
	}
	
	public ServTopicNumberModel(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicNumber() {
		return topicNumber;
	}

	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}

	@Override
	public String toString() {
		return "ServTopicNumberModel [id=" + id + ", topicNumber=" + topicNumber + "]";
	}
	
	
}
