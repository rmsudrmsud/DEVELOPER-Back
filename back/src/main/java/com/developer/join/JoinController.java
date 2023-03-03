package com.developer.join;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.developer.exception.AddException;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.service.StudyroomService;
import com.developer.util.Attach;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequiredArgsConstructor
@RequestMapping("join/*")
public class JoinController {
	private final StudyroomService sService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 스터디카페를 추가한다.
	 * 
	 * @author SR
	 * @param studyroomDTO
	 * @param session
	 * @param f
	 * @return
	 * @throws AddException
	 */
	@PostMapping(value = "studyroom")
	public ResponseEntity<?> addCafe(StudyroomDTO studyroomDTO, // 파일이랑 리퀘스트바디랑 같이 못씀
			HttpSession session, MultipartFile f) throws AddException {

		// TODO 시간 정규표현식 설정해보기..프론트단이든...뭐든..
		String hostId = (String) session.getAttribute("hostLogined");
		logger.error("로그인값:" + hostId);
		String saveDirectory = "C:\\dev\\studyroom"; // 각자 주소로!
		File saveDirFile = new File(saveDirectory);
		String fileName;
		if (f != null && f.getSize() > 0) { // 첨부파일 f이 전달된 경우만 처리해라!, f1값이 없는경우
			long fSize = f.getSize(); // 첨부된 파일크기 확인
			String fOrigin = f.getOriginalFilename(); // 첨부된(업로드된)파일의 이름
			System.out.println("---파일---");
			System.out.println("fSize:" + fSize + ", fOrigin:" + fOrigin);

			logger.error("값:" + hostId);

			// 결합
			String fName = hostId + "_" + fOrigin;

			// 파일저장
			fileName = fName;
			File file = new File(saveDirFile, fileName);

			try {
				Attach.upload(f.getBytes(), file);

				// 섬네일파일 만들기 (비율맞춰서된다!)
				int width = 300;
				int height = 300;

				// 원래 첨부파일과 구분짓기 위해
				String thumbFileName = "t_" + fileName; // 섬네일파일명
				File thumbFile = new File(saveDirFile, thumbFileName);
				FileOutputStream thumbnailOS = new FileOutputStream(thumbFile);// 출력스트림
				InputStream thumbnailIS = f.getInputStream(); // 첨부파일 입력스트림

				Thumbnailator.createThumbnail(thumbnailIS, thumbnailOS, width, height);

				studyroomDTO.setImgPath(fileName);
				sService.insertCafe(studyroomDTO, hostId);
				logger.info("파일업로드 성공");
				return new ResponseEntity<>(HttpStatus.OK);
				// 읽기 쓰기
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("파일업로드에러");
				throw new AddException(e.getMessage());
			}
		}
		return new ResponseEntity<>("오류",HttpStatus.BAD_REQUEST);
	}
}
