package com.developer.hostuser.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
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
@Table(name = "host_user")
@DynamicInsert  //ColumnDefault때문에
@DynamicUpdate
public class HostUser { 
	
	@Id
	@Column(name="host_id")
	private String hostId;
	
	@Column(name="pwd", nullable = false)
	private String pwd;
	
	@Column(name="num", nullable = false)
	private String num;
	
	@Column(name="ready")
	@ColumnDefault(value = "0")
	private Integer ready;
	
	@Column(name="name" , nullable = false)
	private String name;
	
	@Column(name="tel", nullable = false)
	private String tel;
	
	@Column(name="email", nullable = false)
	private String email;
	
	
	
	@OneToOne(mappedBy = "hostUser")
	private Studyroom studyroom;
	
	
	@OneToMany(mappedBy = "hostUser")
	private List<Reservation> reservation;
}
