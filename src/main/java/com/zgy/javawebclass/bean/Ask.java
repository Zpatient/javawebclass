package com.zgy.javawebclass.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author zgy
 * @create 2022-12-19 19:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ask {
    private String question;
    private String course;
    private String teacher;
    private Integer studentId;
    private Integer teacherId;
    private Integer questionId;
    private LocalDateTime askTime;
    private String student;
    private Boolean show;
    private Boolean studentShow;
}
