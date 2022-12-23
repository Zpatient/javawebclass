package com.zgy.javawebclass.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author zgy
 * @create 2022-12-19 18:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String question;
    private String course;
    private String teacher;
    private String student;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime time;
    private Integer questionId;
    private String ownername;
    private String targetname;
    private Integer remarkid;
    private Integer ownerid;
    private Integer targetid;
    private String content;
}
