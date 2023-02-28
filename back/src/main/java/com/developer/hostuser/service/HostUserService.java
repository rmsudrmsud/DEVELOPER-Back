package com.developer.hostuser.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;

@Service
public class HostUserService {
	
	@Autowired
	private HostUserRepository hostRepository;
	ModelMapper modelMapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	    * HostUser 객체 1개를 가져온다.
	    * 
	    * @author SR
	    * @param hostId
	    * @return HostUser
	    * @throws FindException
	    */
	   public HostUserDTO selectHost(String hostId) throws FindException {
	      Optional<HostUser> optHost = hostRepository.findById(hostId);
	      if (optHost.isPresent()) {
	         HostUser hostEntity = optHost.get();
	         HostUserDTO hostDTO = modelMapper.map(hostEntity, HostUserDTO.class);
	         return hostDTO;
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
	
	/**
	 * 호스트로그인 
	 * @author choigeunhyeong
	 * @param userId
	 * @param pwd
	 * @return
	 * @throws FindException
	 */
	public HostUserDTO.HostLoginDTO HostLogin(String HostId, String pwd) throws FindException {
		
		Optional<HostUser> optH = hostRepository.findById(HostId);
		if(optH.isPresent()) {
			HostUser hostuser = optH.get();			
			HostUserDTO.HostLoginDTO hostLoginDTO = modelMapper.map(hostuser, HostUserDTO.HostLoginDTO.class);
			if(hostLoginDTO.getPwd().equals(pwd) && !hostLoginDTO.getReady().equals(2)) {
				hostLoginDTO.setPwd("");
				return hostLoginDTO;
			}else {
				throw new FindException("실패");
			}
		}
		else {
			throw new FindException("로그인 실패");
		}
	}
	
	/**
	 * 호스트 회원정보 찾기
	 * @author choigeunhyeong
	 * @param id
	 * @return
	 * @throws FindException
	 */
	public HostUser findById(String hostId)throws FindException{
		Optional<HostUser> optH= hostRepository.findById(hostId);
		if(optH.isPresent()) {
			return optH.get();
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}

}
