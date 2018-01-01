package core.legion.mtglife.repository;

import core.legion.mtglife.preferences.Preferences;

public class AppRepository implements Repository {

    private Preferences preferences;

    public AppRepository(Preferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public Preferences getPreferences() {
        return preferences;
    }
}
