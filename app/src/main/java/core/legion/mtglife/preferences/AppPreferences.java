package core.legion.mtglife.preferences;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import core.legion.mtglife.pojo.Player;

public class AppPreferences implements Preferences {

    private static final String IS_RATED = "isRated";
    private static final String PLAYERS_COUNT = "playersCount";
    private static final String PLAYER = "player";

    private final SharedPreferences sharedPreferences;
    private Gson gson;

    @Inject
    public AppPreferences(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    @Override
    public boolean getIsRated() {
        return sharedPreferences.getBoolean(IS_RATED, false);
    }

    @Override
    public void setIsRated(boolean value) {
        sharedPreferences.edit().putBoolean(IS_RATED, value).apply();
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();

        int count = sharedPreferences.getInt(PLAYERS_COUNT, 2);
        for (int i = 0; i < count; i++) {
            String player = sharedPreferences.getString(PLAYER + i, "");
            if (TextUtils.isEmpty(player)) {
                players.add(new Player());
            } else {
                players.add(gson.fromJson(player, Player.class));
            }
        }

        return players;
    }

    @Override
    public void setPlayers(List<Player> players) {
        int count = players.size();
        sharedPreferences.edit().putInt(PLAYERS_COUNT, count).apply();

        for (int i = 0; i < count; i++) {
            String playerGson = gson.toJson(players.get(i));
            sharedPreferences.edit().putString(PLAYER + i, playerGson).apply();
        }
    }
}
