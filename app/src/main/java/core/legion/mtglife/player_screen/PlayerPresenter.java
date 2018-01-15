package core.legion.mtglife.player_screen;

import javax.inject.Inject;

import core.legion.mtglife.model.database.Database;

public class PlayerPresenter implements PlayerMvp.Presenter {

    private PlayerMvp.View view;
    private Database database;

    @Inject
    public PlayerPresenter(PlayerMvp.View view, Database database) {
        this.view = view;
        this.database = database;
    }

    @Override
    public void onClick(int position) {
        database.setCurrentPlayer(position);
        view.closeActivity();
    }
}
