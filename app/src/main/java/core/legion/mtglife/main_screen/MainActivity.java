package core.legion.mtglife.main_screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.legion.mtglife.R;
import core.legion.mtglife.dices.DiceRollDialog;
import core.legion.mtglife.main_screen.adapter.PlayerAdapter;
import core.legion.mtglife.pojo.Player;
import dagger.android.support.DaggerAppCompatActivity;

import static android.view.Gravity.START;

public class MainActivity extends DaggerAppCompatActivity implements MainMvp.View {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Inject MainMvp.Presenter presenter;
    @Inject GridLayoutManager gridLayoutManager;
    @Inject PlayerAdapter adapter;
    @Inject @Named(IntentsHelper.TO_PLAYMARKET_SCREEN) Intent rateScreenIntent;
    @Inject @Named(IntentsHelper.TO_PLAYER_SCREEN) Intent playerScreenIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_reset:
                    presenter.onResetClick();
                    break;
                case R.id.menu_roll_dice:
                    presenter.onRollDiceClick();
                    break;
                case R.id.menu_donate:
                    presenter.onDonateClick();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(null);
        recyclerView.setAdapter(adapter);

        presenter.onViewInitialized();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewStopped();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public void navigateToRateScreen() {
        startActivity(rateScreenIntent);
    }

    @Override
    public void navigateToPlayerScreen() {
        startActivity(playerScreenIntent);
    }

    @Override
    public void showRateDialog() {
        //TODO --> replace to module
        new AlertDialog.Builder(this)
                .setTitle(R.string.txt_rate_title)
                .setMessage(R.string.txt_rate_message)
                .setPositiveButton(R.string.txt_rate_positive, (dialog, which) -> presenter.onRateDialogRateClick())
                .setNegativeButton(R.string.txt_rate_negative, (dialog, which) -> dialog.dismiss())
                .setNeutralButton(R.string.txt_rate_neutral, (dialog, which) -> presenter.onRateDialogAlwaysRatedClick())
                .show();
    }

    @Override
    public void showRollDiceDialog() {
        DiceRollDialog diceRollDialog = new DiceRollDialog(this);
        diceRollDialog.show();
    }

    @Override
    public void showPurchaseDialog() {
        //TODO--> to dagger
        new AlertDialog.Builder(this)
                .setTitle("Donate")
                .setIcon(R.drawable.ic_coin)
                .setItems(
                        new String[]{"Thank you!", "Good job!", "Great!", "Awesome!"},
                        (dialog, which) -> presenter.onPurchaseClick(which))
                .show();
    }

    @Override
    public void setPlayers(List<Player> players) {
        adapter.setPlayers(players);
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public void playerCountSwitcher(View v) {
        switch (v.getId()) {
            case R.id.btn_one_player:
                presenter.onBtnOnePlayerClick();
                break;
            case R.id.btn_two_players:
                presenter.onBtnTwoPlayerClick();
                break;
            case R.id.btn_three_players:
                presenter.onBtnThreePlayerClick();
                break;
            case R.id.btn_four_players:
                presenter.onBtnFourPlayerClick();
                break;
        }
        drawerLayout.closeDrawer(START);
    }
}
