package nri.startup.inshort.networkprocess;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
public interface WebService {

    @GET("public/")
    Observable<JsonObject> getNews(@Query("device_id") String deviceId, @Query("page") int page);


}
