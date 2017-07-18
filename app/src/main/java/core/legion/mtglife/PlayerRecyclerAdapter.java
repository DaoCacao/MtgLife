package core.legion.mtglife;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Locale;

class PlayerRecyclerAdapter extends RecyclerView.Adapter<PlayerRecyclerAdapter.VH> {

    private Activity activity;
    private Player[] players;

    PlayerRecyclerAdapter(Activity activity) {
        this.activity = activity;
        players = new Player[0];

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    class VH extends RecyclerView.ViewHolder {

        final ImageView backgroungImage;
        final ImageView btnIncreaseLife, btnDecreaseLife;
        final ImageView btnIncreasePoison, btnDecreasePoison;
        final TextView txtLife;

        VH(View itemView) {
            super(itemView);

            backgroungImage = (ImageView) itemView.findViewById(R.id.background_img);

            btnIncreaseLife = (ImageView) itemView.findViewById(R.id.btn_increase_life);
            btnDecreaseLife = (ImageView) itemView.findViewById(R.id.btn_decrease_life);
            btnIncreasePoison = (ImageView) itemView.findViewById(R.id.btn_increase_poison);
            btnDecreasePoison = (ImageView) itemView.findViewById(R.id.btn_decrease_poison);
            txtLife = (TextView) itemView.findViewById(R.id.txt_total);

            btnIncreaseLife.setOnClickListener(v -> {
                players[getAdapterPosition()].increaseLifeBy(1);
                notifyItemChanged(getAdapterPosition());
            });
            btnDecreaseLife.setOnClickListener(v -> {
                players[getAdapterPosition()].decreaseLifeBy(1);
                notifyItemChanged(getAdapterPosition());
            });

            btnIncreasePoison.setOnClickListener(v -> {
                players[getAdapterPosition()].increasePoisonCountBy(1);
                notifyItemChanged(getAdapterPosition());
            });
            btnDecreasePoison.setOnClickListener(v -> {
                players[getAdapterPosition()].decreasePoisonCountBy(1);
                notifyItemChanged(getAdapterPosition());
            });

            txtLife.setOnClickListener(v -> showChangeTotalDialog(getAdapterPosition()));
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout rootLayout = new FrameLayout(parent.getContext());
        rootLayout.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER));

        return new VH(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.player_item, rootLayout));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.backgroungImage.setImageResource(players[position].getBackgroundRes());

        holder.txtLife.setText(
                String.format(Locale.getDefault(), "%d/%d",
                        players[position].getLifeTotal(),
                        players[position].getPoisonCounters()));
    }

    @Override
    public int getItemCount() {
        return players.length;
    }

    private void showChangeTotalDialog(int pos) {
        NumberPicker lifePicker = new NumberPicker(activity);
        lifePicker.setMaxValue(999);
        lifePicker.setValue(20);
        lifePicker.setMinValue(0);

        NumberPicker poisonPicker = new NumberPicker(activity);
        poisonPicker.setMaxValue(10);
        poisonPicker.setValue(0);
        poisonPicker.setMinValue(0);

        LinearLayout pickerLayout = new LinearLayout(activity);
        pickerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pickerLayout.setOrientation(LinearLayout.HORIZONTAL);
        pickerLayout.setGravity(Gravity.CENTER);
        pickerLayout.addView(lifePicker);
        pickerLayout.addView(poisonPicker);

        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);

        Button btnConfirm = new Button(activity);
        btnConfirm.setBackgroundResource(typedValue.resourceId);
        btnConfirm.setText(R.string.txt_done);
        btnConfirm.setTextColor(Color.BLACK);

        LinearLayout rootLayout = new LinearLayout(activity);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.addView(pickerLayout);
        rootLayout.addView(btnConfirm);

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setView(rootLayout)
                .create();

        btnConfirm.setOnClickListener(v -> {
            players[pos].setLifeTotal(lifePicker.getValue());
            players[pos].setPoisonCounters(poisonPicker.getValue());
            notifyItemChanged(pos);
            alertDialog.dismiss();
        });

        alertDialog.show();
    }

    void updatePlayers(int count) {
        players = new Player[count];
        for (int i = 0; i < players.length; i++) players[i] = new Player();
    }
}
