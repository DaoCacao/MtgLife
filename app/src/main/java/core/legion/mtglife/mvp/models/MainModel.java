package core.legion.mtglife.mvp.models;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.anjlab.android.iab.v3.BillingProcessor;

import java.util.Random;

import core.legion.mtglife.R;
import core.legion.mtglife.enums.Donations;
import core.legion.mtglife.pojo.Player;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Legion on 07.09.2017.
 */

public class MainModel implements MainModelInterface {
    private Context context;
    private SharedPreferences preferences;

    private BillingProcessor billing;

    private String DONATE_1;
    private String DONATE_2;
    private String DONATE_3;
    private String DONATE_4;
    private String LICENSE_KEY;

    private static final String IS_RATED_KEY = "is_rated";
    private static final String COUNT_KEY = "count";
    private static final String TIMER_KEY = "timer";

    private Random random;

    private int playerCount;
    private Player[] players;

    private String[] names;
    private String[] types;
    private Bitmap[] images;

    public MainModel(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE);

        DONATE_1 = context.getString(R.string.donate_1);
        DONATE_2 = context.getString(R.string.donate_2);
        DONATE_3 = context.getString(R.string.donate_3);
        DONATE_4 = context.getString(R.string.donate_4);

        LICENSE_KEY = context.getString(R.string.license_key);

        random = new Random();

        playerCount = preferences.getInt(COUNT_KEY, 2);
        names = context.getResources().getStringArray(R.array.planeswalker_names);
        types = context.getResources().getStringArray(R.array.planeswalker_types);
        images = new Bitmap[types.length];
        for (int i = 0; i < types.length; i++) {
            String img_name = String.format("pw_%s", types[i].toLowerCase());
            int id = context.getResources().getIdentifier(img_name, "drawable", context.getPackageName());
            images[i] = BitmapFactory.decodeResource(context.getResources(), id);
        }
        createPlayers(playerCount);
    }

    @Override
    public void initBilling(BillingProcessor.IBillingHandler donationBilling) {
        billing = new BillingProcessor(context, LICENSE_KEY, donationBilling);
    }

    @Override
    public boolean isAppRated() {
        boolean isRated = preferences.getBoolean(IS_RATED_KEY, false);
        if (!isRated) {
            int count = preferences.getInt(TIMER_KEY, 0);
            count++;
            preferences.edit().putInt(TIMER_KEY, count).apply();
            if (count % 10 == 0) return false;
        }
        return true;
    }

    @Override
    public void setAppAlreadyRated() {
        preferences.edit().putBoolean(IS_RATED_KEY, true).apply();
    }

    @Override
    public Uri getPlayMarketLink() {
        return Uri.parse(context.getString(R.string.link_play_market));
    }

    @Override
    public Player[] getPlayers() {
        return players;
    }

    @Override
    public String[] getNames() {
        return names;
    }

    @Override
    public Bitmap[] getImages() {
        return images;
    }

    @Override
    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public void createPlayers(int count) {
        playerCount = count;
        players = new Player[playerCount];
        for (int i = 0; i < players.length; i++) {
            int index = random.nextInt(types.length);
            players[i] = new Player(names[index], types[index], images[index]);
        }
    }

    @Override
    public void updatePlayer(int pos, int chosenPos) {
        players[pos].setName(names[chosenPos]);
        players[pos].setType(types[chosenPos]);
        players[pos].setBackground(images[chosenPos]);
    }

    @Override
    public void updatePlayerTotal(int pos, int life, int poison, int energy) {
        players[pos].setLifeCounters(life);
        players[pos].setPoisonCounters(poison);
        players[pos].setEnergyCounters(energy);
    }

    @Override
    public void donate(Activity activity, Donations donation) {
        switch (donation) {
            case DONATE_1:
                billing.purchase(activity, DONATE_1);
                break;
            case DONATE_5:
                billing.purchase(activity, DONATE_2);
                break;
            case DONATE_10:
                billing.purchase(activity, DONATE_3);
                break;
            case DONATE_50:
                billing.purchase(activity, DONATE_4);
                break;
        }
    }
}
