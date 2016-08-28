package iambedant.com.playmusicreplica;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import iambedant.com.playmusicreplica.model.Song;

public class Detail extends AppCompatActivity {

    public static final String EXTRA_SONG = "_song_name";
    Context mContext;
    @BindView(R.id.iv_cover)
    ImageView mImageViewCover;

    @BindView(R.id.tv_title)
    TextView mTextViewTitle;
    @BindView(R.id.tv_artist)
    TextView getmTextViewArtist;
    @BindView(R.id.tv_rank)
    TextView mTextViewRank;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Song mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mContext = this;
//        setExitSharedElementCallback(fabLoginSharedElementCallback);
        getWindow().getSharedElementReturnTransition().addListener(shotReturnHomeListener);
        mSong = getIntent().getParcelableExtra(EXTRA_SONG);


        populateView();
    }

    private void populateView() {
        fab.setVisibility(View.INVISIBLE);
        Glide.with(mContext)
                .load(mSong.getImage().get(3).getText())
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        fab.setVisibility(View.VISIBLE);
                        animateFab();
                        return false;
                    }
                })
                .centerCrop()
                .into(mImageViewCover);
     fab.setScaleX(0);
        fab.setScaleY(0);
        postponeEnterTransition();
        mTextViewTitle.setText(mSong.getName());
        getmTextViewArtist.setText(mSong.getArtist().getName());
        mTextViewRank.setText("Playcount " + mSong.getPlaycount());
    }

    private void animateFab() {

        Animator showFab = ObjectAnimator.ofPropertyValuesHolder(fab,
                PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f));
        showFab.setStartDelay(300L);
        showFab.setDuration(300L);
        showFab.start();
    }



    private Transition.TransitionListener shotReturnHomeListener = new AnimUtils
            .TransitionListenerAdapter() {
        @Override
        public void onTransitionStart(Transition transition) {
            super.onTransitionStart(transition);
            // hide the fab as for some reason it jumps position??  TODO work out why
            fab.setVisibility(View.INVISIBLE);
            // fade out the "toolbar" & list as we don't want them to be visible during return
            // animation
        }
    };

//    private SharedElementCallback fabLoginSharedElementCallback = new SharedElementCallback() {
//        @Override
//        public Parcelable onCaptureSharedElementSnapshot(View sharedElement,
//                                                         Matrix viewToGlobalMatrix,
//                                                         RectF screenBounds) {
//            // store a snapshot of the fab to fade out when morphing to the login dialog
//            int bitmapWidth = Math.round(screenBounds.width());
//            int bitmapHeight = Math.round(screenBounds.height());
//            Bitmap bitmap = null;
//            if (bitmapWidth > 0 && bitmapHeight > 0) {
//                bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
//                sharedElement.draw(new Canvas(bitmap));
//            }
//            return bitmap;
//        }
//    };

}
