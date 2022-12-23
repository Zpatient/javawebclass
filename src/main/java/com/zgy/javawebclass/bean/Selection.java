package com.zgy.javawebclass.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Selection implements Serializable {
    private Integer id;

    private Integer studentid;

    private Integer courseid;

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
        Selection other = (Selection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentid() == null ? other.getStudentid() == null : this.getStudentid().equals(other.getStudentid()))
            && (this.getCourseid() == null ? other.getCourseid() == null : this.getCourseid().equals(other.getCourseid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentid() == null) ? 0 : getStudentid().hashCode());
        result = prime * result + ((getCourseid() == null) ? 0 : getCourseid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentid=").append(studentid);
        sb.append(", courseid=").append(courseid);
        sb.append("]");
        return sb.toString();
    }
}