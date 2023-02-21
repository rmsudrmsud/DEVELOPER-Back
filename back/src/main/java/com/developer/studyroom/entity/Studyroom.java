package com.developer.studyroom.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.developer.favaritesstudyroom.entity.FavoritesStudyroom;
import com.developer.hostuser.entity.HostUser;
import com.developer.reservation.entity.Reservation;
import com.developer.roominfo.entity.RoomInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor 

@Entity
@Table(name = "studyroom")
@SequenceGenerator(
name =
"SR_SEQ_GENERATOR", // 사용할 sequence 이름
sequenceName =
"SR_SEQ", // 실제 데이터베이스 sequence 이름
initialValue = 1, allocationSize = 1
)

@DynamicInsert()  //ColumnDefault때문에
@DynamicUpdate()
public class Studyroom {
	
	
		@Id
		@Column(name="sr_seq")
		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "SR_SEQ_GENERATOR" // 위의 sequence 이름
				)
		private long srSeq;
		
		@Column(name="name", nullable = false)
		private String name;
		
		@Column(name="addr", nullable = false)
		private String addr;
		
		@Column(name="info")
		private String info;
		
		@Column(name="open_time", nullable = false)
		private String openTime;
		
		@Column(name="end_time", nullable = false)
		private String endTime;
		
		@Column(name="img_path", nullable = false)
		private String imgPath;
		
		@Column(name="oc")
		@ColumnDefault(value = "0")
		private Integer oc;
		
		//sr
		@OneToMany(cascade= {CascadeType.REMOVE}, 
				   mappedBy = "studyroom")
		private List<RoomInfo> roomInfo;
		
		//sr
		@OneToOne(cascade= {CascadeType.MERGE})
		@JoinColumn(name="host_id", nullable = false)
		private HostUser hostUser;
		
		
		//sr
		@OneToMany(cascade= {CascadeType.REMOVE}
		          , mappedBy = "studyroom")
		private List<FavoritesStudyroom> favoritesStudyroom;
		
//		//sr: 카페등록때 필요한 생성자  DTO로 나중에 만들기
//		public StudyroomVO(int srSeq, String name, String addr, String info, String openTime, String endTime,
//				String imgPath, int oc, String hostId) {
//			super();
//			this.srSeq = srSeq;
//			this.name = name;
//			this.addr = addr;
//			this.info = info;
//			this.openTime = openTime;
//			this.endTime = endTime;
//			this.imgPath = imgPath;
//			this.oc = oc;
//			this.hostId = hostId;
//		}
		
}
