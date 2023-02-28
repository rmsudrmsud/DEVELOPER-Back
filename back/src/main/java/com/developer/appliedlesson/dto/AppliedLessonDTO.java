//package com.developer.appliedlesson.dto;
//
//import java.util.Date;
//
//import com.developer.appliedlesson.entity.AppliedLesson;
//import com.developer.lesson.dto.LessonDTO;
//import com.developer.lessonreview.dto.LessonReviewDTO;
//import com.developer.userreview.dto.UserReviewDTO;
//import com.developer.users.dto.UsersDTO;
//
//import lombok.Builder;
//import lombok.Data;
//
//@Data
//public class AppliedLessonDTO {
//      private Long applySeq;
//      private Date cdate;
//      private Integer applyOk;
//      private String tuteeId;
//      
//      private LessonDTO lessonDTO;
//      private LessonReviewDTO lessonReviewDTO;
//      private UsersDTO usersDTO;
//      private UserReviewDTO userReviewDTO;
//      
//      public AppliedLessonDTO() {
//         
//      }
//      
//   @Builder
//   public AppliedLessonDTO(Long applySeq, Date cdate, Integer applyOk, String tuteeId, LessonDTO lessonDTO,
//         LessonReviewDTO lessonReviewDTO, UsersDTO usersDTO, UserReviewDTO userReviewDTO) {
//      this.applySeq = applySeq;
//      this.cdate = cdate;
//      this.applyOk = applyOk;
//      this.tuteeId = tuteeId;
//      this.lessonDTO = lessonDTO;
//      this.lessonReviewDTO = lessonReviewDTO;
//      this.usersDTO = usersDTO;
//      this.userReviewDTO = userReviewDTO;
//   }
//      
//   
//
//   public AppliedLesson toEntity() {
//      return AppliedLesson.builder()
//            .applySeq(applySeq)
//            .cdate(cdate)
//            .applyOk(applyOk)
//            .tuteeId(tuteeId)
//            .build();
//            
//   }
//
//
//
//
//
//}