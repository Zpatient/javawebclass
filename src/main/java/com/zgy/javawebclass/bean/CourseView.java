package com.zgy.javawebclass.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zgy
 * @create 2022-12-20 9:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseView {
    private Integer id;
    private String name;
    private String teacher;
    private String content;
    private Integer score;
}
