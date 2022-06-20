package model;

import lombok.Data;

import java.util.List;

@Data
public class PostDTO {

    private String type;
    private int status;
    private List<PostPojo> posts;
    private List<UsersPajo> users;

    @Override
    public String toString() {
        return "PostDTO{" +
                "type='" + type + '\'' +
                ", status=" + status +
                ", posts=" + posts +
                ", users=" + users +
                '}';
    }
}
