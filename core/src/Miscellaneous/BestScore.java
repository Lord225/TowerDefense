package Miscellaneous;

import com.mygdx.game.PlayerState;
import java.io.*;

/**
 * Class used for serialization. It is responsible for keeping the best score (PlayerState object) based on enemies killed in file (.dat)
 */
public class BestScore implements Serializable {

    PlayerState playerState;
    ObjectOutputStream save;
    ObjectInputStream readSave;

    /**
     * Used for loading best score (PlayerState object) from file (.dat)
     */
    public void loadBest() {
        try {
            readSave = new ObjectInputStream(new FileInputStream(Resources.getInstance().bestScoreFile));
            this.playerState = (PlayerState) readSave.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (readSave != null) readSave.close();
            }catch( java.io.IOException e){

            }
        }
    }
    /**
     * Used for saving the best score (PlayerState object) into file (.dat)
     */
    public void saveBest(PlayerState playerState) {
        try {
            save = new ObjectOutputStream(new FileOutputStream(Resources.getInstance().bestScoreFile));
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

    public PlayerState getPlayerState(){
        return playerState;
    }
}
