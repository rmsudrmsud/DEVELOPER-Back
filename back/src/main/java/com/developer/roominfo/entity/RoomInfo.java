package com.developer.roominfo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.developer.reservation.entity.Reservation;
import com.developer.studyroom.entity.Studyroom;

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
@DynamicUpdate()

@SequenceGenerator(
name =
"ROOM_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"ROOM_SEQ", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1
)
public class RoomInfo {

	@Id
	@Column(name="room_seq")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ROOM_SEQ_GENERATOR" // 위의 sequence 이름
			)
	private long roomSeq;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="info", nullable = false)
	private String info;
	
	@Column(name="img_path", nullable = false)
	private String imgPath;
	
	@Column(name="person", nullable = false)
	private Integer person;
	
	@Column(name="price", nullable = false)
	private Integer price;

	
	@ManyToOne//(cascade= {CascadeType.MERGE})
	@JoinColumn(name="sr_seq", nullable = false)
	private Studyroom studyroom;
	
	
	@OneToMany(mappedBy = "roominfo")
	private List<Reservation> reservation;

}
