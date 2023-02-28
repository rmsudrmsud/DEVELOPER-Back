package com.developer.hostuser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;
import com.developer.reservation.entity.Reservation;

@Service
public class HostUserService {

	@Autowired
	private HostUserRepository hostRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());
	ModelMapper modelMapper = new ModelMapper();

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
	 * HostUser 상태값을 변환한다(탈퇴기능 ready=2).
	 * 
	 * @author SR
	 * @param hostId
	 * @throws FindException
	 */
	public void outHost(String hostId) throws FindException {
		Optional<HostUser> optHost = hostRepository.findById(hostId);
		if (optHost.isPresent()) {
			HostUser hostEntity = optHost.get();
			hostEntity.setReady(2);
			hostRepository.save(hostEntity);
		}
	}

	/**
	 * 호스트정보를 수정한다.
	 * 
	 * @author SR
	 * @param hostId
	 * @param hostUserDTO
	 * @throws FindException
	 */
	public void updateHost(String hostId, HostUserDTO hostuserDTO) throws FindException {

		Optional<HostUser> optHost = hostRepository.findById(hostId);
		if (optHost.isPresent()) {
			HostUser hostEntity = optHost.get();

			// logger.error("값:" + hostuserDTO.toString());
			hostEntity.setPwd(hostuserDTO.getPwd());
			hostEntity.setTel(hostuserDTO.getTel());
			hostEntity.setEmail(hostuserDTO.getEmail());
			hostRepository.save(hostEntity);

		} else {
			throw new FindException("호스트회원정보 수정 오류");
		}
	}

	/**
	 * 미승인 호스트 유저 목록 출력한다.
	 * 
	 * @author SR
	 * @return
	 * @throws FindException
	 */
	public List<HostUserDTO.unApproveHostDTO> hostUnapproveList() throws FindException {
		List<Object[]> hList = hostRepository.selectAllUnapproveHost();
		List<HostUserDTO.unApproveHostDTO> hListDto = new ArrayList<>();

		for (int i = 0; i < hList.size(); i++) {
			HostUserDTO.unApproveHostDTO hDto = new HostUserDTO.unApproveHostDTO();
			hDto.setHostId((String) hList.get(i)[0]);
			hDto.setNum((String) hList.get(i)[1]);
			hDto.setName((String) hList.get(i)[2]);
			hDto.setTel((String) hList.get(i)[3]);
			hDto.setEmail((String) hList.get(i)[4]);

			hListDto.add(hDto);
		}
		return hListDto;
	}

	/**
	 * 호스트 가입을 승인한다.
	 * 
	 * @author SR
	 * @param hostId
	 * @throws FindException
	 */
	public void readyOk(String hostId) throws FindException {
		Optional<HostUser> optHost = hostRepository.findById(hostId);
		HostUser hostEntity = optHost.get();
		hostEntity.setReady(1);
		hostRepository.save(hostEntity);
	}

	/**
	 * 호스트 승인을 거절한다(삭제)
	 * 
	 * @author SR
	 * @param hostId
	 * @throws RemoveException
	 */
	public void deleteHost(String hostId) throws RemoveException {
		Optional<HostUser> optH = hostRepository.findById(hostId);
		HostUser entityH = optH.get();
		hostRepository.delete(entityH);
	}
}
