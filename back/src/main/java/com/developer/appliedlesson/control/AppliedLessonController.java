package com.developer.appliedlesson.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.appliedlesson.service.AppliedLessonService;

@RestController
@RequestMapping("appliedlesson/*")
public class AppliedLessonController {

	@Autowired
	private AppliedLessonService alService;
	
}
