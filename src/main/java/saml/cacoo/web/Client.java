package saml.cacoo.web;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import saml.cacoo.Diagram;
import saml.cacoo.Diagrams;

import java.io.IOException;
import java.util.List;

/**
 * author: saml
 */
public class Client implements Diagrams {
    private DiagramsService diagramsService;

    // Builds Cacoo API client.
    public static Client build(String apiKey) {
        final OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request req = chain.request();
                    HttpUrl url = req.url().newBuilder().addQueryParameter("apiKey", apiKey).build();
                    req = req.newBuilder().url(url).build();
                    return chain.proceed(req);
                })
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cacoo.com/api/v1/")
                .callFactory(httpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        final Client client = new Client();
        client.diagramsService = retrofit.create(DiagramsService.class);
        return client;
    }

    @Override
    public List<Diagram> all() throws IOException {
        final Response<DiagramsResponse> response = diagramsService.all().execute();
        final DiagramsResponse diagrams = response.body();
        return diagrams.getResult();
    }

    @Override
    public Diagram create(String title) throws IOException {
        final Response<Diagram> response = diagramsService.create(title).execute();
        return response.body();
    }

    @Override
    public boolean delete(String id) throws IOException {
        final Response<Void> response = diagramsService.delete(id).execute();
        return 200 == response.code();
    }
}
