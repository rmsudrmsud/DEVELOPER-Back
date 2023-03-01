package com.developer.board.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developer.board.dto.BoardDTO;
import com.developer.board.dto.BoardDTO.getBoardByBoardTypeDTO;
import com.developer.board.entity.Board;
import com.developer.board.repository.BoardRepository;
import com.developer.boardrep.dto.BoardRepDTO.BoardRepSelectDTO;
import com.developer.exception.AddException;
import com.developer.exception.FindException;
import com.developer.exception.ModifyException;
import com.developer.exception.RemoveException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.entity.Users;
import com.developer.users.repository.UsersRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository bRepository;
	@Autowired
	private UsersRepository uRepository;
	ModelMapper modelMapper = new ModelMapper();
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 게시글 작성
	 * 
	 * @author choigeunhyeong
	 * @param board
	 * @throws AddException
	 */
	@Transactional
	public void addBoard(BoardDTO.saveBoardDTO saveBoardDTO, String logined) throws AddException {
		Board board = new Board();
		Users writer = new Users();
		writer.setUserId(logined);
		board.setTitle(saveBoardDTO.getTitle());
		board.setContent(saveBoardDTO.getContent());
		board.setImgPath(saveBoardDTO.getImgPath());
		board.setUsers(writer);
		bRepository.save(board);

	}
	/**
	 * 게시글 전체 목록(작성일순)
	 * @author choigeunhyeong
	 * @return
	 * @throws FindException
	 */
	public List<BoardDTO.getBoardByBoardTypeDTO> getBoardByC_date() throws FindException {
		List<Object[]> Blist = bRepository.getBoardByC_date();
		List<BoardDTO.getBoardByBoardTypeDTO> dtoList = new ArrayList<>();
		for (int i = 0; i < Blist.size(); i++) {
			BoardDTO.getBoardByBoardTypeDTO bDTO = new BoardDTO.getBoardByBoardTypeDTO();
			BigDecimal post_seq = (BigDecimal) Blist.get(i)[1];
			Long resultPost_seq = post_seq.longValue();
			bDTO.setPostSeq(resultPost_seq);

			BigDecimal Category = (BigDecimal) Blist.get(i)[2];
			int resultCategory = Category.intValue();
			bDTO.setCategory(resultCategory);

			bDTO.setTitle((String) Blist.get(i)[3]);
			bDTO.setContent((String) Blist.get(i)[4]);
			bDTO.setImgPath((String) Blist.get(i)[5]);
			bDTO.setCDate((Date) Blist.get(i)[6]);

			BigDecimal Recommend = (BigDecimal) Blist.get(i)[7];
			int resultRec = Recommend.intValue();
			bDTO.setRecommend(resultRec);

			BigDecimal Cnt = (BigDecimal) Blist.get(i)[8];
			int resultCnt = Cnt.intValue();
			bDTO.setCnt(resultCnt);

			UsersDTO.UsersNameDTO uDTO = new UsersDTO.UsersNameDTO();
			uDTO.setNickname((String) Blist.get(i)[0]);
			bDTO.setUsersNameDTO(uDTO);
			dtoList.add(bDTO);
		}
		return dtoList;

	}
	
	/**
	 * 게시글 전체 목록(추천순)
	 * @author choigeunhyeong
	 * @return
	 * @throws FindException
	 */
	public List<BoardDTO.getBoardByBoardTypeDTO> getBoardByRecommend() throws FindException {
		List<Object[]> Blist = bRepository.getBoardByRecommend();
		List<BoardDTO.getBoardByBoardTypeDTO> dtoList = new ArrayList<>();
		for (int i = 0; i < Blist.size(); i++) {
			BoardDTO.getBoardByBoardTypeDTO bDTO = new BoardDTO.getBoardByBoardTypeDTO();
			BigDecimal post_seq = (BigDecimal) Blist.get(i)[1];
			Long resultPost_seq = post_seq.longValue();
			bDTO.setPostSeq(resultPost_seq);

			BigDecimal Category = (BigDecimal) Blist.get(i)[2];
			int resultCategory = Category.intValue();
			bDTO.setCategory(resultCategory);

			bDTO.setTitle((String) Blist.get(i)[3]);
			bDTO.setContent((String) Blist.get(i)[4]);
			bDTO.setImgPath((String) Blist.get(i)[5]);
			bDTO.setCDate((Date) Blist.get(i)[6]);

			BigDecimal Recommend = (BigDecimal) Blist.get(i)[7];
			int resultRec = Recommend.intValue();
			bDTO.setRecommend(resultRec);

			BigDecimal Cnt = (BigDecimal) Blist.get(i)[8];
			int resultCnt = Cnt.intValue();
			bDTO.setCnt(resultCnt);

			UsersDTO.UsersNameDTO uDTO = new UsersDTO.UsersNameDTO();
			uDTO.setNickname((String) Blist.get(i)[0]);
			bDTO.setUsersNameDTO(uDTO);
			dtoList.add(bDTO);
		}
		return dtoList;

	}
	
	/**
	 * 게시글 전체 목록(조회수순)
	 * @author choigeunhyeong
	 * @return
	 * @throws FindException
	 */
	public List<BoardDTO.getBoardByBoardTypeDTO> getBoardByCnt() throws FindException {
		List<Object[]> Blist = bRepository.getBoardByCnt();
		List<BoardDTO.getBoardByBoardTypeDTO> dtoList = new ArrayList<>();
		for (int i = 0; i < Blist.size(); i++) {
			BoardDTO.getBoardByBoardTypeDTO bDTO = new BoardDTO.getBoardByBoardTypeDTO();
			BigDecimal post_seq = (BigDecimal) Blist.get(i)[1];
			Long resultPost_seq = post_seq.longValue();
			bDTO.setPostSeq(resultPost_seq);

			BigDecimal Category = (BigDecimal) Blist.get(i)[2];
			int resultCategory = Category.intValue();
			bDTO.setCategory(resultCategory);

			bDTO.setTitle((String) Blist.get(i)[3]);
			bDTO.setContent((String) Blist.get(i)[4]);
			bDTO.setImgPath((String) Blist.get(i)[5]);
			bDTO.setCDate((Date) Blist.get(i)[6]);

			BigDecimal Recommend = (BigDecimal) Blist.get(i)[7];
			int resultRec = Recommend.intValue();
			bDTO.setRecommend(resultRec);

			BigDecimal Cnt = (BigDecimal) Blist.get(i)[8];
			int resultCnt = Cnt.intValue();
			bDTO.setCnt(resultCnt);

			UsersDTO.UsersNameDTO uDTO = new UsersDTO.UsersNameDTO();
			uDTO.setNickname((String) Blist.get(i)[0]);
			bDTO.setUsersNameDTO(uDTO);
			dtoList.add(bDTO);
		}
		return dtoList;

	}
	
	
//	public List<BoardDTO.getBoardByBoardTypeDTO> selectAllList() throws FindException {
//		List<Object[]> Blist = BoardRepository.getBoardByC_date();
//		List<BoardDTO.getBoardByBoardTypeDTO> dtoList = new ArrayList<>();
//		for (int i = 0; i < Blist.size(); i++) {
//			BoardDTO.getBoardByBoardTypeDTO bDTO = new BoardDTO.getBoardByBoardTypeDTO();
//			BigDecimal post_seq = (BigDecimal) Blist.get(i)[1];
//			Long resultPost_seq = post_seq.longValue();
//			bDTO.setPostSeq(resultPost_seq);
//
//			BigDecimal Category = (BigDecimal) Blist.get(i)[2];
//			int resultCategory = Category.intValue();
//			bDTO.setCategory(resultCategory);
//
//			bDTO.setTitle((String) Blist.get(i)[3]);
//			bDTO.setContent((String) Blist.get(i)[4]);
//			bDTO.setImgPath((String) Blist.get(i)[5]);
//			bDTO.setCDate((Date) Blist.get(i)[6]);
//
//			BigDecimal Recommend = (BigDecimal) Blist.get(i)[7];
//			int resultRec = Recommend.intValue();
//			bDTO.setRecommend(resultRec);
//
//			BigDecimal Cnt = (BigDecimal) Blist.get(i)[8];
//			int resultCnt = Cnt.intValue();
//			bDTO.setCnt(resultCnt);
//
//			UsersDTO.UsersNameDTO uDTO = new UsersDTO.UsersNameDTO();
//			uDTO.setNickname((String) Blist.get(i)[0]);
//			bDTO.setUsersNameDTO(uDTO);
//			dtoList.add(bDTO);
//		}
//		return dtoList;
//
//	}

	/**
	 * 글 번호로 게시글 상세 검색(닉네임+글상세+댓글)
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * @throws FindException
	 */
	public List<BoardDTO.BoardAllSelectDTO> selectAllPostSeq(Long postSeq) throws FindException {
		List<Object[]> Blist = bRepository.findPostSeq(postSeq);
		List<BoardDTO.BoardAllSelectDTO> dto = new ArrayList<>();
		for (int i = 0; i < Blist.size(); i++) {
			BoardDTO.BoardAllSelectDTO bDTO = new BoardDTO.BoardAllSelectDTO();
			BigDecimal post_seq = (BigDecimal) Blist.get(i)[2];
			Long resultPost_seq = post_seq.longValue();
			bDTO.setPostSeq(resultPost_seq);

			BigDecimal Category = (BigDecimal) Blist.get(i)[3];
			int resultCategory = Category.intValue();
			bDTO.setCategory(resultCategory);

			bDTO.setTitle((String) Blist.get(i)[4]);
			bDTO.setContent((String) Blist.get(i)[5]);
			bDTO.setImgPath((String) Blist.get(i)[6]);
			bDTO.setCDate((Date) Blist.get(i)[7]);

			BigDecimal Recommend = (BigDecimal) Blist.get(i)[8];
			int resultRec = Recommend.intValue();
			bDTO.setRecommend(resultRec);

			BigDecimal Cnt = (BigDecimal) Blist.get(i)[9];
			int resultCnt = Cnt.intValue();
			bDTO.setCnt(resultCnt);

			UsersDTO.UsersNameDTO uDTO = new UsersDTO.UsersNameDTO();
			uDTO.setNickname((String) Blist.get(i)[1]);
			BigDecimal Role = (BigDecimal) Blist.get(i)[12];
			int userRole = Role.intValue();
			uDTO.setRole(userRole);
			uDTO.setUserId((String) Blist.get(i)[13]);

			BoardRepSelectDTO brsDTO = new BoardRepSelectDTO();
			brsDTO.setContent((String) Blist.get(i)[10]);
			brsDTO.setCDate((Date) Blist.get(i)[11]);
			brsDTO.setUsersNameDTO(uDTO);
			bDTO.setUsersNameDTO(uDTO);
			bDTO.setBoardRepSelectDTO(brsDTO);

			dto.add(bDTO);
		}
		return dto;
	}

	/**
	 * 글 수정폼 출력
	 * 
	 * @param postSeq
	 * @throws FindException
	 */
	public getBoardByBoardTypeDTO detailBoard(Long postSeq) throws FindException {
		Object[] obj = bRepository.detailBoard(postSeq);
		logger.error("대체뭔데: " + obj.length);
		getBoardByBoardTypeDTO dto = new getBoardByBoardTypeDTO();

		// 각각의 값을 꺼내서 DTO에 셋팅
		BigDecimal post_seq = (BigDecimal) obj[1];
		Long resultPost_seq = post_seq.longValue();
		dto.setPostSeq(resultPost_seq);
		BigDecimal Category = (BigDecimal) obj[2];
		int resultCategory = Category.intValue();
		dto.setCategory(resultCategory);
		dto.setTitle((String) obj[3]);
		dto.setContent((String) obj[4]);
		dto.setImgPath((String) obj[5]);
		dto.setCDate((Date) obj[6]);
		dto.setRecommend((Integer) obj[7]);
		dto.setCnt((Integer) obj[8]);
		UsersDTO.UsersNameDTO usersNameDTO = new UsersDTO.UsersNameDTO();
		usersNameDTO.setNickname((String) obj[0]);
		dto.setUsersNameDTO(usersNameDTO);

		return dto;
	}

	/**
	 * 글 수정
	 * 
	 * @author choigeunhyeong
	 * @param board
	 * @throws FindException
	 */
	@Transactional
	public void editBoard(BoardDTO.saveBoardDTO boardDTO, Long postSeq) throws ModifyException{
	    Optional<Board> optB = bRepository.findById(postSeq);

	    if (optB.isPresent()) {
	    	Board b = optB.get();
	    	b.setTitle(boardDTO.getTitle());
	    	b.setContent(boardDTO.getContent());
	    	b.setImgPath(boardDTO.getImgPath());
	    	bRepository.save(b);
	    } else {
	        throw new ModifyException("정상적인 수정이 되지 않았습니다.");
	    }
	}
	
	/**
	 * 게시글 조회수 증가
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @throws FindException
	 */

	@Transactional
	public void updateCnt(Long postSeq) throws ModifyException {
		Optional<Board> optB = bRepository.findById(postSeq);
		if (optB.isPresent()) {
			Integer oldcnt = optB.get().getCnt();
			optB.get().setCnt(oldcnt + 1);
		} else {
			 throw new ModifyException("정상적인 수정이 되지 않았습니다.");
		}
	}
	

	/**
	 * 게시글 삭제
	 * 
	 * @author choigeunhyeong
	 * @param postSeq
	 * @throws RemoveException
	 */
	public void deleteBoard(Long postSeq) throws RemoveException {
		Optional<Board> optB = bRepository.findById(postSeq);
		if (optB.isPresent()) {
			bRepository.deleteById(postSeq);
		} else {
			 throw new RemoveException("정상적인 삭제가 되지 않았습니다.");
		}
	}

	/**
	 * 제목으로 검색
	 * 
	 * @author choigeunhyeong
	 * @param title
	 * @return
	 * @throws FindException
	 */
	public List<Board> findByTitle(String title) throws FindException {
		List<Board> list = bRepository.findByTitleLike(title);
		return list;
	}
	
	/**[메인페이지] 글작성 최신순으로 list를 출력한다.
	 * @author SR
	 * @return List<BoardDTO> 글목록
	 * @throws FindException
	 */
	public List<BoardDTO.selectAllBydateBoardDTO> listByDate() throws FindException{
		List<Object[]> bList =  bRepository.selectAllByDate();
		
		List<BoardDTO.selectAllBydateBoardDTO> bListDto = new ArrayList<>() ;
		
		for(int i=0; i<bList.size(); i++) {
		
			BoardDTO.selectAllBydateBoardDTO bDto = new BoardDTO.selectAllBydateBoardDTO();
			BigDecimal postSeq = (BigDecimal) bList.get(i)[0];
			long convertSeq = postSeq.longValue();
			bDto.setPostSeq(convertSeq);
			BigDecimal category = (BigDecimal) bList.get(i)[2];
			int convertCate = category.intValue();
			bDto.setCategory(convertCate);
			bDto.setTitle((String)bList.get(i)[3]);
			bDto.setContent((String)bList.get(i)[4]);
			bDto.setImgPath((String)bList.get(i)[5]);
			bDto.setCDate((Date)bList.get(i)[6]);
			BigDecimal recommend = (BigDecimal) bList.get(i)[7];
			int convertRec = recommend.intValue();
			bDto.setRecommend(convertRec);
			BigDecimal cnt = (BigDecimal) bList.get(i)[8];
			int convertCnt = cnt.intValue();
			bDto.setCnt(convertCnt);
			
			UsersDTO.selectAllBydateBoardDTO uDto = new UsersDTO.selectAllBydateBoardDTO();
			uDto.setNickname((String)bList.get(i)[1]);
			
			bDto.setUsersDTO(uDto);
			bListDto.add(bDto);
		}
		return bListDto;	

	}

}
