package com.saeyan.dto;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CommentVO {
	private int comment_num;
	private int board_num;
	private String id;
	private String content;
	private Timestamp writedate;
	
}
