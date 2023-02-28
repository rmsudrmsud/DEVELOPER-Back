package com.developer.hostuser.control;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;
import com.developer.hostuser.dto.HostUserDTO;
import com.developer.hostuser.service.HostUserService;

@RestController
@RequestMapping("hostuser/*")
public class HostUserController {
	
	   private Logger logger = LoggerFactory.getLogger(getClass());
	   @Autowired
	   private HostUserService hostUserService;

	   /**
	    * 호스트유저 1개의 정보를 출력한다.
	    * @author SR
	    * @param hostId
	    * @param session
	    * @return
	    * @throws FindException
	    */
	   @GetMapping(value ="", produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<?> infoHost(String hostId, HttpSession session) throws FindException {
	      
	      hostId = "아이디1";
	       //hostId = (String) session.getAttribute("logined");
	      if (hostId == null) {
	         return new ResponseEntity<>("먼저 로그인을 해주세요", HttpStatus.BAD_REQUEST);
	      } else {
	         HostUserDTO hostDTO = hostUserService.selectHost(hostId);
	         return new ResponseEntity<>(hostDTO, HttpStatus.OK);
	      }
	   }
	   
	   /**
	    * 호스트유저 로그
	    * @author choigeunhyeong
	    * @param hostId
	    * @param pwd
	    * @param session
	    * @return
	    * @throws FindException
	    */
	   @PostMapping(value="hostlogin",  produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<?> hostLogin(@RequestParam String hostId, @RequestParam String pwd, HttpSession session) throws FindException{
		   HostUserDTO.HostLoginDTO hostDTO = hostUserService.HostLogin(hostId, pwd);
		   session.setAttribute("hostLogined", hostDTO.getHostId());
		   session.setAttribute("hostLoginedReady", hostDTO.getReady());
		   logger.info("로그인성공시 sessionid : " + session.getId());
			return new ResponseEntity<>(hostDTO, HttpStatus.OK);
	   }
	   
	   /**
		 * 호스트 로그아웃
		 * @author choigeunhyeong
		 * @param session
		 * @return
		 */
		@RequestMapping("logout")
		public String logout(HttpSession session) {
			logger.info("로그아웃시 sessionid : " + session.getId());
			session.invalidate();
			return "";
		}
}


