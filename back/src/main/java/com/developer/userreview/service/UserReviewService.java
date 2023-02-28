package com.developer.userreview.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.exception.AddException;
import com.developer.userreview.dto.UserReviewDTO;
import com.developer.userreview.entity.UserReview;
import com.developer.userreview.repository.UserReviewRepository;

@Service
public class UserReviewService {
	@Autowired
	private UserReviewRepository userReviewRepository;
	@Autowired
	private AppliedLessonRepository appliedLessonRepository;
	

	ModelMapper modelMapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 튜티가 튜터에게 받은후기작성 
	 * @author choigeunhyeong
	 * @param addReviewDTO
	 * @throws AddException
	 * 회원아이디에 해당하는 수업번호를 알아내서 insert 한다
	 */
	@Transactional
	public void addUserReview(UserReviewDTO addReviewDTO,Long applySeqRv) throws AddException{
		
		Optional<AppliedLesson> optA = appliedLessonRepository.findById(applySeqRv);
		AppliedLesson a = optA.get();
		UserReview userReview = new UserReview();
		userReview.setReview(addReviewDTO.getReview());
		userReview.setStar(addReviewDTO.getStar());
		userReview.setApplySeqRv(addReviewDTO.getApplySeqRv());
		userReview.setAlLesson(a);
		userReviewRepository.save(userReview);
	}
}
