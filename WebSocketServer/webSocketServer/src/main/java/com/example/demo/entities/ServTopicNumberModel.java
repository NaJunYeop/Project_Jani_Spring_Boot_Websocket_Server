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
	// ���� DB ������ ��!!!! ������!!!! �� ���� 0���� �ʱ�ȭ �Ǿ��־�� �Ѵ�.
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="topic_Number")
	private Integer topicNumber;

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

	public ServTopicNumberModel(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}

	@Override
	public String toString() {
		return "ServTopicNumberModel [id=" + id + ", topicNumber=" + topicNumber + "]";
	}
	
	
}
