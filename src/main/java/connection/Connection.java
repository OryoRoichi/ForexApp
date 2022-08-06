package connection;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

//https://fcsapi.com/document/forex-api
public class Connection {

    private final URIProvider uriProvider;

    public Connection() {
        uriProvider = new URIProvider();
    }

    public HttpResponse<String> get(final Method method, List<Object> params) {
        try {
            HttpRequest request = HttpRequest.newBuilder(uriProvider.getURI(method, params)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }
}
