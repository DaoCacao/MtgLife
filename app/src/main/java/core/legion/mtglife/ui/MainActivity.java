package core.legion.mtglife.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import core.legion.mtglife.R;

import static trikita.anvil.DSL.*;

public class MainActivity extends AppCompatActivity {

    private BillingProcessor billing;

    private int columns, count;

    private DrawerLayout drawerLayout;

    private GridLayoutManager gridLayoutManager;
    private PlayerAdapter adapter;
    private RecyclerView recyclerView;

    private String DONATE_1;
    private String DONATE_2;
    private String DONATE_3;
    private String DONATE_4;

    private String LICENSE_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        DONATE_1 = getString(R.string.donate_1);
        DONATE_2 = getString(R.string.donate_2);
        DONATE_3 = getString(R.string.donate_3);
        DONATE_4 = getString(R.string.donate_4);

        LICENSE_KEY = getString(R.string.license_key);

        checkForRate();

        initNavigationView();
        initRecyclerView();
        initBilling();
    }

    private void checkForRate() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        if (!preferences.getBoolean("isRated", false)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.txt_rate_title)
                    .setMessage(R.string.txt_rate_message)
                    .setPositiveButton(R.string.txt_rate_positive, (dialog, which) -> {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(getString(R.string.link_play_market)));
                        startActivity(intent);
                    })
                    .setNegativeButton(R.string.txt_rate_negative, (dialog, which) -> {
                    })
                    .setNeutralButton(R.string.txt_rate_neutral, (dialog, which) ->
                            preferences.edit().putBoolean("isRated", true).apply())
                    .show();
        }
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        drawerLayout.openDrawer(START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!billing.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_reset:
                    changePlayerCount(count, columns);
                    break;
                case R.id.menu_roll_dice:
                    showRollDiceDialog();
                    break;
                case R.id.menu_flip_coin:
                    showFlipCoinDialog();
                    break;
                case R.id.menu_purchase:
                    showPurchaseDialog();
                    break;
            }
            drawerLayout.closeDrawer(START);
            return true;
        });
    }

    private void showPurchaseDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Donate")
                .setIcon(R.drawable.ic_coin)
                .setItems(
                        new String[]{"Thank you!", "Good job!", "Great!", "Awesome!"},
                        (dialog, which) -> {
                            switch (which) {
                                case 0:
                                    billing.purchase(MainActivity.this, DONATE_1);
                                    break;
                                case 1:
                                    billing.purchase(MainActivity.this, DONATE_2);
                                    break;
                                case 2:
                                    billing.purchase(MainActivity.this, DONATE_3);
                                    break;
                                case 3:
                                    billing.purchase(MainActivity.this, DONATE_4);
                                    break;
                            }
                        })
                .show();
    }

    private void initBilling() {
        billing = new BillingProcessor(this, LICENSE_KEY, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
                Toast.makeText(MainActivity.this, "Purchased!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPurchaseHistoryRestored() {

            }

            @Override
            public void onBillingError(int errorCode, @Nullable Throwable error) {
                Toast.makeText(MainActivity.this, "Something wrong, try later", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBillingInitialized() {

            }
        });
        if (!BillingProcessor.isIabServiceAvailable(this))
            Toast.makeText(this, "Purchase is not available", Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerView() {
        gridLayoutManager = new GridLayoutManager(this, 1);
        adapter = new PlayerAdapter(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(null);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        changePlayerCount(2, 2);
    }

    public void playerCountSwitcher(View v) {
        switch (v.getId()) {
            case R.id.btn_one_player:
                changePlayerCount(1, 1);
                break;
            case R.id.btn_two_players:
                changePlayerCount(2, 2);
                break;
            case R.id.btn_three_players:
                changePlayerCount(3, 3);
                break;
            case R.id.btn_four_players:
                changePlayerCount(4, 2);
                break;
        }
        drawerLayout.closeDrawer(START);
    }

    private void changePlayerCount(int newCount, int newColumns) {
        count = newCount;
        columns = newColumns;

        gridLayoutManager.setSpanCount(columns);
        adapter.updatePlayers(count);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void showRollDiceDialog() {
        new DiceRollDialog(this).show();
    }

    private void showFlipCoinDialog() {
        Toast.makeText(this, "to be continued", Toast.LENGTH_SHORT).show();
    }
}
