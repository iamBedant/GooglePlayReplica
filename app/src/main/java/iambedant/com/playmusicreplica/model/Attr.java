
package iambedant.com.playmusicreplica.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr {

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

}
