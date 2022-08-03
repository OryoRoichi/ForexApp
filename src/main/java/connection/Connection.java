package connection;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.CurrencyPair;
import model.ResponseBody;
import model.mapping.CurrencyPairMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Connection {

    private final ObjectMapper objectMapper;

    private final String url;

    private final String key;

    public Connection() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ConnectionConstants.RESOURCE_NAME);
        this.url = resourceBundle.getString(ConnectionConstants.BASE_URL);
        this.key = resourceBundle.getString(ConnectionConstants.ACCESS_KEY);
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                .registerModule(new JavaTimeModule());
    }

    public List<CurrencyPair> getPair(final Integer id) {
        try {
            HttpRequest request = HttpRequest.newBuilder(getLatestURI(id)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), ResponseBody.class)
                    .getResponse()
                    .stream()
                    .map(val -> CurrencyPairMapper.toPair((Map) val))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    private URI getLatestURI(final Integer id) throws URISyntaxException {
        return new URI(url.concat("latest").concat("?id=").concat(String.valueOf(id)).concat("&access_key=").concat(key));
    }
}
