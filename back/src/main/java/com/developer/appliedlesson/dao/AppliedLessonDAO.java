package com.developer.appliedlesson.dao;
import java.util.List;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.exception.FindException;

public interface AppliedLessonDAO {
	
	/**
	 * [일반회원 : 클래스 > 튜터 > 진행 예정 클래스 > 상세 정보] 미승인 튜티 목록
	 * @author Jin
	 * @param applyOk= 0
	 * @param 특정한 lessonSeq
	 * @return 해당 lesson에 신청한 미승인된 Tutee list
	 * @throws FindException
	 */
	public List<AppliedLesson> getLessonApplyUsers0(int applyOk, int lessonSeq) throws FindException;
	
	/**
	 * [일반회원 : 클래스 > 튜터 > 진행 예정 클래스 > 상세 정보] 승인 튜티 목록
	 * @author Jin
	 * @param applyOk=1 
	 * @param 특정한 lessonSeq
	 * @return 해당 lesson에 신청한 승인된 Tutee list
	 * @throws FindException
	 */
	public List<AppliedLesson> getLessonApplyUsers1(int applyOk, int lessonSeq) throws FindException;
	
	
	/**
	 * [일반회원 : 클래스 > 튜터 > 진행 예정 클래스 > 상세 정보] 신청튜티 승인
	 * @author Jin
	 * @param 해당 튜티의 appliedLesson의 applyOk를 1로 변경
	 * @throws FindException
	 */
	public void updateApplyLesson(int applySeq) throws FindException;
	
	
	/**
	 * [일반회원 : 클래스 > 튜터 > 진행 예정 클래스 > 상세 정보] 신청튜티 거절
	 * @author Jin
	 * @param 해당 튜티의 applySeq
	 * @throws FindException
	 */
	public void updateNotApplyLesson(int applySeq) throws FindException;

	/**
	 * [일반회원 : 클래스 > 튜터 > 진행 예정 클래스] 신청한 튜티의 VO값을 반환받기 위해 만든 메서드.
	 * @author Jin
	 * @param 해당 튜티의 applySeq
	 * @throws FindException
	 */
	public AppliedLesson getAppliedLesson(int applySeq) throws FindException;
	

	/**
	 * [마이페이지: 튜티] 대시보드 
	 * @author moonone
	 * @param userId 회원아이디 
	 * @return 신청한 수업 상태 및 목록 확인 
	 * @throws FindException
	 */
	public List<AppliedLesson> tuteeDashboard (String userId) throws FindException;
}

