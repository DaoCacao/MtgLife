package core.legion.mtglife;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import trikita.anvil.Anvil;
import trikita.anvil.BaseDSL;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;
import static trikita.anvil.BaseDSL.*;

public class MainActivity extends AppCompatActivity {

    private int columns, count;

    private ImageView backgroundImage;

    private GridLayoutManager gridLayoutManager;
    private PlayerRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        columns = 2;
        count = 2;

        FrameLayout rootLayout = new FrameLayout(this);

        backgroundImage = new ImageView(this);
        backgroundImage.setImageResource(R.drawable.background_guilds);
        backgroundImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        gridLayoutManager = new GridLayoutManager(this, columns);

        adapter = new PlayerRecyclerAdapter(this);
        adapter.updatePlayers(count);

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(null);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        rootLayout.addView(backgroundImage, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        rootLayout.addView(recyclerView, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));

        setContentView(rootLayout, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        bottomSheetDialog = new BottomSheetDialog(this) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.bottom_sheet_dialog);
            }
        };
        bottomSheetDialog.show();
        return false;
    }

    public void onBottomSheetBtnClick(View view) {
        switch (view.getId()) {
            case R.id.txt_player_count:
                showPlayerCountPicker();
                break;
            case R.id.txt_theme_change:
                showThemeChangeDialog();
                break;
        }
        bottomSheetDialog.dismiss();
    }

    private void showPlayerCountPicker() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        NumberPicker playerCountPicker = new NumberPicker(this);
        playerCountPicker.setMaxValue(4);
        playerCountPicker.setValue(2);
        playerCountPicker.setMinValue(1);

        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);

        Button btnConfirm = new Button(this);
        btnConfirm.setBackgroundResource(typedValue.resourceId);
        btnConfirm.setText(R.string.txt_done);
        btnConfirm.setTextColor(Color.BLACK);
        btnConfirm.setOnClickListener(v -> {
            switch (playerCountPicker.getValue()) {
                case 1:
                    changePlayerCount(1, 1);
                    break;
                case 2:
                    changePlayerCount(2, 2);
                    break;
                case 3:
                    changePlayerCount(3, 3);
                    break;
                case 4:
                    changePlayerCount(4, 2);
                    break;
            }
            alertDialog.dismiss();
        });

        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.addView(playerCountPicker);
        rootLayout.addView(btnConfirm);

        alertDialog.setView(rootLayout);
        alertDialog.show();
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

    private void showThemeChangeDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setView(new RenderableView(this) {
            @Override
            public void view() {
                linearLayout(() -> {
                    size(MATCH, MATCH);
                    orientation(LinearLayout.VERTICAL);
                    gravity(Gravity.CENTER);

                    createTextView(
                            R.string.txt_plain,
                            R.drawable.back_white,
                            alertDialog);
                    createTextView(
                            R.string.txt_island,
                            R.drawable.back_blue,
                            alertDialog);
                    createTextView(
                            R.string.txt_swamp,
                            R.drawable.back_black,
                            alertDialog);
                    createTextView(
                            R.string.txt_mountain,
                            R.drawable.back_red,
                            alertDialog);
                    createTextView(
                            R.string.txt_forest,
                            R.drawable.back_green,
                            alertDialog);
                    createTextView(
                            R.string.txt_default,
                            R.drawable.background_guilds,
                            alertDialog);
                });
            }
        });
        alertDialog.show();
    }

    private void createTextView(int titleRes, int imgRes, AlertDialog alertDialog) {
        textView(() -> {
            size(MATCH, WRAP);
            gravity(Gravity.CENTER);
            padding(dip(8));
            text(titleRes);
            textSize(BaseDSL.sip(18));
            textColor(Color.BLACK);
            onClick(v -> {
                backgroundImage.setImageResource(imgRes);
                alertDialog.dismiss();
            });
        });
    }
}
