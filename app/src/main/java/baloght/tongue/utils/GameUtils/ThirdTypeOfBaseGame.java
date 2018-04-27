package baloght.tongue.utils.GameUtils;

/**
 * Created by baloght on 2018.04.27..
 */

public class ThirdTypeOfBaseGame extends BaseGame {
    public static int GAMETYPE = 3;

    private int answerLength;
    private String[] possibilities; //every string contains 3 characters

    public ThirdTypeOfBaseGame(int gametype, String word, String image0, String[] possibilities) {
        super(gametype, word, image0);
        this.answerLength = word.length();
        this.possibilities = possibilities;
    }

    public char[] getCharacters(int round){
        char[] array = new char[3];

        for(int i=0; i < possibilities[round].length(); i++){
            array[i]=possibilities[round].charAt(i);
        }

        return array;
    }

    public int getAnswerLength() {
        return answerLength;
    }

    public String[] getPossibilities() {
        return possibilities;
    }

    private String printArray(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (String c : possibilities){
            sb.append(c + " ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ThirdTypeOfGame{" +
                "gametype=" + super.getGametype() +
                ", word=" + super.getWord() +
                ", image0=" + super.getImage0() +
                ", answerLength=" + answerLength +
                ", possibilities=" + printArray() + '}';
    }
}
