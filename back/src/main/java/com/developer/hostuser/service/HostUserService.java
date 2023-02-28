package com.developer.hostuser.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class HostUserService {
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private HostUserRepository hostRepository;
	/**
	 * HostUser 객체 1개를 가져온다.
	 * @author SR
	 * @param hostId
	 * @return HostUser
	 * @throws FindException
	 */
	public HostUser selectHost(String hostId) throws FindException{
		Optional<HostUser> optHost =  hostRepository.findById(hostId);
		HostUser hostuser = optHost.get();
		
		if (optHost.isPresent()) {
			return hostuser;
		} else {
			throw new FindException("해당 HOST_ID가 존재하지 않습니다.");
		}
	}
	
	/**
	 * 호스트유저 추가하기
	 * @author Jin
	 * @param hostUserDTO
	 * @throws AddException
	 */
	public void addHost(HostUserDTO hostUserDTO) throws AddException{ 
		HostUser hostUserEntity = mapper.map(hostUserDTO, HostUser.class);
		hostRepository.save(hostUserEntity);
		
	}
	
	/**
	 * 호스트유저 전체목록 불러오기
	 * @author Jin
	 * @return
	 * @throws FindException
	 */
	public List<HostUser> selectAll() throws FindException{
		List<HostUser> list = hostRepository.selectAll();
		return list;
		
	}
}
