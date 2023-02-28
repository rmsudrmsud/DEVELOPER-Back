package com.developer.studyroom.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;

@Service
public class StudyroomService {
	@Autowired
	private StudyroomRepository studyroomRepository;
	
	ModelMapper modelMapper = new ModelMapper();
	
	/**
	 * 관리자 스터디카페 전체목록 출력(일단은 방번호, 방이름, 주소, 등록한id) 추후수정
	 * @author choigeunhyeong
	 * @return
	 * @throws FindException
	 */
	public List<StudyroomDTO.getAllStudyroomDTO> getAllStudyroom() throws FindException{
		List<Object[]> Slist = studyroomRepository.getAllStudyroom();
		List<StudyroomDTO.getAllStudyroomDTO> dtoList = new ArrayList<>();
		for(int i = 0; i <Slist.size(); i ++) {
			StudyroomDTO.getAllStudyroomDTO srDTO = new StudyroomDTO.getAllStudyroomDTO();
			BigDecimal sr_seq = (BigDecimal) Slist.get(i)[0];
			Long resultsr_seq = sr_seq.longValue();
			srDTO.setSrSeq(resultsr_seq);
			srDTO.setName((String) Slist.get(i)[5]);
			srDTO.setAddr((String) Slist.get(i)[1]);
			HostUserDTO.getAllHostUserDTO hDTO = new HostUserDTO.getAllHostUserDTO();
			hDTO.setHostId((String) Slist.get(i)[8]);
			srDTO.setHostUser(hDTO);
			dtoList.add(srDTO);
		}
		return dtoList;
	}
	/**
	 * 관리자 스터디카페 상세페이지 -> 오류발생시 엔티티 @JsonIgnore 지우고 반환타입 dto로 만들어서 ..!
	 * @author choigeunhyeong
	 * @param srSeq
	 * @return
	 * @throws FindException
	 */
	public Studyroom detailStudyroom(Long srSeq) throws FindException{
		Optional<Studyroom> optS = studyroomRepository.findById(srSeq);
		Studyroom s = optS.get();
		return s;	
	}
}
