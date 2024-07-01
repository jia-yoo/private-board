package com.example.private_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.private_board.dto.BoardDto;

@Mapper
public interface IBoardDao {

	public List<BoardDto> getAllBoard();
	public BoardDto getBoard(int bno);
	public void regBoard(@Param("board")BoardDto board);
	public void deleteBoard(int bno);
	public List<BoardDto> getMyList(String writer);
	public List<BoardDto> getReceiveList(String receiver);
	public void updateBoard(@Param("board")BoardDto board);
	
	
}
