package com.developer.mypage;

import java.util.List;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.lesson.dto.LessonDTO;
import com.developer.reservation.dto.ReservationDTO;
import com.developer.roomreview.dto.RoomReviewDTO;
import com.developer.users.dto.UsersDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

public class MyPageDTO {

	
	@Data
	@NoArgsConstructor
	public static class getRoomReviewList{
		private List<ReservationDTO.selectRmRvDTO> selectRmRvDTO;
		private List<RoomReviewDTO.selectMyRmRvDTO> selectMyRmRvDTO;
	}
	
	//근형 mypage-tutor-completed-detail
		@Data
		@NoArgsConstructor
		public static class tutorCompletedDetailDTO{
			List<AppliedLessonDTO.UserByAppliedLessonDTO> userAppliedLessonDTO;
			List<LessonDTO.selectLessonDTO> selectLessonDTO;
			List<UsersDTO.getCompletedClassDTO> CompletedlessonReviewDTO;
		}
}
