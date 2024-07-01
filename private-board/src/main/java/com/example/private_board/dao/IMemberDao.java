package com.example.private_board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.private_board.dto.MemberDto;

@Mapper
public interface IMemberDao {

	public List<MemberDto> getAllMember();
	public MemberDto getMember(String id);
	public void regMember(@Param("member")MemberDto member);
	
	public void updateMember(@Param("pw") String pw, @Param("name") String name, @Param("id") String id);
}
