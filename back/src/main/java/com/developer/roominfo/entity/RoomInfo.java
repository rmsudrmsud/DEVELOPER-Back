package com.developer.roominfo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.developer.reservation.entity.Reservation;

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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "roomInfo")
	private List<Reservation> reservation;
	
	//private RoomReviewVO roomReviewVO;

}
