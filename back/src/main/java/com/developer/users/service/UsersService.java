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
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository uRepository;
	private final AppliedLessonRepository alRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 회원 추가.
	 * @author Jin
	 * @param usersDTO
	 * @throws AddException
	 */
	public void addUsers(UsersDTO usersDTO) throws AddException{
//		Optional<Users> u = uRepository.findById(usersDTO.getUserId());
		Users usersEntity = modelMapper.map(usersDTO, Users.class);
		uRepository.save(usersEntity);
	}
	
	/**
	 * user 상세정보 조회.
	 * @author Jin
	 * @param userId
	 * @return usersDTO
	 * @throws FindException
	 */
	public UsersDTO getUser(String userId) throws FindException{
		Optional<Users> optU = uRepository.findById(userId);
		if(optU.isPresent()) {
			Users users = optU.get();
			UsersDTO usersDTO = modelMapper.map(users, UsersDTO.class);
			
			return usersDTO;
		}else {
			throw new FindException("존재하지 않는 유저입니다.");
		}
	}


	/**
	 * 유저 로그인 
	 * @author choigeunhyeong
	 * @param userId
	 * @param pwd
	 * @return
	 * @throws FindException
	 */
	public UsersDTO.uDTO userLogin(String userId, String pwd) throws FindException {
		
		Optional<Users> optU = uRepository.findById(userId);
		if(optU.isPresent()) {
			Users users = optU.get();		
			UsersDTO.uDTO usersDTO = modelMapper.map(users, UsersDTO.uDTO.class);
			if(usersDTO.getPwd().equals(pwd) && !usersDTO.getRole().equals(3)) {
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
	 * 회원 탈퇴(Role 값이 3인 유저는 탈퇴한 것으로 간주)
	 * @author Jin
	 * @param userId
	 * @throws FindException
	 */
	public void deleteUser(String userId) throws FindException{
		UsersDTO usersDTO = this.getUser(userId);

    	ModelMapper modelMapper = new ModelMapper();
    	usersDTO.setRole(3);
		Users usersEntity = new Users();
		
		modelMapper.map(usersDTO, usersEntity);
		uRepository.save(usersEntity);
	}
	
	/**
	 * 회원정보 찾기
	 * @author choigeunhyeong
	 * @param id
	 * @return
	 * @throws FindException
	 */
	public Users findById(String userId)throws FindException{
		Optional<Users> optU= uRepository.findById(userId);
		if(optU.isPresent()) {
			return optU.get();
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}
	
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
	 * @throws RemoveException 
	 */
	public void deleteTutee(String tuteeId, Long lessonSeq) throws RemoveException {
		AppliedLesson al = alRepository.delAppliedTutee(tuteeId, lessonSeq);
		if(al.getApplySeq() != null) {
			alRepository.deleteById(al.getApplySeq());			
		} else {
			throw new RemoveException("삭제가 되지 않았습니다.");
		}
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
	
	/**
	 * 관리자페이지에서 회원리스트 전체출력
	 * @author DS
	 * @return
	 * @throws FindException
	 */
	public List<UsersDTO> getALLUsers() throws FindException{
		List<Object[]> list = uRepository.selectALLUsers();
		List<UsersDTO> dto = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			uDTO.setUserId((String)list.get(i)[0]);
			uDTO.setRole(Integer.parseInt(String.valueOf(list.get(i)[1])));
			uDTO.setName((String)list.get(i)[2]);
			uDTO.setNickname((String)list.get(i)[3]);
			uDTO.setTel((String)list.get(i)[4]);
			dto.add(uDTO);
			
		}
		return dto;
	}
	/**관리자페이지 회원리스트에서 검색하기
	 * @author DS
	 * @param userId
	 * @return List<UsersDTO> 
	 * @throws FindException
	 */
	public List<UsersDTO> getUserById(String userId) throws FindException{
		List<Object[]> list = uRepository.selectALLUsers();
		List<UsersDTO> dto = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			UsersDTO uDTO = new UsersDTO();
			uDTO.setUserId((String)list.get(i)[0]);
			uDTO.setRole(Integer.parseInt(String.valueOf(list.get(i)[1])));
			uDTO.setName((String)list.get(i)[2]);
			uDTO.setNickname((String)list.get(i)[3]);
			uDTO.setTel((String)list.get(i)[4]);
			dto.add(uDTO);
			
		}
		return dto;
	}
}
