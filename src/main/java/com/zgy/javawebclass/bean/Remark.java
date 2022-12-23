package com.zgy.javawebclass.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName remark
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remark implements Serializable {
    private Integer id;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private LocalDateTime remarktime;

    private Integer questionid;

    private Integer ownerid;

    private Integer targetid;

    private Integer isread;


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
        Remark other = (Remark) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getRemarktime() == null ? other.getRemarktime() == null : this.getRemarktime().equals(other.getRemarktime()))
            && (this.getQuestionid() == null ? other.getQuestionid() == null : this.getQuestionid().equals(other.getQuestionid()))
            && (this.getOwnerid() == null ? other.getOwnerid() == null : this.getOwnerid().equals(other.getOwnerid()))
            && (this.getTargetid() == null ? other.getTargetid() == null : this.getTargetid().equals(other.getTargetid()))
            && (this.getIsread() == null ? other.getIsread() == null : this.getIsread().equals(other.getIsread()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getRemarktime() == null) ? 0 : getRemarktime().hashCode());
        result = prime * result + ((getQuestionid() == null) ? 0 : getQuestionid().hashCode());
        result = prime * result + ((getOwnerid() == null) ? 0 : getOwnerid().hashCode());
        result = prime * result + ((getTargetid() == null) ? 0 : getTargetid().hashCode());
        result = prime * result + ((getIsread() == null) ? 0 : getIsread().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", content=").append(content);
        sb.append(", remarktime=").append(remarktime);
        sb.append(", questionid=").append(questionid);
        sb.append(", ownerid=").append(ownerid);
        sb.append(", targetid=").append(targetid);
        sb.append(", isread=").append(isread);
        sb.append("]");
        return sb.toString();
    }
}