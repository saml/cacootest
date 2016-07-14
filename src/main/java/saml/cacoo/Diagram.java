package saml.cacoo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * author: saml
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Diagram {
    private String url;
    private String title;
    private String diagramId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(String diagramId) {
        this.diagramId = diagramId;
    }

}
