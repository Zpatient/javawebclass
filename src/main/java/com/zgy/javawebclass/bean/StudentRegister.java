package com.zgy.javawebclass.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zgy
 * @create 2022-12-23 19:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegister {
    private String name;
    private Integer id;
    private String password;
    private String confirmPassword;
}
