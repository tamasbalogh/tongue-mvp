package baloght.tongue.utils.GameUtils;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private int id;
    private String uri;

    public Image(int id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    protected Image(Parcel in) {
        id = in.readInt();
        uri = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(uri);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                '}';
    }
}
