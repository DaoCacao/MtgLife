package core.legion.mtglife.main_screen;

import java.util.List;

import core.legion.mtglife.main_screen.adapter.OnPlayerChangeListener;
import core.legion.mtglife.pojo.Player;


public interface MainMvp {

    interface View {
        void navigateToRateScreen();

        void showRateDialog();

        void setPlayers(List<Player> players);
        void notifyAdapter();
    }

    interface Presenter extends OnPlayerChangeListener {
        void onViewInitialized();
        void onViewStopped();

        void onResetClick();
        void onRollDiceClick();
        void onDonateClick();

        void onRateDialogRateClick();
        void onRateDialogAlwaysRatedClick();
    }
}
