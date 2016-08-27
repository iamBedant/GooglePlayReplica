package iambedant.com.playmusicreplica;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import iambedant.com.playmusicreplica.model.Song;

/**
 * Created by @iamBedant on 28-08-2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {


    List<Song> mList = new ArrayList<>();
    Context mContext;

    public CardAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_music,viewGroup,false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
       Song song = mList.get(position);
        Glide.with(mContext).load(song.getImage().get(1).getText()).into(holder.mAlbumImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public class CardHolder extends RecyclerView.ViewHolder {
        private final ImageView mAlbumImage;
        private int mAlbumPosition;

        public CardHolder(View itemView) {
            super(itemView);
            mAlbumImage = (ImageView) itemView.findViewById(R.id.iv_cover);
        }

    }


    public void addItems(List<Song> list){
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
