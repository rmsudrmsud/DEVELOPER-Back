package com.developer.hostuser.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;

@Service
public class HostUserService {
	
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
	 * HostUser를 삭제한다.
	 * @author SR
	 * @param hostId
	 * @throws FindException
	 */
	public void outHost(String hostId) throws FindException{
		this.selectHost(hostId);
		hostRepository.deleteById(hostId);
	}
}
