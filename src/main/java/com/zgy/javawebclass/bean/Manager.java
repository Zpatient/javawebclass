package com.zgy.javawebclass.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zgy
 * @create 2022-12-13 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    Integer id;
    String name;
    String phoneNumber;
    Integer branchCompanyId;

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", branchCompanyId=" + branchCompanyId +
                '}';
    }
}
