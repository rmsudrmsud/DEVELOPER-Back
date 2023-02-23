package com.developer.board.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.board.entity.Board;
import com.developer.board.repository.BoardRepository;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private UsersRepository UsersRepository;
	
	/**
	 * 게시글 작성
	 * @author choigeunhyeong
	 * @param board
	 * @throws AddException
	 */
	public void addBoard(Board board) throws AddException{
		Optional<Users> optU =  UsersRepository.findById("아이디2");
		Users users = optU.get();
		board.setUsers(users);
		boardRepository.save(board);
	}
	
	/**
	 * 글 번호로 게시글 상세 검색(닉네임+글상세+댓글)
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 */
	public Map<String, Object> findPostSeq(Long postSeq) throws FindException{
		Optional<Users> optU =  UsersRepository.findById("아이디2");
		Users users = optU.get();
		Map<String, Object> list = boardRepository.findPostSeq(postSeq);
		return list;
	}
	
	
	
	/**
	 * 글 수정
	 * @param board
	 * @throws FindException
	 */
	public void editBoard(Board board) throws FindException{
		Optional<Users> optU =  UsersRepository.findById("아이디1");
		Users users = optU.get();
		board.setUsers(users);
		boardRepository.save(board);
	}
}
