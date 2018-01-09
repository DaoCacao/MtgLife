package core.legion.mtglife.mvp.presenters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import core.legion.mtglife.enums.Donations;
import core.legion.mtglife.mvp.models.MainModelInterface;
import core.legion.mtglife.pojo.Player;

/**
 * Created by Legion on 07.09.2017.
 */

public class MainPresenter<V extends MainViewInterface> implements MainPresenterInterface<V> {
    private MainViewInterface mView;
    private MainModelInterface mModel;

    private BillingProcessor.IBillingHandler donationBilling = new BillingProcessor.IBillingHandler() {
        @Override
        public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

        }

        @Override
        public void onPurchaseHistoryRestored() {

        }

        @Override
        public void onBillingError(int errorCode, @Nullable Throwable error) {

        }

        @Override
        public void onBillingInitialized() {

        }
    };

    @Override
    public void onAttach(V view, MainModelInterface model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onCreateView() {
        mModel.initBilling(donationBilling);
        if (!mModel.isAppRated()) mView.showRateDialog();
    }

    @Override
    public void onRateDialogRateClick() {
        mView.navigateToPlayMarket(mModel.getPlayMarketLink());
    }

    @Override
    public void onRateDialogAlreadyRatedClick() {
        mModel.setAppAlreadyRated();
    }

    @Override
    public void onDonateDialogClick(Activity activity, int which) {
        switch (which) {
            case 0:
                mModel.donate(activity, Donations.DONATE_1);
                break;
            case 1:
                mModel.donate(activity, Donations.DONATE_5);
                break;
            case 2:
                mModel.donate(activity, Donations.DONATE_10);
                break;
            case 3:
                mModel.donate(activity, Donations.DONATE_50);
                break;
        }
    }

    @Override
    public void onChooseDialogItemClick(int pos, int chosenPos) {
        mModel.updatePlayer(pos, chosenPos);
        mView.updateAdapterItem(pos);
    }

    @Override
    public void onChangeTotalDialogClick(int pos, int life, int poison, int energy) {
        mModel.updatePlayerTotal(pos, life, poison, energy);
        mView.updateAdapterItem(pos);
    }

    @Override
    public void onNavResetClick() {
        mModel.createPlayers(mModel.getPlayerCount());
        showPlayers();
    }

    @Override
    public void onNavRollDiceClick() {
        mView.showRollDiceDialog();
    }

    @Override
    public void onNavDonateClick() {
        mView.showDonateDialog(new String[]{
                Donations.DONATE_1.getMsg(),
                Donations.DONATE_5.getMsg(),
                Donations.DONATE_10.getMsg(),
                Donations.DONATE_50.getMsg()});
    }

    @Override
    public void loadPlayers() {
        mModel.createPlayers(mModel.getPlayerCount());
        showPlayers();
    }

    @Override
    public void changePlayerCount(int count) {
        mModel.createPlayers(count);
        showPlayers();
    }

    @Override
    public void changePlaneswalker() {
        mView.showChoosePlayerDialog(mModel.getNames(), mModel.getImages());
    }

    @Override
    public void changeTotal(int pos) {
        Player player = mModel.getPlayers()[pos];
        mView.showChangeTotalDialog(player.getLifeCounters(), player.getPoisonCounters(), player.getEnergyCounters());
    }

    @Override
    public void updateLife(int pos, int changing) {
        mModel.updatePlayerLife(pos, changing);
        mView.updateAdapterItem(pos);
    }

    @Override
    public void updatePoison(int pos, int changing) {
        mModel.updatePlayerPoison(pos, changing);
        mView.updateAdapterItem(pos);
    }

    @Override
    public void updateEnergy(int pos, int changing) {
        mModel.updatePlayerEnergy(pos, changing);
        mView.updateAdapterItem(pos);
    }

    private void showPlayers() {
        Player[] players = mModel.getPlayers();
        switch (players.length) {
            case 1:
                mView.updatePlayers(players, 1);
                break;
            case 2:
                mView.updatePlayers(players, 2);
                break;
            case 3:
                mView.updatePlayers(players, 1);
                break;
            case 4:
                mView.updatePlayers(players, 2);
                break;
        }
    }
}
