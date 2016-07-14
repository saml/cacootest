package saml.cacoo.web;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import saml.cacoo.Diagram;


/**
 * author: saml
 */
interface DiagramsService {
    String PREFIX = "diagrams";

    @FormUrlEncoded
    @POST(PREFIX + "/create.json")
    Call<Diagram> create(@Field("title") String title);

    @GET(PREFIX + ".json")
    Call<DiagramsResponse> all();

    @POST(PREFIX + "/{id}/delete.json")
    Call<Void> delete(@Path("id") String id);
}