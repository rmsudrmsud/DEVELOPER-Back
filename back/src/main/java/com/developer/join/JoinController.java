package com.developer.join;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.service.HostUserService;
import com.developer.studyroom.dto.StudyroomDTO;
import com.developer.studyroom.service.StudyroomService;
import com.developer.users.dto.UsersDTO;
import com.developer.users.service.UsersService;
import com.developer.util.Attach;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("join/*")
@RequiredArgsConstructor
public class JoinController {

	private final UsersService uService;
	private final HostUserService hService;
	private final StudyroomService sService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	// [JH] 호스트 회원가입
	@PostMapping(value = "hostuser")
	public ResponseEntity<?> addHost(@RequestBody HostUserDTO hostDTO) throws AddException {
		hService.addHost(hostDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// [JH] 사용자 회원가입
	@PostMapping(value = "users")
	public ResponseEntity<?> addUsers(@RequestBody UsersDTO usersDTO) throws AddException {
		uService.addUsers(usersDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// [JH] 사용자 아이디 중복체크
	@GetMapping(value = "users/check/{userId}")
	public boolean checkUser(@PathVariable String userId, HttpSession session) throws FindException {
		UsersDTO.UsersDetailDTO usersDTO;
		boolean flag = true;
		usersDTO = uService.getUser(userId);
		String check = usersDTO.getUserId();
		if (check == null) {
			flag = true;
		} else if (check != null) {
			flag = false;
		}
		return flag;
	}

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
		return new ResponseEntity<>("오류", HttpStatus.BAD_REQUEST);
	}

}
