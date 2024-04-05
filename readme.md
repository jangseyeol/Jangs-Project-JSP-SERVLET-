
#커넥션 풀
<Resource name="jdbc/myoracle" auth="Container"
type="javax.sql.DataSource" driverClassName="oracle.jdbc.OracleDriver"
url="jdbc:oracle:thin:@127.0.0.1:1521:xe"
username="ezen" password="1234" maxTotal="20" maxIdle="10"
 maxWaitMillis="-1"/>
 
#DB
create table board( 
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
comment_count number(10) default 0,
like_it number(10) default 0,
constraint fk_name foreign key(name) references christian(id) on delete cascade
);
create SEQUENCE board_seq start with 1 increment by 1;

create table christian(
id varchar2(10) not null,
pass varchar2(10) not null,
name varchar2(24),
enter DATE DEFAULT SYSDATE,
gender CHAR(1) DEFAULT '1',
phone VARCHAR2(30),
PRIMARY KEY(id)
);


create table heavenload( 
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

create sequence heavenload_seq start with 1 increment by 1;

create table board_comment(
comment_num number(5) primary key,
board_num number(5),
id varchar2(30),
content varchar2(600),
writedate date default sysdate,
constraint fk_comment foreign key(board_num) references board(num),
constraint fk_name2 foreign key(id) references christian(id)
);

create sequence COMMENT_SEQ start with 1 increment by 1;
