package com.zgy.javawebclass.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author zgy
 * @create 2022-12-13 19:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String baseurl;
    private List<String> messages;
    private List<String> files;
    private List<Boolean> isImages;
    private Integer code;
}
