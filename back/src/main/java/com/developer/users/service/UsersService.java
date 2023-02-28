package com.developer.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository usersRepository;
	private final AppliedLessonRepository alRepository;
	private final ModelMapper modelMapper;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 로그인 
	 * @author choigeunhyeong
	 * @param userId
	 * @param pwd
	 * @return
	 * @throws FindException
	 */
	public UsersDTO.uDTO userLogin(String userId, String pwd) throws FindException {
		
		Optional<Users> optU = usersRepository.findById(userId);
		logger.error("optU : "+optU);
		if(optU.isPresent()) {
			Users users = optU.get();
			logger.error("값"+users);			
			UsersDTO.uDTO usersDTO = modelMapper.map(users, UsersDTO.uDTO.class);
			if(usersDTO.getPwd().equals(pwd)) {
				usersDTO.setPwd("");
				return usersDTO;
			}else {
				throw new FindException("실패");
			}
		}
		else {
			throw new FindException("로그인 실패");
		}
	}
	
	/**
	 * 회원정보 찾기
	 * @author choigeunhyeong
	 * @param id
	 * @return
	 * @throws FindException
	 */
	public Users findById(String userId)throws FindException{
		Optional<Users> optU= usersRepository.findById(userId);
		if(optU.isPresent()) {
			return optU.get();
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}
	
	
	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	public List<UsersDTO.uNameDTO> applyTuteeList(Long lessonSeq) {
		 List<Object> list = usersRepository.applyTuteeList(lessonSeq);
		 List<UsersDTO.uNameDTO> uDTO = new ArrayList<>();
		 for(int i=0; i<list.size(); i++) {
			 UsersDTO.uNameDTO dto = new UsersDTO.uNameDTO();
			 dto.setName((String)list.get(i));
			 uDTO.add(dto);
		 }
		 list.add(uDTO);
		return uDTO;
	}
	
	
	/**
	 * [관리자] 수업을 예약한 튜티 삭제
	 * @author moonone
	 * @param tuteeId 튜티아이디
	 */
	public void deleteTutee(String tuteeId) {
		AppliedLesson al = alRepository.findByTuteeId(tuteeId);
		alRepository.delete(al);
	}
}
