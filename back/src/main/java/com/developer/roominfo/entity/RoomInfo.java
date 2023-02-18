package com.developer.roominfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "room_info")
public class RoomInfo {

	@Id
	@Column(name="room_seq")
	private long roomSeq;
	
	@Column(name="sr_seq")
	private long srSeq;
	
	@Column(name="name")
	private String name;
	
	@Column(name="info")
	private String info;
	
	@Column(name="img_path")
	private String imgPath;
	
	@Column(name="person")
	private Integer person;
	
	@Column(name="price")
	private Integer price;
	
	
	//private RoomReviewVO roomReviewVO;

}
