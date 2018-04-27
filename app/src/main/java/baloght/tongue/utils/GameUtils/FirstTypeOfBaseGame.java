package baloght.tongue.utils.GameUtils;

/**
 * Created by baloght on 2018.04.27..
 */

public class FirstTypeOfBaseGame extends BaseGame {

    public static int GAMETYPE = 1;

    private String image1;
    private String answer;

    public FirstTypeOfBaseGame(int gametype, String word, String image0, String image1, String answer) {
        super(gametype, word, image0);
        this.image1 = image1;
        this.answer = answer;
    }

    public String getImage1() {
        return image1;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "FirstTypeOfGame{" +
                "gametype=" + super.getGametype() +
                ", word=" + super.getWord() +
                ", image0=" + super.getImage0() +
                ", image1=" + image1 +
                ", answer=" + answer + '}';
    }

}
