package com.developer.studyroom.service;

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
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.entity.HostUser;
import com.developer.hostuser.repository.HostUserRepository;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.entity.Studyroom;
import com.developer.studyroom.repository.StudyroomRepository;

@Service
public class StudyroomService {

	@Autowired
	private StudyroomRepository studyroomRepository;
	@Autowired
	private HostUserRepository hostUserRepository;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
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


	/**
	 * Studyroom 객체 1개를 출력한다.
	 * @author SR
	 * @param srSeq
	 * @return StudyroomDTO
	 * @throws FindException
	 */
	public StudyroomDTO selectStudyroom(long srSeq) throws FindException {
		Optional<Studyroom> optStudyroom = studyroomRepository.findById(srSeq);
		if (optStudyroom.isPresent()) {
			Studyroom StudyroomEntity = optStudyroom.get();
			StudyroomDTO studyroomDTO = modelMapper.map(StudyroomEntity, StudyroomDTO.class);
			return studyroomDTO;
		} else {
			throw new FindException("해당 스터디카페가 존재하지 않습니다.");
		}
	}

	/**
	 * 스터디카페의 영업을 시작한다.
	 * @author SR
	 * @param srSeq
	 * @throws FindException
	 */
	public void openOc(Long srSeq) throws FindException {
		StudyroomDTO studyroomDTO = this.selectStudyroom(srSeq);
		studyroomDTO.setOc(0);
		Studyroom studyroomEntity = modelMapper.map(studyroomDTO, Studyroom.class);
		studyroomRepository.save(studyroomEntity);
	}

	/**
	 * 스터디카페의 영업을 마감한다.
	 * @author SR
	 * @param srSeq
	 * @throws FindException
	 */
	public void closeOc(Long srSeq) throws FindException {
		StudyroomDTO studyroomDTO = this.selectStudyroom(srSeq);
		studyroomDTO.setOc(1);
		Studyroom studyroomEntity = modelMapper.map(studyroomDTO, Studyroom.class);
		studyroomRepository.save(studyroomEntity);
	}

	/**
	 * [호스트메인페이지] 스터디룸 + 호스트정보 출력
	 * @author SR
	 * @param hostId
	 * @return StudyroomDTO.getHostAndStudyroomDTO
	 * @throws FindException
	 */
	public StudyroomDTO.getHostAndStudyroomDTO getHostAndStudyroom(String hostId) throws FindException {
		Optional<Studyroom> optS = studyroomRepository.getHostAndStudyroom(hostId);

		StudyroomDTO.getHostAndStudyroomDTO sDto = new StudyroomDTO.getHostAndStudyroomDTO();
		sDto.setSrSeq(optS.get().getSrSeq());
		sDto.setName(optS.get().getName());
		sDto.setAddr(optS.get().getAddr());
		sDto.setInfo(optS.get().getInfo());
		sDto.setOpenTime(optS.get().getOpenTime());
		sDto.setEndTime(optS.get().getEndTime());
		sDto.setImgPath(optS.get().getImgPath());
		sDto.setOc(optS.get().getOc());

		HostUserDTO.getHostDTO hDto = new HostUserDTO.getHostDTO();
		hDto.setHostId(optS.get().getHostUser().getHostId());
		hDto.setPwd(optS.get().getHostUser().getPwd());
		hDto.setNum(optS.get().getHostUser().getNum());
		hDto.setReady(optS.get().getHostUser().getReady());
		hDto.setName(optS.get().getHostUser().getName());
		hDto.setTel(optS.get().getHostUser().getTel());
		hDto.setEmail(optS.get().getHostUser().getEmail());

		sDto.setHostUserDTO(hDto);
		return sDto;
		
		
	}

	/**
	 * 스터디카페를 추가한다.
	 * @author SR
	 * @param studyroomDTO
	 * @throws AddException
	 */
	public void insertCafe(StudyroomDTO studyroomDTO, String hostId) throws AddException {
		Optional<HostUser> hostUser = hostUserRepository.findById(hostId);
		HostUser hostUserEntity = hostUser.get();
		studyroomDTO.setHostUser(hostUserEntity);
		Studyroom studyroomEntity = modelMapper.map(studyroomDTO, Studyroom.class);
		studyroomRepository.save(studyroomEntity);
	}
	
	/**
	 * 스터디카페 정보를 수정한다.
	 * @author SR
	 * @param srSeq
	 * @param studyroomDTO
	 * @throws FindException
	 */
	public void updateCafe(long srSeq, StudyroomDTO studyroomDTO) throws FindException {

		Optional<Studyroom> optCafe = studyroomRepository.findById(srSeq);
		if (optCafe.isPresent()) {
			Studyroom cafeEntity = optCafe.get();
			cafeEntity.setInfo(studyroomDTO.getInfo());
			cafeEntity.setImgPath(studyroomDTO.getImgPath());
			cafeEntity.setOpenTime(studyroomDTO.getOpenTime());
			cafeEntity.setEndTime(studyroomDTO.getEndTime());
			studyroomRepository.save(cafeEntity);
		} else {
			throw new FindException("해당 스터디카페가 존재하지 않습니다.");
		}
	}
	
}
