package core.legion.mtglife.mvp.models;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import com.anjlab.android.iab.v3.BillingProcessor;

import core.legion.mtglife.enums.Donations;
import core.legion.mtglife.pojo.Player;

/**
 * Created by Legion on 07.09.2017.
 */

public interface MainModelInterface {
    void initBilling(BillingProcessor.IBillingHandler donationBilling);

    boolean isAppRated();
    void setAppAlreadyRated();

    Uri getPlayMarketLink();
    Player[] getPlayers();
    String[] getNames();
    Bitmap[] getImages();

    int getPlayerCount();
    void createPlayers(int count);

    void updatePlayer(int pos, int chosenPos);
    void updatePlayerTotal(int pos, int life, int poison, int energy);
    void updatePlayerLife(int pos, int changing);
    void updatePlayerPoison(int pos, int changing);
    void updatePlayerEnergy(int pos, int changing);

    void donate(Activity activity, Donations donation);
}
