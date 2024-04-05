package com.saeyan.dto;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*create table heavenload( 
num NUMBER(5) primary key,
pass VARCHAR2(30) not null,
name VARCHAR2(30) not null,
email VARCHAR2(30),
title VARCHAR2(50),
content VARCHAR2(1000),
readcount NUMBER(4) DEFAULT 0,
img varchar2(100),
ref number(5) default 0,
indent number(5) default 0,
step number(5) default 0,
writedate date default sysdate,
constraint fk_name foreign key(name) references christian(id) on delete cascade
);
*/
@Setter
@Getter
@ToString
public class HeavenLoadVO {
	
	private int num;
	private String pass;
	private String name;
	private String email;
	private String title;
	private String content;
	private int readcount;
	private Timestamp writedate; 
	private String pictureUrl;
	private int ref; //참조할 부모글 번호
	private int indent; //원글 답글 구분을 위한 들여쓰기
	private int step; //같은 답글 끼리 순서

}