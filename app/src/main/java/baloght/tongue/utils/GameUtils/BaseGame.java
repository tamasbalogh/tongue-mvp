package baloght.tongue.utils.GameUtils;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by baloght on 2018.04.27..
 */

public class BaseGame implements Parcelable{

    private int gametype;
    private String word;
    private Image image0;
    private Image image1;
    private int answer;

    public BaseGame(int gametype, String word, Image image0, Image image1, int answer) {
        this.gametype = gametype;
        this.word = word;
        this.image0 = image0;
        this.image1 = image1;
        this.answer = answer;
    }

    public int getGametype() {
        return gametype;
    }

    public String getWord() {
        return word;
    }

    public Image getImage0() {
        return image0;
    }

    public Image getImage1() {
        return image1;
    }

    public int getAnswer() {
        return answer;
    }

    public void setGametype(int gametype) {
        this.gametype = gametype;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setImage0(Image image0) {
        this.image0 = image0;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    protected BaseGame(Parcel in) {
        gametype = in.readInt();
        word = in.readString();
        image0 = in.readParcelable(Image.class.getClassLoader());
        image1 = in.readParcelable(Image.class.getClassLoader());
        answer = in.readInt();
    }

    public static final Creator<BaseGame> CREATOR = new Creator<BaseGame>() {
        @Override
        public BaseGame createFromParcel(Parcel in) {
            return new BaseGame(in);
        }

        @Override
        public BaseGame[] newArray(int size) {
            return new BaseGame[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gametype);
        dest.writeString(this.word);
        dest.writeParcelable(image0,flags);
        dest.writeParcelable(image1,flags);
        dest.writeInt(this.answer);
    }

    @Override
    public String toString() {
        return "BaseGame{" +
                "gametype=" + gametype +
                ", word='" + word + '\'' +
                ", image0=" + image0.toString() +
                ", image1=" + image1.toString() +
                ", answer='" + answer + '\'' +
                '}';
    }
}
