package com.developer.appliedlesson.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.developer.lesson.entity.Lesson;
import com.developer.lessonreview.entity.LessonReview;
import com.developer.users.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="APPLIED_LESSON")
@DynamicInsert

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonFormat(pattern = "yy-MM-dd", timezone = "Asia/Seoul")
@SequenceGenerator(
      name ="applySeq", 
      sequenceName ="apply_seq", 
      initialValue = 1, allocationSize = 1 
      )
public class AppliedLesson {
   @Id
   @Column(name="apply_seq")
   @GeneratedValue( 
         strategy = GenerationType.SEQUENCE, 
         generator ="applySeq"  
      )
   private Long applySeq;
   @Column(name="cdate", columnDefinition = "DATE DEFAULT SYSDATE")
   private Date cdate;
   @Column(name="apply_ok", columnDefinition = "NUMBER DEFAULT 0")
   private Integer applyOk;
   //TODO: 튜티아이디로 바꾸는 건 어떠신지 ...
   @Column(name="user_id", nullable = false)
   private String userId;
   
   
   @ManyToOne
   @JoinColumn(name="al_lessonSeq")
   private Lesson lesson;
   
   @OneToOne(mappedBy = "appliedLesson")	
   private LessonReview lessonReview;
   
   @ManyToOne
   @JoinColumn(name="tutor_id")
   private Users user;
   
   //private List<UserReviewVO> userReviewVO;
   
}