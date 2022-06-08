package Miscellaneous;

import com.mygdx.game.Map.Map;

import java.io.Serializable;

public class BestScore implements Serializable {

    Map map;
    float points;
    int gold;
    String userName;

    BestScore(Map map, float points, int gold, String userName){
        this.map=map;
        this.points=points;
        this.gold = gold;
        this.userName = userName;
    }

}
