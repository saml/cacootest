package saml.cacoo;


import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import saml.Env;

import java.nio.file.Paths;

/**
 * author: saml
 */
public class Main {
    public static void main(String[] args) {
        final String apiKey = System.getenv("CACOO_API_KEY");
        final String staticDir = Env.asString("STATIC_DIR", "public");
        final int port = Env.asInt("PORT", 8080);

        final ResourceHandler renderStatic = Handlers.resource(
                new PathResourceManager(Paths.get(staticDir).toAbsolutePath(), 100)
        ).setDirectoryListingEnabled(true);


        final Undertow app = Undertow.builder()
                .addHttpListener(port, null)
                .setHandler(Handlers.path().addPrefixPath("/static", renderStatic))
                .build();
        app.start();

    }
}
