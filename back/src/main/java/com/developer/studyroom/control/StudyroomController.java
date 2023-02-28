package com.developer.studyroom.control;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.service.StudyroomService;

@RestController
@RequestMapping("studyroom/*")
public class StudyroomController {
	@Autowired
	private StudyroomService studyroomService;

	/**
	 * Studyroom 객체 1개를 출력한다.
	 * 
	 * @author SR
	 * @param srSeq
	 * @param session
	 * @return StudyroomDTO
	 * @throws FindException
	 */
	@GetMapping(value = "{srSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> infoCafe(@PathVariable long srSeq, HttpSession session) throws FindException {
		StudyroomDTO dto = studyroomService.selectStudyroom(srSeq);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/**
	 * 스터디카페의 영업을 시작한다.
	 * 
	 * @author SR
	 * @param srSeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "open/{srSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> openCafe(@PathVariable Optional<Integer> srSeq, HttpSession session) throws FindException {
		Integer seq = srSeq.get();
		Long srSeqValue = Long.valueOf(seq);
		studyroomService.openOc(srSeqValue);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 스터디카페의 영업을 마감한다.
	 * 
	 * @author SR
	 * @param srSeq
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PatchMapping(value = "close/{srSeq}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> closeCafe(@PathVariable long srSeq, HttpSession session)
			throws FindException {

		studyroomService.closeOc(srSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * [호스트메인페이지] 스터디룸 + 호스트정보 출력
	 * @author SR
	 * @return ResponseEntity<?>
	 * @throws FindException
	 */
	@GetMapping(value = "infohostandcafe", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> InfoHostAndCafeController(String hostId, HttpSession session) throws FindException {
		
		hostId = "아이디2";
		// hostId = (String) session.getAttribute("logined");
		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			StudyroomDTO.getHostAndStudyroomDTO dto = studyroomService.getHostAndStudyroom(hostId);
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}

	}

	/**
	 * 스터디카페를 추가한다.
	 * 
	 * @author SR
	 * @param studyroomDTO
	 * @throws AddException
	 */
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCafe(@RequestBody StudyroomDTO studyroomDTO, String hostId, HttpSession session, MultipartFile f1) throws AddException {
		// TODO 시간 정규표현식 설정해보기..프론트단이든...뭐든..

		hostId = "아이디4";
		// String hostId = (String) session.getAttribute("logined");
		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} else {
			studyroomService.insertCafe(studyroomDTO, hostId);
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

	/**
	 * 스터디카페 정보를 수정한다.
	 * @author SR
	 * @param srSeq
	 * @param studyroomDTO
	 * @param session
	 * @return
	 * @throws FindException
	 */
	@PutMapping(value = "edit/{srSeq}")
	public ResponseEntity<?> editCafe(@PathVariable long srSeq, @RequestBody StudyroomDTO studyroomDTO,
			HttpSession session) throws FindException {

		studyroomService.updateCafe(srSeq, studyroomDTO);
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
