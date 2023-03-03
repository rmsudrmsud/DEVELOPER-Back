package com.developer.userreview.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.userreview.service.UserReviewService;

@RestController
@RequestMapping("userreview/*")
public class UserReviewController {

	@Autowired
	private UserReviewService urService;
	

}
