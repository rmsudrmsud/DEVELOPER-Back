package com.developer.users.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 로그인 
	 * @author choigeunhyeong
	 * @param userId
	 * @param pwd
	 * @return
	 * @throws FindException
	 */
	public UsersDTO userLogin(String userId, String pwd)throws FindException {
		
		Optional<Users> optU = usersRepository.findById(userId);
		logger.error("optU : "+optU);
		if(optU.isPresent()) {
			Users users = optU.get();
			logger.error("값"+users);			
			UsersDTO usersDTO = modelMapper.map(users, UsersDTO.class);
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
}
