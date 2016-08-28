package iambedant.com.playmusicreplica;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import iambedant.com.playmusicreplica.model.Song;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by @iamBedant on 27-08-2016.
 */
public interface NetworkService {
    String ENDPOINT = "http://demo2820241.mockable.io/";

    @GET("music")
    Observable<List<Song>> getSongs();

}
