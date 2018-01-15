package core.legion.mtglife.model.database;

import java.util.List;

import core.legion.mtglife.pojo.Player;

public interface Database {
    List<Player> getPlaneswalkers();
    List<Player> getPlayers();
    void setCurrentPos(int pos);
    void savePlayers();

    void resetPlayers();

    void setCurrentPlayer(int position);

    void changePlayersCount(int count);
}
