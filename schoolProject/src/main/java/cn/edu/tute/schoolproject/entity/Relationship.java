package cn.edu.tute.schoolproject.entity;

public class Relationship {
    private Integer id;
    private Integer userId;
    private Integer reminderId;

    public Relationship() {
    }

    public Relationship(Integer id, Integer userId, Integer reminderId) {
        this.id = id;
        this.userId = userId;
        this.reminderId = reminderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relationship that = (Relationship) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(userId, that.userId) &&
                java.util.Objects.equals(reminderId, that.reminderId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, userId, reminderId);
    }

    @Override
    public String toString() {
        return "Relationship{" +
                "id=" + id +
                ", userId=" + userId +
                ", reminderId=" + reminderId +
                '}';
    }
}