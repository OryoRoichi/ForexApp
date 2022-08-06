package connection;

import java.util.List;

public enum Method {

    LATEST("latest", "?id=%d", 1),
    LATEST_SYMBOL("latest", "?symbol=%s", 1),
    CONVERTER("converter", "?symbol=%s&amount=%d", 2),
    HISTORY("history", "?symbol=%s&period=%s", 2),
    LIST("list", "?type=forex", 0);

    private final String endpoint;

    private final String pattern;

    private final int paramsCount;

    Method(String endpoint, String pattern, int paramsCount) {
        this.endpoint = endpoint;
        this.pattern = pattern;
        this.paramsCount = paramsCount;
    }

    String getEndpoint() {
        return endpoint;
    }

    String getPattern() {
        return pattern;
    }

    int getParamsCount() {
        return paramsCount;
    }

    String joinParams(final List<Object> params) {
        if (paramsCount == 0) {
            return pattern;
        }
        if (params == null || params.size() < paramsCount) {
            throw new ConnectionException("Не достаточно параметров для выполнения запроса");
        }
        return String.format(pattern, params.toArray());
    }
}
