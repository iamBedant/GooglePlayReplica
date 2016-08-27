package iambedant.com.playmusicreplica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class Home extends AppCompatActivity {


    @BindView(R.id.rv_song_list)
    RecyclerView mRecyclerView;
    Context mContext;
    CardAdapter mAdapter;
    Subscription mSubscription;
    NetworkService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        setupRecyclerView();
        getMusicTracks();

    }

    private void getMusicTracks() {
     
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mAdapter = new CardAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

    }
}
