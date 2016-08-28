
package iambedant.com.playmusicreplica.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Song implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("image")
    @Expose
    private List<Image> image = new ArrayList<Image>();
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The playcount
     */
    public String getPlaycount() {
        return playcount;
    }

    /**
     * @param playcount The playcount
     */
    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    /**
     * @return The listeners
     */
    public String getListeners() {
        return listeners;
    }

    /**
     * @param listeners The listeners
     */
    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    /**
     * @return The mbid
     */
    public String getMbid() {
        return mbid;
    }

    /**
     * @param mbid The mbid
     */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The streamable
     */
    public String getStreamable() {
        return streamable;
    }

    /**
     * @param streamable The streamable
     */
    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    /**
     * @return The artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @param artist The artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * @return The image
     */
    public List<Image> getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(List<Image> image) {
        this.image = image;
    }

    /**
     * @return The attr
     */
    public Attr getAttr() {
        return attr;
    }

    /**
     * @param attr The @attr
     */
    public void setAttr(Attr attr) {
        this.attr = attr;
    }


    protected Song(Parcel in) {
        name = in.readString();
        playcount = in.readString();
        listeners = in.readString();
        mbid = in.readString();
        url = in.readString();
        streamable = in.readString();
        artist = (Artist) in.readValue(Artist.class.getClassLoader());
        if (in.readByte() == 0x01) {
            image = new ArrayList<Image>();
            in.readList(image, Image.class.getClassLoader());
        } else {
            image = null;
        }
        attr = (Attr) in.readValue(Attr.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(playcount);
        dest.writeString(listeners);
        dest.writeString(mbid);
        dest.writeString(url);
        dest.writeString(streamable);
        dest.writeValue(artist);
        if (image == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(image);
        }
        dest.writeValue(attr);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}