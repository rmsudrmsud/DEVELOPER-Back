package com.developer.hostuser.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.reservation.entity.Reservation;
import com.developer.studyroom.entity.Studyroom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "host_user")

@DynamicInsert
@DynamicUpdate
public class HostUser { 
	
	@Id
	@Column(name="host_id")
	private String hostId;
	
	@NotNull
	@Column(name="pwd")
	private String pwd;
	
	@NotNull
	@Column(name="num")
	private String num;
	
	@Column(name="ready")
	@ColumnDefault(value = "0") //0: 승인대기, 1: 승인, 2:탈퇴
	private Integer ready;
	
	@NotNull
	@Column(name="name")
	private String name;
	
	@NotNull
	@Column(name="tel")
	private String tel;
	
	@NotNull
	@Column(name="email")
	private String email;

	
	@OneToOne(mappedBy = "hostUser")//, cascade = CascadeType.ALL)//, cascade = CascadeType.REMOVE)
	private Studyroom studyroom;
	
	@OneToMany(mappedBy = "hostUser")
	private List<Reservation> reservation;
}
