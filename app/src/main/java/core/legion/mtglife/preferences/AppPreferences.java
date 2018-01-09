package core.legion.mtglife.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class AppPreferences implements Preferences {

    private static final String IS_RATED = "isRated";

    private final SharedPreferences sharedPreferences;

    @Inject
    public AppPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean getIsRated() {
        return sharedPreferences.getBoolean(IS_RATED, false);
    }

    @Override
    public void setIsRated(boolean value) {
        sharedPreferences.edit().putBoolean(IS_RATED, value).apply();
    }
}
