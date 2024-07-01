package com.example.private_board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	
	private int cno;
	private String content;
	private String writer;
	private int bno;
	private String regTime; 
}
