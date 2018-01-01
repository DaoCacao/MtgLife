package core.legion.mtglife.main_screen;

import java.util.List;

import javax.inject.Inject;

import core.legion.mtglife.R;
import core.legion.mtglife.models.Player;
import core.legion.mtglife.repository.Repository;

public class MainPresenter implements MainMvp.Presenter {

    private Repository repository;
    private MainMvp.View view;

    private List<Player> players;

    @Inject
    public MainPresenter(Repository repository, MainMvp.View view) {
        this.repository = repository;
        this.view = view;
    }

    private void checkIsRated() {
        boolean isRated = repository.getPreferences().getIsRated();
        if (!isRated) {
            view.showRateDialog();
        }
    }

    private void initPlayers() {
        players = repository.getPreferences().getPlayers();
        view.setPlayers(players);
        view.notifyAdapter();
    }

    @Override
    public void onViewInitialized() {
        checkIsRated();
        initPlayers();
    }

    @Override
    public void onViewStopped() {
        repository.getPreferences().setPlayers(players);
    }

    @Override
    public void onNavigationItemClick(int id) {
        switch (id) {
            case R.id.menu_reset:
                for (Player player : players) {
                    player.reset();
                }
//                changePlayerCount(count, columns);
                break;
            case R.id.menu_roll_dice:
//                showRollDiceDialog();
                break;
            case R.id.menu_flip_coin:
//                showFlipCoinDialog();
                break;
            case R.id.menu_purchase:
//                showPurchaseDialog();
                break;
        }
    }

    @Override
    public void onRateDialogRateRClick() {
        view.navigateToRateScreen();
    }

    @Override
    public void onRateDialogAlwaysRatedClick() {
        repository.getPreferences().setIsRated(true);
    }

    @Override
    public void onNameClick(int pos) {
    }

    @Override
    public void onTotalClick(int pos) {

    }

    @Override
    public void onLifeIncreaseClick(int pos) {
        players.get(pos).increaseLifeCount();
        view.notifyAdapter();
    }

    @Override
    public void onLifeDecreaseClick(int pos) {
        players.get(pos).decreaseLifeCount();
        view.notifyAdapter();
    }

    @Override
    public void onPoisonIncreaseClick(int pos) {
        players.get(pos).increasePoisonCount();
        view.notifyAdapter();
    }

    @Override
    public void onPoisonDecreaseClick(int pos) {
        players.get(pos).decreasePoisonCount();
        view.notifyAdapter();
    }

    @Override
    public void onEnergyIncreaseClick(int pos) {
        players.get(pos).increaseEnergyCount();
        view.notifyAdapter();
    }

    @Override
    public void onEnergyDecreaseClick(int pos) {
        players.get(pos).decreaseEnergyCount();
        view.notifyAdapter();
    }
}
