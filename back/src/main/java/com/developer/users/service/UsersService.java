package com.developer.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository uRepository;
	private AppliedLessonRepository alRepository;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	ModelMapper modelMapper = new ModelMapper();
	
	/**
	 * user 객체 1개를 출력한다
	 * @author SR
	 * @param userId
	 * @return
	 * @throws FindException
	 */
	public UsersDTO selectUsers(String userId) throws FindException {
		Optional<Users> optU = uRepository.findById(userId);
		if (optU.isPresent()) {
			Users entityU = optU.get();
			UsersDTO usersDTO = modelMapper.map(entityU, UsersDTO.class);
			return usersDTO;
		} else {
			throw new FindException("해당 ID가 존재하지 않습니다.");
		}
	}
	
	/**
	 * [관리자] 수업을 예약한 튜티 목록
	 * @author moonone
	 * @param lessonSeq 수업번호
	 * @return 튜티목록
	 */
	public List<UsersDTO.uNameDTO> applyTuteeList(Long lessonSeq) {
		 List<Object> list = uRepository.applyTuteeList(lessonSeq);
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

	/**
	 * 튜터로 승인한다.
	 * @author SR
	 * @param userId
	 * @throws FindException
	 */
	public void tutorApply(String userId) throws FindException {
		UsersDTO usersDTO = this.selectUsers(userId);
		usersDTO.setRole(1);
		Users EntityU = modelMapper.map(usersDTO, Users.class);
		uRepository.save(EntityU);
	}
	

	/**
	 * 튜터 미승인 목록을 출력한다.
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	public List<UsersDTO.unapproveTutorDTO> selectAllUnapproveTutor() throws FindException{
		List<Object[]> uList = uRepository.selectAllUnapproveTutor();
		
		List<UsersDTO.unapproveTutorDTO> uListDto = new ArrayList<>();
		for(int i=0; i<uList.size(); i++) {
			UsersDTO.unapproveTutorDTO uDto = new UsersDTO.unapproveTutorDTO();
			uDto.setUserId((String)uList.get(i)[0]);
			uDto.setName((String)uList.get(i)[1]);
			uDto.setNickname((String)uList.get(i)[2]);
			uDto.setEmail((String)uList.get(i)[3]);
			uDto.setTel((String)uList.get(i)[4]);
			
			uListDto.add(uDto);
		}
		return uListDto;
	}
}
