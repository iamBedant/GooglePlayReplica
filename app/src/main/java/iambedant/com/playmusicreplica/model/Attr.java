
package iambedant.com.playmusicreplica.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr implements Parcelable {

    @SerializedName("rank")
    @Expose
    private String rank;

    /**
     *
     * @return
     *     The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     *     The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }


    protected Attr(Parcel in) {
        rank = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rank);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Attr> CREATOR = new Parcelable.Creator<Attr>() {
        @Override
        public Attr createFromParcel(Parcel in) {
            return new Attr(in);
        }

        @Override
        public Attr[] newArray(int size) {
            return new Attr[size];
        }
    };
}