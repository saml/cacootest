package saml.cacoo;

import java.io.IOException;
import java.util.List;

/**
 * Diagrams access.
 * Mock this for test. Use web.Client that hits Cacoo diagrams API.
 *
 * author: saml
 */
public interface Diagrams {
    List<Diagram> all() throws IOException;
    Diagram create(String title) throws IOException;
    boolean delete(String id) throws IOException;
}
