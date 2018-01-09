package core.legion.mtglife.model.database;

import java.util.List;

import core.legion.mtglife.pojo.Player;

public interface Database {
    List<Player> getPlayers();
    void savePlayers();

    void resetPlayers();
}
