package com.example.private_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private int bno;
	private String title;
	private String content;
	private String receiver;
	private String writer;
	private String regTime;
	
}
