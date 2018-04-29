package baloght.tongue.utils.GameUtils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by baloght on 2018.04.27..
 */

public class FirstTypeOfBaseGame extends BaseGame implements Parcelable {

    public static int GAMETYPE = 1;

    private String image1;
    private String answer;

    public FirstTypeOfBaseGame(int gametype, String word, String image0, String image1, String answer) {
        super(gametype, word, image0);
        this.image1 = image1;
        this.answer = answer;
    }

    protected FirstTypeOfBaseGame(Parcel in) {
        super(in.readInt(),in.readString(),in.readString());
        image1 = in.readString();
        answer = in.readString();
    }

    public static final Creator<FirstTypeOfBaseGame> CREATOR = new Creator<FirstTypeOfBaseGame>() {
        @Override
        public FirstTypeOfBaseGame createFromParcel(Parcel in) {
            return new FirstTypeOfBaseGame(in);
        }

        @Override
        public FirstTypeOfBaseGame[] newArray(int size) {
            return new FirstTypeOfBaseGame[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(super.getGametype());
        dest.writeString(super.getWord());
        dest.writeString(super.getImage0());
        dest.writeString(getImage1());
        dest.writeString(getAnswer());
    }
}
