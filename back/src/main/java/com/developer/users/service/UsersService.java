package com.developer.users.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class UsersService {

	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private UsersRepository uRepository;
	
	
	/**
	 * 회원 추가.
	 * @author Jin
	 * @param usersDTO
	 * @throws AddException
	 */
	public void addUsers(UsersDTO usersDTO) throws AddException{
		Optional<Users> u = uRepository.findById(usersDTO.getUserId());
		Users usersEntity = mapper.map(usersDTO, Users.class);
		uRepository.save(usersEntity);
	}
	
	/**
	 * user 상세정보 조회.
	 * @param userId
	 * @return usersDTO
	 * @throws FindException
	 */
	public UsersDTO getUser(String userId) throws FindException{
		Optional<Users> optU = uRepository.findById(userId);
		if(optU.isPresent()) {
			Users users = optU.get();
			UsersDTO usersDTO = mapper.map(users, UsersDTO.class);
			
			return usersDTO;
		}else {
			throw new FindException("존재하지 않는 유저입니다.");
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
	
}
