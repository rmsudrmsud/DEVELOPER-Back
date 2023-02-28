package com.developer.roominfo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.roominfo.dto.RoomInfoDTO;
import com.developer.roominfo.entity.RoomInfo;
import com.developer.roominfo.repository.RoomInfoRepository;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;

@Service
public class RoomInfoService {
	@Autowired
	private RoomInfoRepository roomInfoRepository;

	@Autowired
	private StudyroomRepository studyroomRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());
	ModelMapper modelMapper = new ModelMapper();

	/**
	 * 룸 1개정보를 출력한다.
	 * 
	 * @author SR
	 * @param roomSeq
	 * @return
	 * @throws FindException
	 */
	public RoomInfoDTO selectRoom(long roomSeq) throws FindException {
		Optional<RoomInfo> optRoom = roomInfoRepository.findById(roomSeq);
		if (optRoom.isPresent()) {
			RoomInfo roomEntity = optRoom.get();
			RoomInfoDTO roomDTO = modelMapper.map(roomEntity, RoomInfoDTO.class);
			return roomDTO;
		} else {
			throw new FindException("해당 방이 존재하지 않습니다.");
		}
	}

	/**
	 * 룸을 추가한다.
	 * 
	 * @author SR
	 * @param roomInfoDTO
	 * @param srSeq
	 * @throws AddException
	 */
	public void insertRoom(RoomInfoDTO roomInfoDTO, long srSeq) {
		Optional<Studyroom> optCafe = studyroomRepository.findById(srSeq);
		Studyroom cafeEntity = optCafe.get();
		roomInfoDTO.setStudyroom(cafeEntity);
		RoomInfo roomEntity = modelMapper.map(roomInfoDTO, RoomInfo.class);
		roomInfoRepository.save(roomEntity);
	}

	/**
	 * 룸정보를 수정한다.
	 * 
	 * @author SR
	 * @param roomSeq
	 * @param roomInfoDTO
	 * @return
	 * @throws FindException
	 */
	public void updateRoom(long roomSeq, RoomInfoDTO roomInfoDTO) throws FindException {
		Optional<RoomInfo> optRoom = roomInfoRepository.findById(roomSeq);
		if (optRoom.isPresent()) {
			RoomInfo roomEntity = optRoom.get();
			roomEntity.setName(roomInfoDTO.getName());
			roomEntity.setInfo(roomInfoDTO.getInfo());
			roomEntity.setImgPath(roomInfoDTO.getImgPath());
			roomEntity.setPerson(roomInfoDTO.getPerson());
			roomEntity.setPrice(roomInfoDTO.getPrice());
			roomInfoRepository.save(roomEntity);
		} else {
			throw new FindException("해당 방이 존재하지 않습니다.");
		}
	}

	/**
	 * 룸 1개를 삭제한다(status = 1)
	 * 
	 * @author SR
	 * @param roomSeq
	 * @return
	 * @throws FindException
	 */
	public void deleteRoom(long roomSeq) throws FindException {
		RoomInfoDTO roomInfoDTO = this.selectRoom(roomSeq);
		roomInfoDTO.setStatus(1);
		RoomInfo roomEntity = modelMapper.map(roomInfoDTO, RoomInfo.class);
		roomInfoRepository.save(roomEntity);
	}

	/**
	 * 방 목록을 출력한다.
	 * 
	 * @author SR
	 * @param srSeq
	 * @return
	 * @throws FindException
	 */
	public List<RoomInfoDTO.selectAllRoomDTO> selectAllRoom(long srSeq) throws FindException {
		List<Object[]> rList = roomInfoRepository.selectAllRoom(srSeq);

		List<RoomInfoDTO.selectAllRoomDTO> rListDto = new ArrayList<>();
		for (int i = 0; i < rList.size(); i++) {
			RoomInfoDTO.selectAllRoomDTO rDto = new RoomInfoDTO.selectAllRoomDTO();
			BigDecimal roomSeq = (BigDecimal) rList.get(i)[0];
			long convertRoomSeq = roomSeq.longValue();
			rDto.setRoomSeq(convertRoomSeq);
			rDto.setName((String) rList.get(i)[1]);
			rDto.setInfo((String) rList.get(i)[2]);
			rDto.setImgPath((String) rList.get(i)[3]);
			BigDecimal person = (BigDecimal) rList.get(i)[4];
			int convertPerson = person.intValue();
			rDto.setPerson(convertPerson);
			BigDecimal price = (BigDecimal) rList.get(i)[5];
			int convertPrice = price.intValue();
			rDto.setPrice(convertPrice);

			rListDto.add(rDto);
		}
		return rListDto;

	}
}
