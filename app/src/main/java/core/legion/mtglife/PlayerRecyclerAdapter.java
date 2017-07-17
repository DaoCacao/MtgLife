package core.legion.mtglife;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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

    private Context context;
    private Player[] players;

    PlayerRecyclerAdapter(Context context, int count) {
        this.context = context;
        players = new Player[count];
        for (int i = 0; i < players.length; i++) players[i] = new Player();
    }

    class VH extends RecyclerView.ViewHolder {

        final ImageView btnIncreaseLife, btnDecreaseLife;
        final ImageView btnIncreasePoison, btnDecreasePoison;
        final TextView txtLife;

        VH(View itemView) {
            super(itemView);

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
        return new VH(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.player_item, new FrameLayout(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.txtLife.setText(
                String.format(Locale.getDefault(), "%d/%d",
                        players[position].getLifeTotal(),
                        players[position].getPoisonCounters())
        );
    }

    @Override
    public int getItemCount() {
        return players.length;
    }

    private void showChangeTotalDialog(int pos) {
        NumberPicker lifePicker = new NumberPicker(context);
        lifePicker.setMaxValue(999);
        lifePicker.setValue(20);
        lifePicker.setMinValue(0);

        NumberPicker poisonPicker = new NumberPicker(context);
        poisonPicker.setMaxValue(10);
        poisonPicker.setValue(0);
        poisonPicker.setMinValue(0);

        LinearLayout pickerLayout = new LinearLayout(context);
        pickerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        pickerLayout.setOrientation(LinearLayout.HORIZONTAL);
        pickerLayout.setGravity(Gravity.CENTER);
        pickerLayout.addView(lifePicker);
        pickerLayout.addView(poisonPicker);

        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);

        Button btnConfirm = new Button(context);
        btnConfirm.setBackgroundResource(typedValue.resourceId);
        btnConfirm.setText("Done");
        btnConfirm.setTextColor(Color.BLACK);

        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setGravity(Gravity.CENTER);
        rootLayout.addView(pickerLayout);
        rootLayout.addView(btnConfirm);

        AlertDialog alertDialog = new AlertDialog.Builder(context)
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

    private String[] generateArray(int minValue, int maxValue) {
        String[] array = new String[maxValue - minValue];
        for (int i = 0, value = minValue; value < maxValue; i++, value++) array[i] = Integer.toString(value);
        return array;
    }
}
