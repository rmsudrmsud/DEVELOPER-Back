package com.developer.studyroom.dto;

import java.util.List;

import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.favoritesstudyroom.entity.FavoritesStudyroom;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.users.dto.UsersDTO;
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
	
	// SR: 즐겨찾기목록용
		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public static class selectAllFavStudyroomDTO {
			private String name;
			private String addr;
		}
	
	
	//ds
		@Data
		@NoArgsConstructor
		public static class StudyroomNameDTO{
			private String name;
		}
		//ds
		@Data
		@NoArgsConstructor
		public	static class StudyroomTimeDTO{
			private String openTime;
			private String endTime;
		}
		//ds
		@Data
		@NoArgsConstructor
		public static class StudyroomSelectBySearchDTO{
			private String name;
			private String addr;
			private String imgPath;
			private Integer person;
			private RoomInfoDTO.RoomInfoPriceAndPersonDTO roomInfoPriceAndPersonDTO;
			private FavoritesStudyroomDTO.favoritesStudyroomUserIdDTO favoritesStudyroomUserIdDTO;
			
		}
		//ds
		@Data
		@NoArgsConstructor
		public static class StudyroomNameAndUserDTO{
			private String name;
			private UsersDTO.UserNickNameDTO userNickNameDTO;
		}
		
		//ds
		@Data
		@NoArgsConstructor
		public static class StudyroomSrSeqDTO{
			private Long srSeq;
		}
		
		//ds
		@Data
		@NoArgsConstructor
		public static class StudyroomList5DTO{
			private Long srSeq;
			private String name;
			private HostUserDTO.HostIdDTO hostIdDTO;
		}
		
		//ds
		@Data
		@NoArgsConstructor
		public static class StudyroomRoomInfoPageDTO{
			private List<RoomInfoDTO> roominfoDTO;
			private List<RoomReviewDTO.RoomReviewSelectAllDTO> roomReviewSelectAllDTO;
			private StudyroomDTO studyroomDTO;
		}
}
