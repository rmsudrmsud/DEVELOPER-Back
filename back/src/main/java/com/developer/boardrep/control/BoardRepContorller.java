package com.developer.boardrep.control;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.boardrep.dto.BoardRepDTO;
import com.developer.boardrep.service.BoardRepSerivce;
import com.developer.exception.AddException;
import com.developer.exception.ModifyException;
import com.developer.exception.RemoveException;

@RestController
@RequestMapping("boardRep/*")
public class BoardRepContorller {

	
}
