package model;

import lombok.Data;

@Data
public class PostPojo {

    private int userId;
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "PostModel{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}