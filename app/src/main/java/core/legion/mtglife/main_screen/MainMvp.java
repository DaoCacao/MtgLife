package core.legion.mtglife.main_screen;

import android.app.Activity;

import java.util.List;

import core.legion.mtglife.main_screen.adapter.OnPlayerChangeListener;
import core.legion.mtglife.pojo.Player;


public interface MainMvp {

    interface View {
        void navigateToRateScreen();
        void navigateToPlayerScreen();

        void showRateDialog();
        void showRollDiceDialog();
        void showPurchaseDialog();

        void setPlayers(List<Player> players);
        void notifyAdapter();

        //TODO--> i don't like it. to dagger
        Activity getActivity();
    }

    interface Presenter extends OnPlayerChangeListener {
        void onViewInitialized();
        void onViewStopped();

        void onResetClick();
        void onRollDiceClick();
        void onDonateClick();
        void onPurchaseClick(int which);

        void onRateDialogRateClick();
        void onRateDialogAlwaysRatedClick();
    }
}
