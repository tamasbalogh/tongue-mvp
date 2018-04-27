package baloght.tongue.utils.GameUtils;

/**
 * Created by baloght on 2018.04.27..
 */

public abstract class BaseGame {
    public static int counter = 0;
    private int gametype;
    private String word;
    private String image0;

    public BaseGame(int gametype, String word, String image0) {
        this.counter++;
        this.gametype = gametype;
        this.word = word;
        this.image0 = image0;
    }

    public int getGametype() {
        return gametype;
    }

    public String getWord() {
        return word;
    }

    public String getImage0() {
        return image0;
    }

    @Override
    public String toString() {
        return "BaseGame{" + "gametype=" + gametype + ", word=" + word + ", image0=" + image0 + '}';
    }
}
