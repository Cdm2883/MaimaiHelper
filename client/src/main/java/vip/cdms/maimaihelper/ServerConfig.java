package vip.cdms.maimaihelper;

import android.content.SharedPreferences;

public class ServerConfig {
    private final SharedPreferences preferences;
    public ServerConfig(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    private static final String KEY_URL = "url";
    public String getUrl() {
        return preferences.getString(KEY_URL, "http://0.0.0.0:16643/maimaihelper");
    }
    public void setUrl(String url) {
        preferences.edit().putString(KEY_URL, url).apply();
    }

    private static final String KEY_TOKEN = "token";
    public String getToken() {
        return preferences.getString(KEY_TOKEN, "DO_NOT_USE_DEFAULT_TOKEN");
    }
    public void setToken(String token) {
        preferences.edit().putString(KEY_TOKEN, token).apply();
    }
}
