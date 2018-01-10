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
import android.view.View;

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
        setContentView(R.layout.main_layout);
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

//    public void showChoosePlayerDialog(String[] names, Bitmap[] images) {
//        AlertDialog planeswalkerDialog = new AlertDialog.Builder(this).create();
//        planeswalkerDialog.setView(new RenderableView(this) {
//            @Override
//            public void view() {
//                listView(() -> {
//                    size(MATCH, MATCH);
//                    divider(null);
//                    dividerHeight(0);
//                    adapter(new RenderableAdapter() {
//                        @Override
//                        public void view(int index) {
//                            frameLayout(() -> {
//                                size(MATCH, dip(80));
//
//                                imageView(() -> {
//                                    size(MATCH, MATCH);
//                                    scaleType(ImageView.ScaleType.CENTER_CROP);
//                                    gravity(CENTER);
//                                    imageBitmap(images[index]);
//                                });
//
//                                textView(() -> {
//                                    size(MATCH, MATCH);
//                                    textSize(sip(32));
//                                    textColor(Color.WHITE);
//                                    gravity(CENTER);
//                                    text(names[index]);
//                                });
//
//                                onClick(v -> {
//                                    mPresenter.onChooseDialogItemClick(adapter.getCurrentPos(), index);
//                                    planeswalkerDialog.dismiss();
//                                });
//                            });
//                        }
//
//                        @Override
//                        public int getCount() {
//                            return names.length;
//                        }
//
//                        @Override
//                        public Object getItem(int position) {
//                            return null;
//                        }
//                    });
//                });
//            }
//        });
//        planeswalkerDialog.show();
//    }
//    private void showChangeNameDialog(int pos) {
//        LinearLayout rootLayout = new LinearLayout(context);
//        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        rootLayout.setOrientation(LinearLayout.VERTICAL);
//        rootLayout.setGravity(Gravity.CENTER);
//
//        EditText edName = new EditText(context);
//        edName.setGravity(Gravity.CENTER);
//        edName.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
//        rootLayout.addView(edName);
//
//        TypedValue typedValue = new TypedValue();
//        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
//
//        Button btnConfirm = new Button(context);
//        btnConfirm.setBackgroundResource(typedValue.resourceId);
//        btnConfirm.setText(R.string.txt_done);
//        btnConfirm.setTextColor(Color.BLACK);
//        rootLayout.addView(btnConfirm);
//
//        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setView(rootLayout)
//                .create();
//
//        btnConfirm.setOnClickListener(v -> {
//            players.get(pos).setName(edName.getText().toString().trim());
//            notifyItemChanged(pos);
//            alertDialog.dismiss();
//        });
//
//        alertDialog.show();
//    }
//
//    private void showChangeTotalDialog(int pos) {
//        LinearLayout rootLayout = new LinearLayout(context);
//        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        rootLayout.setOrientation(LinearLayout.VERTICAL);
//        rootLayout.setGravity(Gravity.CENTER);
//
//        LinearLayout pickerLayout = new LinearLayout(context);
//        pickerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        pickerLayout.setOrientation(LinearLayout.HORIZONTAL);
//        pickerLayout.setGravity(Gravity.CENTER);
//        rootLayout.addView(pickerLayout);
//
//        int currentLife = players.get(pos).getLifeCounters();
//        NumberPicker lifePicker = new NumberPicker(context);
//        lifePicker.setMaxValue(999);
//        lifePicker.setValue(currentLife);
//        lifePicker.setMinValue(0);
//        pickerLayout.addView(lifePicker);
//
//        int currentPoison = players.get(pos).getPoisonCounters();
//        NumberPicker poisonPicker = new NumberPicker(context);
//        poisonPicker.setMaxValue(10);
//        poisonPicker.setValue(currentPoison);
//        poisonPicker.setMinValue(0);
//        pickerLayout.addView(poisonPicker);
//
//        int currentEnergy = players.get(pos).getEnergyCounters();
//        NumberPicker energyPicker = new NumberPicker(context);
//        energyPicker.setMaxValue(999);
//        energyPicker.setValue(currentEnergy);
//        energyPicker.setMinValue(0);
//        pickerLayout.addView(energyPicker);
//
//        TypedValue typedValue = new TypedValue();
//        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
//
//        Button btnConfirm = new Button(context);
//        btnConfirm.setBackgroundResource(typedValue.resourceId);
//        btnConfirm.setText(R.string.txt_done);
//        btnConfirm.setTextColor(Color.BLACK);
//        rootLayout.addView(btnConfirm);
//
//        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setView(rootLayout)
//                .create();
//
//        btnConfirm.setOnClickListener(v -> {
//            players.get(pos).setLifeCounters(lifePicker.getValue());
//            players.get(pos).setPoisonCounters(poisonPicker.getValue());
//            players.get(pos).setEnergyCounters(energyPicker.getValue());
//            notifyItemChanged(pos);
//            alertDialog.dismiss();
//        });
//
//        alertDialog.show();
//    }
//
//
//
//    @Deprecated
//    public void playerCountSwitcher(View v) {
//        switch (v.getId()) {
//            case R.id.btn_one_player:
//                changePlayerCount(1, 1);
//                break;
//            case R.id.btn_two_players:
//                changePlayerCount(2, 2);
//                break;
//            case R.id.btn_three_players:
//                changePlayerCount(3, 3);
//                break;
//            case R.id.btn_four_players:
//                changePlayerCount(4, 2);
//                break;
//        }
//        drawerLayout.closeDrawer(START);
//    }
//
//    @Deprecated
//    private void showRollDiceDialog() {
//        new DiceRollDialog(this).show();
//    }
//
//    @Deprecated
//    private void showFlipCoinDialog() {
//        Toast.makeText(this, "to be continued", Toast.LENGTH_SHORT).show();
//    }
}
