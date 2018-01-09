package core.legion.mtglife.model.database;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import core.legion.mtglife.pojo.Player;

public class MockDatabase implements Database {

    private List<Player> players;

    @Inject
    public MockDatabase() {
        players = new ArrayList<>();
        players.add(new Player("Dummy, the dummiest", "Dummy", Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)));
        players.add(new Player("Dummy, the dummiest", "Dummy", Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)));
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void savePlayers() {

    }

    @Override
    public void resetPlayers() {
        for (Player player : players) {
            player.reset();
        }
    }

}
