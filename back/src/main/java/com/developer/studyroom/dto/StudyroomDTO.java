package com.developer.studyroom.dto;

import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.entity.RoomInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyroomDTO {
	private long srSeq;
	private String name;
	private String addr;
	private String info;
	private String openTime;
	private String endTime;
	private String imgPath;
	private Integer oc;
	@JsonIgnore
	private RoomInfo roomInfo; // 원래 List<>
	@JsonIgnore
	private HostUser hostUser;
	@JsonIgnore
	private FavoritesStudyroom favoritesStudyroom; // 원래 List<>


	//sr: 카페등록때 필요한 생성자
	public StudyroomDTO(long srSeq, String name, String addr, String info, String openTime, String endTime,
			String imgPath, Integer oc) {
		super();
		this.srSeq = srSeq;
		this.name = name;
		this.addr = addr;
		this.info = info;
		this.openTime = openTime;
		this.endTime = endTime;
		this.imgPath = imgPath;
		this.oc = oc;
	}
	
	//근형
	@Data
	@NoArgsConstructor
	public static class getAllStudyroomDTO{
		private Long srSeq;
		private String name;
		private String addr;
		private HostUserDTO.getAllHostUserDTO hostUser;
	}
	


	// sr: 카페등록때 필요한 생성자
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class InsertStudyroomDTO {
		private long srSeq;
		private String name;
		private String addr;
		private String info;
		private String openTime;
		private String endTime;
		private String imgPath;
		private Integer oc;
	}

	// SR
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class getHostAndStudyroomDTO {
		private long srSeq;
		private String name;
		private String addr;
		private String info;
		private String openTime;
		private String endTime;
		private String imgPath;
		private Integer oc;
		private HostUserDTO.getHostDTO hostUserDTO;
	}
}
