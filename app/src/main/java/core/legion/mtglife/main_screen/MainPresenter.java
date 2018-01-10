package core.legion.mtglife.main_screen;

import javax.inject.Inject;

import core.legion.mtglife.model.billing.Billing;
import core.legion.mtglife.model.database.Database;
import core.legion.mtglife.model.preferences.Preferences;

import static core.legion.mtglife.model.billing.Donations.DONATE_1;
import static core.legion.mtglife.model.billing.Donations.DONATE_2;
import static core.legion.mtglife.model.billing.Donations.DONATE_3;
import static core.legion.mtglife.model.billing.Donations.DONATE_4;

public class MainPresenter implements MainMvp.Presenter {

    private MainMvp.View view;
    private Preferences preferences;
    private Database database;
    private Billing billing;

    @Inject
    public MainPresenter(MainMvp.View view, Preferences preferences, Database database, Billing billing) {
        this.view = view;
        this.preferences = preferences;
        this.database = database;
        this.billing = billing;
    }

    private void checkIsRated() {
        boolean isRated = preferences.getIsRated();
        if (!isRated) {
            view.showRateDialog();
        }
    }

    private void initPlayers() {
        view.setPlayers(database.getPlayers());
        view.notifyAdapter();
    }

    @Override
    public void onViewInitialized() {
        checkIsRated();
        initPlayers();
    }

    @Override
    public void onViewStopped() {
        database.savePlayers();
    }

    @Override
    public void onResetClick() {
        database.resetPlayers();
        view.notifyAdapter();
    }

    @Override
    public void onRollDiceClick() {
        view.showRollDiceDialog();
    }

    @Override
    public void onDonateClick() {
        view.showPurchaseDialog();
    }

    @Override
    public void onPurchaseClick(int which) {
        switch (which) {
            case 0:
                billing.donate(view.getActivity(), DONATE_1);
                break;
            case 1:
                billing.donate(view.getActivity(),DONATE_2);
                break;
            case 2:
                billing.donate(view.getActivity(), DONATE_3);
                break;
            case 3:
                billing.donate(view.getActivity(), DONATE_4);
                break;
        }
    }

    @Override
    public void onRateDialogRateClick() {
        view.navigateToRateScreen();
    }

    @Override
    public void onRateDialogAlwaysRatedClick() {
        preferences.setIsRated(true);
    }

    @Override
    public void onNameClick() {
        view.navigateToPlayerScreen();
    }

    @Override
    public void onTotalClick() {
    }

    @Override
    public void onLifeIncreaseClick(int pos) {
        database.getPlayers().get(pos).increaseLifeCount();
        view.notifyAdapter();
    }

    @Override
    public void onLifeDecreaseClick(int pos) {
        database.getPlayers().get(pos).decreaseLifeCount();
        view.notifyAdapter();
    }

    @Override
    public void onPoisonIncreaseClick(int pos) {
        database.getPlayers().get(pos).increasePoisonCount();
        view.notifyAdapter();
    }

    @Override
    public void onPoisonDecreaseClick(int pos) {
        database.getPlayers().get(pos).decreasePoisonCount();
        view.notifyAdapter();
    }

    @Override
    public void onEnergyIncreaseClick(int pos) {
        database.getPlayers().get(pos).increaseEnergyCount();
        view.notifyAdapter();
    }

    @Override
    public void onEnergyDecreaseClick(int pos) {
        database.getPlayers().get(pos).decreaseEnergyCount();
        view.notifyAdapter();
    }
}
