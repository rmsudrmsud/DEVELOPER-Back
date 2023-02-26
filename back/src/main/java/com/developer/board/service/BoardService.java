package com.developer.board.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.board.dto.BoardDTO;
import com.developer.board.repository.BoardRepository;
import com.developer.exception.FindException;
import com.developer.users.dto.UsersDTO;
import com.developer.users.repository.UsersRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UsersRepository UsersRepository;
	
	/**[메인페이지] 글작성 최신순으로 list를 출력한다.
	 * @author SR
	 * @return List<BoardDTO>
	 * @throws FindException
	 */
	public List<BoardDTO.selectAllBydateBoardDTO> listByDate() throws FindException{
		List<Object[]> bList =  boardRepository.selectAllByDate();
		
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
