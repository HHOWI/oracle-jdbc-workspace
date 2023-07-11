
CREATE SEQUENCE SEQ_MATCHINGTEXT_POST; -- 매칭글SEQ
CREATE SEQUENCE SEQ_MATCHINGTEXTLIKE_LIKE; -- 매칭글좋아요SEQ 
CREATE SEQUENCE SEQ_CHATROOM_NUMBER; -- 채팅방번호(채팅)


 CREATE TABLE CHAT (  ---------------------------------------------------------- 채팅
    CHAT_CONTENT VARCHAR2(200),                    -- 채팅내용
    CHAT_SENTTIME TIMESTAMP,                       -- 발송시각
    CHAT_PHOTO BLOB,                               -- 채팅사진, URL
    CHAT_STARTDATE DATE DEFAULT SYSDATE,           -- 대화시작날짜시간
    CHAT_ENDDATE DATE DEFAULT SYSDATE              -- 대화종료날짜시간
);
SELECT * FROM CHAT;
DROP TABLE CHAT;


CREATE TABLE CHATUSERINFO (  ------------------------------------------------- 채팅참여유저정보
    CHATROOM_NUMBER NUMBER,                                     -- 채팅방번호
    CHAT_USERID VARCHAR2(50),                                   -- 채팅참여유저아이디
    READ_STATUS VARCHAR(1) DEFAULT 'N' CHECK(READ_STATUS IN('Y', 'N'))          -- 읽음표시(Y/N)
);
SELECT * FROM CHATUSERINFO;
DROP TABLE CHATUSERINFO;

CREATE TABLE MATCHINGTEXT (    ---------------------------------  매칭글
    MATCHINGTEXT_TITLE VARCHAR2(100) NOT NULL,             -- 매칭글제목
    MATCHINGTEXT_CONTENT VARCHAR2(2000) NOT NULL,          -- 매칭글내용
    MATCHINGTEXT_POSTTIME TIMESTAMP,                       -- 매칭글작성시각
    MATCHINGTEXT_VIEWS NUMBER,                             -- 매칭글조회수
    MATCHINGTEXT_TEXTADDRESS VARCHAR2(200) NOT NULL,       -- 매칭글주소
    MATCHINGTEXT_MEDIAURL VARCHAR2(200) NOT NULL,          -- 매칭글사진동영상URL
    MATCHING_ACCEPT VARCHAR(1) DEFAULT 'N' CHECK(MATCHING_ACCEPT IN('Y', 'N')),                          -- 매칭승락(Y/N)
    NOTICE VARCHAR(1) DEFAULT 'N' CHECK(NOTICE IN('Y', 'N'))                                            -- 공지사항(Y/N) 
);
SELECT * FROM MATCHINGTEXT;
DROP TABLE MATCHINGTEXT;


CREATE TABLE MATCHINGTEXTLIKE (  ------------------------------------ 매칭글좋아요
      LIKETIME TIMESTAMP,                                       -- 매칭글좋아요누른시간
      MATCHINGTEXTLIKE_USERID VARCHAR2(50)                     -- 아이디
);
SELECT * FROM MATCHINGTEXTLIKE;
DROP TABLE MATCHINGTEXTLIKE;

ALTER TABLE MATCHINGTEXT ADD CONSTRAINT USER_ID_FK FOREIGN KEY(CATEGORYSEQ) REFERENCES CATEGORY;    --  매칭글(카테고리SEQ)
ALTER TABLE MATCHINGTEXT ADD CONSTRAINT USER_ID_FK FOREIGN KEY(AUTHORUSERID) REFERENCES USERINFO;   -- 매칭글(작성자아디)

ALTER TABLE MATCHINGTEXTLIKE ADD CONSTRAINT MATCHINGTEXT_POST_FK FOREIGN KEY(MATCHINGTEXTLIKE) REFERENCES MATCHINGTEXT; -- 매칭글좋아요(매칭글SEQ)
ALTER TABLE MATCHINGTEXTLIKE ADD CONSTRAINT USERID_FK FOREIGN KEY(MATCHINGTEXTLIKE) REFERENCES USERINFO; -- 매칭글좋아요(아이디) 

ALTER TABLE CHATUSERINFO ADD CONSTRAINT FK_CHATUSERINFO_CHATROOM FOREIGN KEY(CHATROOM_NUMBER) REFERENCES CHAT(CHATROOM_NUMBER);

