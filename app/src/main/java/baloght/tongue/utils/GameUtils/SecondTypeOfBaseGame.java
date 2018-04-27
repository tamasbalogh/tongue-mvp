package baloght.tongue.utils.GameUtils;

/**
 * Created by baloght on 2018.04.27..
 */

public class SecondTypeOfBaseGame extends BaseGame {

    public static int GAMETYPE = 2;

    private int answerLength;
    private char[] possibilities;

    public SecondTypeOfBaseGame(int gametype, String word, String image0, char[] possibilities) {
        super(gametype, word, image0);
        this.answerLength = word.length();
        this.possibilities = possibilities;
    }

    public int getAnswerLength() {
        return answerLength;
    }

    public char[] getPossibilities() {
        return possibilities;
    }

    private String printArray(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (char c : possibilities){
            sb.append(c + " ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SecondTypeOfGame{" +
                "gametype=" + super.getGametype() +
                ", word=" + super.getWord() +
                ", image0=" + super.getImage0() +
                ", answerLength=" + answerLength +
                ", possibilities=" + printArray() + '}';
    }
}
