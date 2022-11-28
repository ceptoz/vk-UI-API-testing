package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoUploadedResModel {
    private Integer server;
    private String photo;
    private String hash;
}
