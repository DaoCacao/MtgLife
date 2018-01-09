package core.legion.mtglife.mvp.presenters;

import android.app.Activity;

import core.legion.mtglife.mvp.models.MainModelInterface;

/**
 * Created by Legion on 07.09.2017.
 */

public interface MainPresenterInterface<V extends MainViewInterface> {
    void onAttach(V view, MainModelInterface model);

    void onCreateView();

    void onRateDialogRateClick();
    void onRateDialogAlreadyRatedClick();
    void onDonateDialogClick(Activity activity, int which);
    void onChooseDialogItemClick(int pos, int chosenPos);
    void onChangeTotalDialogClick(int pos, int life, int poison, int energy);

    void onNavResetClick();
    void onNavRollDiceClick();
    void onNavDonateClick();

    void loadPlayers();

    void changePlayerCount(int count);

    void changePlaneswalker();
    void changeTotal(int pos);

    void updateLife(int pos, int changing);
    void updatePoison(int pos, int changing);
    void updateEnergy(int pos, int changing);
}
