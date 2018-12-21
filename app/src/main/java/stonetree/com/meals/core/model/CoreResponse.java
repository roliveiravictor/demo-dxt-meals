package stonetree.com.meals.core.model;

public class CoreResponse {

    private Error error;

    private String result;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
