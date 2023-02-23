package com.developer.hostuser.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "host_user")
public class HostUser {
	
	@Id
	@Column(name="host_id")
	private String hostId;
	
	@Column(name="pwd")
	private String pwd;
	
	@Column(name="num")
	private String num;
	
	@Column(name="ready")
	private Integer ready;
	
	@Column(name="name")
	private String name;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(mappedBy = "hostUser", cascade=CascadeType.MERGE)
	private List<Reservation> reservation;
}
