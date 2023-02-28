package com.developer.studyroom.dto;

import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.entity.RoomInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class StudyroomDTO {
	private long srSeq;
	private String name;
	private String addr;
	private String info;
	private String openTime;
	private String endTime;
	private String imgPath;
	private Integer oc;
	private RoomInfo roomInfo; //원래 List<>
	private HostUser hostUser;
	private FavoritesStudyroom favoritesStudyroom; //원래 List<>
	
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
	
	@Data
	@NoArgsConstructor
	public static class getAllStudyroomDTO{
		private Long srSeq;
		private String name;
		private String addr;
		private HostUserDTO.getAllHostUserDTO hostUser;
	}
	
}
