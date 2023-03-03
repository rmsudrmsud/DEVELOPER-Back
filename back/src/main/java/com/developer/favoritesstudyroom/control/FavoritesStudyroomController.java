package com.developer.favoritesstudyroom.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.AddException;
import com.developer.exception.RemoveException;
import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.favoritesstudyroom.service.FavoritesStudyroomService;

@RestController
@RequestMapping("favoritesstudyroom/*")
public class FavoritesStudyroomController {
	@Autowired
	FavoritesStudyroomService fsService;

	/**
	 * 성공 [스터디카페 상세페이지]즐겨찾기 추가기능
	 * 
	 * @param fvDTO
	 * @throws AddException
	 */
	@PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addFavStudyroom(@RequestBody Long srSeq, HttpSession session) throws AddException {
		String logined = (String) session.getAttribute("logined");
		if (logined != null) {
			fsService.insertFVstudyroom(srSeq, logined);

		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * [스터디카페 상세페이지]즐겨찾기 삭제기능
	 * 
	 * @param favSeq
	 * @throws RemoveException
	 */
	@DeleteMapping(value = "{favSeq}")
	public ResponseEntity<?> deleteFavStudyroom(@PathVariable Long favSeq) throws RemoveException {
		fsService.deleteFvstudyroom(favSeq);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
