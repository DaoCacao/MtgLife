package core.legion.mtglife.model.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import core.legion.mtglife.R;
import core.legion.mtglife.pojo.Player;

public class MockDatabase implements Database {

    private String[] names;
    private String[] types;
    private Drawable[] images;

    private List<Player> planeswalkers;
    private List<Player> players;

    private int currentPos;

    @Inject
    public MockDatabase(Context context) {
        String[] planeswalkers = context.getResources().getStringArray(R.array.planeswalkers);
        names = new String[planeswalkers.length];
        types = new String[planeswalkers.length];
        images = new Drawable[planeswalkers.length];

        for (int i = 0; i < planeswalkers.length; i++) {
            String[] planeswalker = planeswalkers[i].split("; ");
            names[i] = planeswalker[0];
            types[i] = planeswalker[1];
            int id = context.getResources().getIdentifier("pw_" + types[i].toLowerCase(), "drawable", context.getPackageName());
            images[i] = ContextCompat.getDrawable(context, id);
        }
    }

    @Override
    public List<Player> getPlaneswalkers() {
        if (planeswalkers == null) {
            planeswalkers = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                planeswalkers.add(new Player(names[i], types[i], images[i]));
            }
        }
        return planeswalkers;
    }

    @Override
    public List<Player> getPlayers() {
        if (players == null) {
            int index1 = new Random().nextInt(names.length);
            int index2 = new Random().nextInt(names.length);

            players = new ArrayList<>();
            players.add(new Player(names[index1], types[index1], images[index1]));
            players.add(new Player(names[index2], types[index2], images[index2]));
        }
        return players;
    }

    @Override
    public void setCurrentPos(int pos) {
        currentPos = pos;
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

    @Override
    public void setCurrentPlayer(int position) {
        players.get(currentPos).setName(names[position]);
        players.get(currentPos).setType(types[position]);
        players.get(currentPos).setBackground(images[position]);
    }

    @Override
    public void changePlayersCount(int count) {
        if (players.size() > count) {
            while (players.size() != count) {
                players.remove(players.size() - 1);
            }
        } else {
            while (players.size() != count) {
                int index = new Random().nextInt(names.length);
                players.add(new Player(names[index], types[index], images[index]));
            }
        }
    }

}
