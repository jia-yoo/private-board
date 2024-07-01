package com.example.private_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.private_board.dto.CommentDto;

@Mapper
public interface ICommentDao {
	
	public List<CommentDto> getCommentList(int bno); // 특정 게시물의 댓글전부를 받아와서 뿌려줌
	public void regComment(@Param("comment") CommentDto comment);
	public void deleteComment(int cno);
	public void updateComment(int cno);
	
	
}
