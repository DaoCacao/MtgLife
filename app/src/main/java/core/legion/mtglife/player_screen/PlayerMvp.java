package core.legion.mtglife.player_screen;

import core.legion.mtglife.player_screen.adapter.OnPlayerClickListener;

public interface PlayerMvp {

    interface View {
        void closeActivity();
    }
    interface Presenter extends OnPlayerClickListener {}
}
