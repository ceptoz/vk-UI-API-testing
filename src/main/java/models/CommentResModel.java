package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResModel {
    private Integer comment_id;
    private ArrayList<Object> parents_stack;
}
