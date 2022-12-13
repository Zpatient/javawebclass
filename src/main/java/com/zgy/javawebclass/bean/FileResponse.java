package com.zgy.javawebclass.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zgy
 * @create 2022-12-13 19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse implements Serializable {
    Boolean success;
    Date time;
    Object data;
    Date elapsedTime;
}
