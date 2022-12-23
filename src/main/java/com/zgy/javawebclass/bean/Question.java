package com.zgy.javawebclass.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName question
 */
@Data
public class Question implements Serializable {
    private Integer id;

    private String question;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime asktime;

    private Integer courseid;

    private Integer studentid;

    private Integer see;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getQuestion() == null ? other.getQuestion() == null : this.getQuestion().equals(other.getQuestion()))
            && (this.getAsktime() == null ? other.getAsktime() == null : this.getAsktime().equals(other.getAsktime()))
            && (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()))
            && (this.getStudentid() == null ? other.getStudentid() == null : this.getStudentid().equals(other.getStudentid()))
            && (this.getSee() == null ? other.getSee() == null : this.getSee().equals(other.getSee()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getQuestion() == null) ? 0 : getQuestion().hashCode());
        result = prime * result + ((getAsktime() == null) ? 0 : getAsktime().hashCode());
        result = prime * result + ((getCourseid() == null) ? 0 : getCourseid().hashCode());
        result = prime * result + ((getStudentid() == null) ? 0 : getStudentid().hashCode());
        result = prime * result + ((getSee() == null) ? 0 : getSee().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", question=").append(question);
        sb.append(", asktime=").append(asktime);
        sb.append(", courseid=").append(courseid);
        sb.append(", studentid=").append(studentid);
        sb.append(", see=").append(see);
        sb.append("]");
        return sb.toString();
    }
}