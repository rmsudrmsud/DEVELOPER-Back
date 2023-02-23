package com.developer.tutor.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.lesson.entity.Lesson;
import com.developer.users.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TUTOR")
@DynamicInsert
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tutor {
	@Id
	@Column(name="tutor_id")
	private String tutorId;
	@Column(name="info")
	private String info;
	@Column(name="img_path")
	private String imgPath;
	@Column(name="star_avg", columnDefinition = "NUMBER DEFAULT 0")
	private Double starAvg;
	@Column(name="apply_ok", columnDefinition = "NUMBER DEFAULT 0")
	private Integer applyOk;
	
	@OneToMany(mappedBy = "tutor")
	private List<Lesson> lesson;
	
	@MapsId("tutorId")
	@OneToOne(optional = true, 
						cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="tutor_id", nullable = true)
	private Users users;
}
package com.developer.tutor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="TUTOR")
@DynamicInsert

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Tutor {
	@Id
	@Column(name="user_id")
	private String userId;
	@Column(name="info")
	private String info;
	@Column(name="img_path")
	private String imgPath;
	@Column(name="star_avg")
	private Integer starAvg;
	@Column(name="apply_ok")
	private Integer applyOk;
	
	//private UsersVO usersVO;
}
