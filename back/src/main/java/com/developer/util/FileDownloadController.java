package com.developer.util;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.exception.FindException;

@RestController
@RequestMapping("download/")
public class FileDownloadController {
	//근형
	@GetMapping("board")
	public ResponseEntity<?> download(String imgPath, int type, String opt) throws FindException {

		String saveDirectory = "/Users/choigeunhyeong/Documents/attach";

		String fileName = "";
		if (type == 2) {
			fileName = "t_";
		}
		fileName += imgPath; 
		File dir = new File(saveDirectory); // 첨부파일이 있는 디렉토리
		File file = null;

		for (File f : dir.listFiles()) { // 디렉토리의 모든 파일들

			String fn = f.getName();
			
			if (fn.equals(fileName)) {
				file = f;
				fileName = f.getName();
				break;
			}
		}
		if (file == null) {
			throw new FindException(fileName + "으로 된 파일이 없습니다");
		}
		try {
		    HttpHeaders headers = new HttpHeaders();
		    String contentType = Files.probeContentType(file.toPath());
		    headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		    headers.add(HttpHeaders.CONTENT_LENGTH, "" + file.length());

		    if ("attachment".equals(opt)) {
		        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=UTF-8''"
		                + URLEncoder.encode(file.getName(), "UTF-8").replace("+", "%20"));
		    } else {
		        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename*=UTF-8''"
		                + URLEncoder.encode(file.getName(), "UTF-8").replace("+", "%20"));
		    }

		    byte[] bArr = FileCopyUtils.copyToByteArray(file);
		    ResponseEntity<?> re = new ResponseEntity<>(bArr, headers, HttpStatus.OK);
		    System.out.println(bArr);
		    return re;
		} catch (Exception e) {
		    throw new FindException(e.getMessage());
		}

//		try {
//			HttpHeaders headers = new HttpHeaders();
//			String contentType = Files.probeContentType(file.toPath()); // 파일의 형식
//			headers.add(HttpHeaders.CONTENT_TYPE, contentType);// 응답형식
//			headers.add(HttpHeaders.CONTENT_LENGTH, "" + file.length()); // 응답크기
//
//			if ("attachment".equals(opt)) {
//				headers.add(HttpHeaders.CONTENT_DISPOSITION,
//						"attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));// 다운로드
//			} else {
//				headers.add(HttpHeaders.CONTENT_DISPOSITION,
//						"inline;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));// 바로응답
//			}
//
//			byte[] bArr = FileCopyUtils.copyToByteArray(file);
//
//			ResponseEntity<?> re = new ResponseEntity<>(bArr, headers, HttpStatus.OK);
//			System.out.println(bArr);
//			return re;
//		} catch (Exception e) {
//			throw new FindException(e.getMessage());
//		}
	}
	//근형
}
