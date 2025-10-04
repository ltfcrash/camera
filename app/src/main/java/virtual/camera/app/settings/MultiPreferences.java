package virtual.camera.app.settings;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import virtual.camera.app.app.App;

/**
 * A minimal in-project replacement for the removed MultiPreferences helper.
 * Provides a singleton access point backed by SharedPreferences.
 */
public final class MultiPreferences {
    private static final String PREFERENCES_NAME = "virtual_camera_preferences";
    private static volatile MultiPreferences sInstance;

    private final SharedPreferences sharedPreferences;

    private MultiPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static MultiPreferences getInstance() {
        if (sInstance == null) {
            synchronized (MultiPreferences.class) {
                if (sInstance == null) {
                    sInstance = new MultiPreferences(App.getContext());
                }
            }
        }
        return sInstance;
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public void setInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public void setLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public String getString(String key, @Nullable String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void setString(String key, @Nullable String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
