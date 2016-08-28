package iambedant.com.playmusicreplica;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_music, viewGroup, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, int position) {
        final Song song = mList.get(position);
        Glide.with(mContext)
                .load(song.getImage().get(2).getText())
                .into(holder.mAlbumImage);
        holder.mTextViewArtist.setText(song.getArtist().getName());
        holder.mTextViewTitle.setText(song.getName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Detail.class);
                intent.putExtra(Detail.EXTRA_SONG, song);
                Pair<View, String> p1 = Pair.create((View) holder.mAlbumImage, mContext.getString(R.string.image_cover_transition_name));
                Pair<View, String> p2 = Pair.create((View) holder.mCardView, mContext.getString(R.string.container_transition_name));
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext, p1, p2);
                mContext.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        ImageView mAlbumImage;
        @BindView(R.id.tv_artist)
        TextView mTextViewArtist;
        @BindView(R.id.tv_title)
        TextView mTextViewTitle;
        @BindView(R.id.album_card_view)
        CardView mCardView;

        public CardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


    public void addItems(List<Song> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
