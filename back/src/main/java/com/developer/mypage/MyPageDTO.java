package com.developer.mypage;

import java.util.List;

import com.developer.admin.AdminDTO;
import com.developer.lesson.dto.LessonDTO;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.studyroom.dto.StudyroomDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

public class MyPageDTO {

	
	@Data
	@NoArgsConstructor
	public static class getRoomReviewList{
		private List<ReservationDTO.selectRmRvDTO> selectRmRvDTO;
		private List<RoomReviewDTO.selectMyRmRvDTO> selectMyRmRvDTO;
	}
}
