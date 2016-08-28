
package iambedant.com.playmusicreplica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("size")
    @Expose
    private String size;

    /**
     *
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     *     The #text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     *     The size
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @param size
     *     The size
     */
    public void setSize(String size) {
        this.size = size;
    }


    protected Image(Parcel in) {
        text = in.readString();
        size = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(size);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}