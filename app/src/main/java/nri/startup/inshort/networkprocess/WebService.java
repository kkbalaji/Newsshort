package nri.startup.inshort.networkprocess;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
public interface WebService {

    @GET("public/")
    Observable<JsonObject> getNews(@Query("device_id") String deviceId, @Query("page") int page);


}
