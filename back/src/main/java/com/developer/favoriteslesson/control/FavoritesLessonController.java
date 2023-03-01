package com.developer.favoriteslesson.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.RemoveException;
import com.developer.favoriteslesson.dto.FavoritesLessonDTO;
import com.developer.favoriteslesson.service.FavoritesLessonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("favoriteslesson/*")
public class FavoritesLessonController {
	@Autowired
	FavoritesLessonService flService;
	
	/**
	 *  나의 수업 즐겨찾기 목록 확인 
	 *  @author moonone
	 * @param userId 사용자아이디 
	 * @return 즐겨찾기목록
	 * @throws FindException
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list(HttpSession session) throws FindException {
		String logined = (String)session.getAttribute("logined");
		if(logined != null) {
			List<FavoritesLessonDTO.flListDTO> flDTO = flService.listFavLesson(logined);
			return new ResponseEntity<>(flDTO, HttpStatus.OK);			
		} 
		return new ResponseEntity<>("로그인하세요", HttpStatus.BAD_REQUEST);						 
	}
	
	
	/**
	 * 수업즐겨찾기 추가
	 * @author moonone
	 * @param flDTO 수업즐겨찾기
	 * @param lessonSeq 수업번호
	 * @throws AddException
	 */
	@PostMapping(value="{lessonSeq}")
	public ResponseEntity<?> add(@RequestBody String requestBody, @PathVariable Long lessonSeq) throws AddException, FindException, JsonMappingException, JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		FavoritesLessonDTO.favoritesLessonDTO flDTO = mapper.readValue(requestBody, FavoritesLessonDTO.favoritesLessonDTO.class);
		flService.addFavLesson(flDTO, lessonSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 수업 즐겨찾기 삭제 
	 * @author moonone
	 * @param favLesSeq 수업즐겨찾기SEQ
	 * @throws RemoveException 
	 */
	@DeleteMapping(value = "{favLesSeq}")
	public ResponseEntity<?> del(@PathVariable Long favLesSeq) throws RemoveException, FindException{
		flService.delFavLesson(favLesSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
