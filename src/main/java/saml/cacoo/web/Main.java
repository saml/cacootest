package saml.cacoo.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saml.Env;
import saml.cacoo.Diagram;
import saml.cacoo.Diagrams;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * author: saml
 */
public class Main {
    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        final String apiKey = System.getenv("CACOO_API_KEY");
        final int port = Env.asInt("PORT", 8080);

        final Diagrams diagrams = Client.build(apiKey);

        Spark.port(port);

        Spark.get("/", (request, response) -> {
                    final Map<String, Object> m = new HashMap<>();
                    m.put("diagrams", diagrams.all());
                    return new ModelAndView(m, "diagrams.html");
                }
                , new FreeMarkerEngine());

        Spark.post("/", (request, response) -> {
            final String title = request.queryParams("title");
            if (title == null) {
                response.status(400);
                return "Need title";
            }
            final Diagram diagram = diagrams.create(title);
            response.redirect("/");
            return "Created: " + diagram.getDiagramId();
        });

        Spark.post("/diagrams/:diagramId/delete", (request, response) -> {
            final String diagramId = request.params("diagramId");
            if (!diagrams.delete(diagramId)) {
                response.status(500);
                return "Failed to delete: " + diagramId;
            }
            response.redirect("/");
            return "Deleted: " + diagramId;
        });

        Spark.exception(Exception.class, (exception, request, response) ->
                log.error("", exception));


    }
}
