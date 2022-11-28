package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WhoLikedResModel {
    public Integer count;
    public ArrayList<Integer> items;
}
