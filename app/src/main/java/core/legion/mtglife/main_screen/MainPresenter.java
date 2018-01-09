package core.legion.mtglife.main_screen;

import javax.inject.Inject;

import core.legion.mtglife.model.database.Database;
import core.legion.mtglife.preferences.Preferences;

public class MainPresenter implements MainMvp.Presenter {

    private MainMvp.View view;
    private Preferences preferences;
    private Database database;

    @Inject
    public MainPresenter(MainMvp.View view, Preferences preferences, Database database) {
        this.view = view;
        this.preferences = preferences;
        this.database = database;
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

    }

    @Override
    public void onDonateClick() {

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
    public void onNameClick(int pos) {
    }

    @Override
    public void onTotalClick(int pos) {

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
