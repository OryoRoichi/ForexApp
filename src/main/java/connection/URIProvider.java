package connection;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;

public class URIProvider {

    private final String url;

    private final String key;

    public URIProvider() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ConnectionConstants.RESOURCE_NAME);
        this.url = resourceBundle.getString(ConnectionConstants.BASE_URL);
        this.key = resourceBundle.getString(ConnectionConstants.ACCESS_KEY);
    }

    URI getURI(final Method method, final List<Object> params) throws URISyntaxException {
        return new URI(addAccessKey(url.concat(method.getEndpoint()).concat(method.joinParams(params))));
    }

    private String addAccessKey(final String in) {
        return in.concat("&access_key=").concat(key);
    }
}
