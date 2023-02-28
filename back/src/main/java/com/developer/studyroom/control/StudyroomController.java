package com.developer.studyroom.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.developer.util.Attach;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("studyroom/*")
public class StudyroomController {
	@Autowired
	private StudyroomService studyroomService;
	private Logger logger = LoggerFactory.getLogger(getClass());
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
	public ResponseEntity<?> addCafe(StudyroomDTO studyroomDTO, //파일이랑 리퀘스트바디랑 같이 못씀
			                         String hostId, 
			                         HttpSession session, 
			                         MultipartFile f) throws AddException {
		// TODO 시간 정규표현식 설정해보기..프론트단이든...뭐든..

		hostId = "아이디4";
		// String hostId = (String) session.getAttribute("logined");
		if (hostId == null) {
			return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
		} 
		studyroomDTO.setImgPath(f.getOriginalFilename());
		studyroomService.insertCafe(studyroomDTO, hostId);
		
		String saveDirectory = "D:\\dev\\upload";  //각자 주소로!
		File saveDirFile = new File(saveDirectory);
		
		if (f != null && f.getSize() > 0) { // 첨부파일 f1이 전달된 경우만 처리해라!, f1값이 없는경우
			long fSize = f.getSize(); // 첨부된 파일크기 확인
			String fOrigin = f.getOriginalFilename(); // 첨부된(업로드된)파일의 이름
			System.out.println("---파일---");
			System.out.println("fSize:" + fSize + ", fOrigin:" + fOrigin);

	        // 확장자 추출(ex : .png)
	        //String extension = origName.substring(origName.lastIndexOf("."));

	        //TODO: 구분자인 srSeq 값 꺼내기 (엔티티 값 찾아오기 ) LAST_INSERT_ID 
//			Optional<Studyroom> studyroom = studyroomService.
//	        long srSeq = studyroomDTO.getSrSeq();
//	        logger.error("값:" +srSeq);
//	        String convertSrSeq = String.valueOf(srSeq);
	        
	        // 결합
//	        String fName = convertSrSeq + "_" + fOrigin;

			// 파일저장
//			String fileName = fName;
//			File file = new File(saveDirFile, fileName);
//			
//			try {
//				Attach.upload(f.getBytes(), file);
//
//				// 섬네일파일 만들기 (비율맞춰서된다!)
//				int width = 300;
//				int height = 300;
//
//				// 원래 첨부파일과 구분짓기 위해
//				String thumbFileName = "thum_" + fileName; // 섬네일파일명
//				File thumbFile = new File(saveDirFile, thumbFileName);
//				FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);// 출력스트림
//				InputStream thumbnailIS = f.getInputStream(); // 첨부파일 입력스트림
//
//				Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);
//				                                  // 읽기       쓰기
//			} catch (IOException e) {
//				e.printStackTrace();
//				logger.error("파일업로드에러");
//				throw new AddException(e.getMessage());
//			}
		}
		
			return new ResponseEntity<>(HttpStatus.OK);
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
