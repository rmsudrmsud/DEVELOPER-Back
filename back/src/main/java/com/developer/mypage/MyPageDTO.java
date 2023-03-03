package com.developer.mypage;

import java.util.List;

import com.developer.appliedlesson.dto.AppliedLessonDTO;
import com.developer.lesson.dto.LessonDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MyPageDTO {
	   
	   @Data
	   @NoArgsConstructor
	   @AllArgsConstructor
	   public static class TutorMainDTO{
		   private List<LessonDTO.GetLessonByUser1> list;
		   private List<LessonDTO.GetLessonByUser2> list2;
		   private List<LessonDTO.GetLessonByUser3> list3;
	   }
	   
	   @Data
	   @NoArgsConstructor
	   @AllArgsConstructor
	   public static class TutorUpcomingDTO{
		   private List<AppliedLessonDTO.NotYetUserByAppliedLessonDTO> notYetList;
		   private List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> approveList;
		   private List<LessonDTO.selectLessonDTO> lList;
	   }
	   
	   @Data
	   @NoArgsConstructor
	   @AllArgsConstructor
	   public static class TutorOngoingDTO{
		   private List<LessonDTO.selectLessonDTO> lList;
		   private List<AppliedLessonDTO.ApproveUserByAppliedLessonDTO> alList;
	   }
	   
	   @Data
	   @NoArgsConstructor
	   @AllArgsConstructor
	   public static class TuteeUpcomingDTO{
		   private List<LessonDTO.applyLessonBytutee> applyList;
		   private List<LessonDTO.notYetLessonBytutee> notYetlist;
	   }
	   
	   
}
