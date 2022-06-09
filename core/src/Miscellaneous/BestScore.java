package Miscellaneous;

import com.mygdx.game.Map.Map;
import com.mygdx.game.PlayerState;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class BestScore implements Serializable {

    Map map;
    PlayerState playerState;
    ObjectOutputStream save;
    ObjectInputStream readSave;
    //XMLEncoder save;
   // XMLDecoder readSave;


    public void loadBest() {
        try {
            readSave = new ObjectInputStream(new FileInputStream(Resources.getInstance().bestScoreFile));
            //readSave = new XMLDecoder(new FileInputStream(Resources.getInstance().bestScoreFileXML));
            this.playerState = (PlayerState) readSave.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("FileNotFoundError" + e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readSave != null) readSave.close();
            }catch( java.io.IOException e){

            }
        }
    }

    public void saveBest(PlayerState playerState) {
        try {
            save = new ObjectOutputStream(new FileOutputStream(Resources.getInstance().bestScoreFile));
            //save = new XMLEncoder(new FileOutputStream(Resources.getInstance().bestScoreFileXML));
            save.writeObject(playerState);
        } catch (IOException e) {
            System.out.println("FileOutputStream error " + e);
        } finally {
            try {
                if (save != null) save.close();
            }catch(java.io.IOException e){

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


}
