package Miscellaneous;

import com.mygdx.game.Map.Map;
import com.mygdx.game.PlayerState;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class BestScore implements Serializable {

    Map map;
    PlayerState playerState;
    //ObjectOutputStream save;
    //ObjectInputStream readSave;
    XMLEncoder save;
    XMLDecoder readSave;


    public BestScore(){

    }

    public BestScore(Map map, PlayerState playerState){
        this.map=map;
        this.playerState = playerState;
    }

    public BestScore loadBest(){
        BestScore getBestScore = null;
        try {
            //readSave = new ObjectInputStream(new FileInputStream(Resources.getInstance().bestScoreFile));
            readSave = new XMLDecoder(new FileInputStream(Resources.getInstance().bestScoreFileXML));
            getBestScore = (BestScore) readSave.readObject();
        }catch(java.io.FileNotFoundException e){
            System.out.println("FileNotFoundError"+ e);
        }finally{
                if(readSave != null) readSave.close();
        }
        return getBestScore;
    }

    public void saveBest(BestScore bestScore) {
        try {
            //save = new ObjectOutputStream(new FileOutputStream(Resources.getInstance().bestScoreFile));
            save = new XMLEncoder(new FileOutputStream(Resources.getInstance().bestScoreFileXML));
            save.writeObject(bestScore);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("FileOutputStream error " + e);
        } finally {
            if (save != null) save.close();
        }
    }

    public Map getMap() {
        return map;
    }
    public PlayerState getPlayerState(){
        return playerState;
    }
    public void setMap(Map map){
        this.map = map;
    }
    public void setPlayerState(PlayerState player){
        this.playerState = player;
    }

}
