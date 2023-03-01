package com.developer.favoritesstudyroom.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.developer.exception.AddException;
import com.developer.favoritesstudyroom.dto.FavoritesStudyroomDTO;
import com.developer.favoritesstudyroom.service.FavoritesStudyroomService;

public class FavoritesStudyroomController {
	@Autowired
	FavoritesStudyroomService fsService;
	
	   /**성공
	    * [스터디카페 상세페이지]즐겨찾기 추가기능
	    * @param fvDTO
	    * @throws AddException
	    */
	   @PostMapping(value = "add",produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<?> reserve(@RequestBody FavoritesStudyroomDTO.fvInsertDTO fvDTO)throws AddException{
	      
		   fsService.insertFVstudyroom(fvDTO);
	      return new ResponseEntity<>(fvDTO,HttpStatus.OK);
	   }
}
