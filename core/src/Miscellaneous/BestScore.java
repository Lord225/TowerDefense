package Miscellaneous;

import com.mygdx.game.Map.Map;
import com.mygdx.game.PlayerState;

import java.io.*;

public class BestScore implements Serializable {

    Map map;
    PlayerState playerState;
    ObjectOutputStream save;
    ObjectInputStream readSave;

    public BestScore(){

    }

    public BestScore(Map map, PlayerState playerState){
        this.map=map;
        this.playerState = playerState;
    }

    public BestScore loadBest(){
        BestScore getBestScore = null;
        try {
            readSave = new ObjectInputStream(new FileInputStream(Resources.getInstance().bestScoreFile));
            getBestScore = (BestScore) readSave.readObject();
        }catch(java.io.FileNotFoundException e){
            System.out.println("FileNotFoundError"+ e);
        }catch(java.io.IOException e){
            System.out.println("ObjectInputStream error"+ e);
        }catch(java.lang.ClassNotFoundException e){
            System.out.println("ClassNotFoundException"+ e);
        }finally{
            try {
                if(readSave != null) readSave.close();
            }catch(java.io.IOException e){

            }
        }
        return getBestScore;
    }

    public void saveBest(BestScore bestScore) {
        try {
            save = new ObjectOutputStream(new FileOutputStream(Resources.getInstance().bestScoreFile));
            save.writeObject(bestScore);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("FileOutputStream error " + e);
        } catch (java.io.IOException e) {
            System.out.println("ObjectOutputStream error" + e);
        } finally {
            try {
                if (save != null) save.close();
            } catch (java.io.IOException e) {
            }
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
