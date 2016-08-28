package iambedant.com.playmusicreplica;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iambedant.com.playmusicreplica.model.Song;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Home extends AppCompatActivity {
    String TAG = Home.class.getSimpleName();

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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new UnauthorisedInterceptor(context))
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkService.ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        NetworkService networkService = retrofit.create(NetworkService.class);
        Observable<List<Song>> obj = networkService.getSongs();
        mSubscription = obj.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Song>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "on Error called");
                    }

                    @Override
                    public void onNext(List<Song> songs) {
                        mAdapter.addItems(songs);
                    }
                });
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(30));
        mAdapter = new CardAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);

    }
}
