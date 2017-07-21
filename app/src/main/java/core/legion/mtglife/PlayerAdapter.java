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
import android.widget.SeekBar;
import android.widget.TextView;

class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.VH> {

    private Activity activity;
    private Player[] players;

    PlayerAdapter(Activity activity) {
        this.activity = activity;
        players = new Player[0];

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    class VH extends RecyclerView.ViewHolder {

        final ImageView backgroundImage;

        final TextView txtLifeTotal;
        final ImageView btnIncreaseLife, btnDecreaseLife;

        final TextView txtPoisonTotal;
        final ImageView btnIncreasePoison, btnDecreasePoison;

//        final TextView txtEnergyTotal;
//        final ImageView btnIncreaseEnergy, btnDecreaseEnergy;

        VH(View itemView) {
            super(itemView);

            backgroundImage = (ImageView) itemView.findViewById(R.id.background_img);

            btnIncreaseLife = (ImageView) itemView.findViewById(R.id.btn_increase_life);
            btnDecreaseLife = (ImageView) itemView.findViewById(R.id.btn_decrease_life);
            btnIncreasePoison = (ImageView) itemView.findViewById(R.id.btn_increase_poison);
            btnDecreasePoison = (ImageView) itemView.findViewById(R.id.btn_decrease_poison);
//            btnIncreaseEnergy = (ImageView) itemView.findViewById(R.id.btn_increase_energy);
//            btnDecreaseEnergy = (ImageView) itemView.findViewById(R.id.btn_decrease_energy);

            txtLifeTotal = (TextView) itemView.findViewById(R.id.txt_life_total);
            txtPoisonTotal = (TextView) itemView.findViewById(R.id.txt_poison_total);
//            txtEnergyTotal = (TextView) itemView.findViewById(R.id.txt_energy_total);

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

//            btnIncreaseEnergy.setOnClickListener(v -> {
//                players[getAdapterPosition()].increaseEnergyCountBy(1);
//                notifyItemChanged(getAdapterPosition());
//            });
//            btnDecreaseEnergy.setOnClickListener(v -> {
//                players[getAdapterPosition()].decreaseEnergyCountBy(1);
//                notifyItemChanged(getAdapterPosition());
//            });

            View.OnClickListener onTotalClick = v -> showChangeTotalDialog(getAdapterPosition());
            txtLifeTotal.setOnClickListener(onTotalClick);
            txtPoisonTotal.setOnClickListener(onTotalClick);
//            txtEnergyTotal.setOnClickListener(onTotalClick);
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
        holder.backgroundImage.setImageResource(players[position].getBackgroundRes());

        holder.txtLifeTotal.setText(String.valueOf(players[position].getLifeCounters()));
        holder.txtPoisonTotal.setText(String.valueOf(players[position].getPoisonCounters()));
//        holder.txtEnergyTotal.setText(String.valueOf(players[position].getEnergyCounters()));
    }

    @Override
    public int getItemCount() {
        return players.length;
    }

    private void showChangeTotalDialog(int pos) {
        LinearLayout rootLayout = new LinearLayout(activity);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);

        LinearLayout pickerLayout = new LinearLayout(activity);
        pickerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pickerLayout.setOrientation(LinearLayout.HORIZONTAL);
        pickerLayout.setGravity(Gravity.CENTER);
        rootLayout.addView(pickerLayout);

        int currentLife = players[pos].getLifeCounters();
        NumberPicker lifePicker = new NumberPicker(activity);
        lifePicker.setMaxValue(999);
        lifePicker.setValue(currentLife);
        lifePicker.setMinValue(0);
        pickerLayout.addView(lifePicker);

        int currentPoison = players[pos].getPoisonCounters();
        NumberPicker poisonPicker = new NumberPicker(activity);
        poisonPicker.setMaxValue(10);
        poisonPicker.setValue(currentPoison);
        poisonPicker.setMinValue(0);
        pickerLayout.addView(poisonPicker);

//        int currentEnergy = players[pos].getEnergyCounters();
//        NumberPicker energyPicker = new NumberPicker(activity);
//        energyPicker.setMaxValue(999);
//        energyPicker.setValue(currentEnergy);
//        energyPicker.setMinValue(0);
//        pickerLayout.addView(energyPicker);

        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);

        Button btnConfirm = new Button(activity);
        btnConfirm.setBackgroundResource(typedValue.resourceId);
        btnConfirm.setText(R.string.txt_done);
        btnConfirm.setTextColor(Color.BLACK);
        rootLayout.addView(btnConfirm);

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setView(rootLayout)
                .create();

        btnConfirm.setOnClickListener(v -> {
            players[pos].setLifeCounters(lifePicker.getValue());
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
