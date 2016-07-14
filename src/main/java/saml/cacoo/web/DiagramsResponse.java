package saml.cacoo.web;

import saml.cacoo.Diagram;

import java.util.List;

/**
 * author: saml
 */
class DiagramsResponse {
    private List<Diagram> result;
    private Integer count;

    public List<Diagram> getResult() {
        return result;
    }

    public void setResult(List<Diagram> result) {
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
