package core.legion.mtglife.main_screen.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.legion.mtglife.R;
import core.legion.mtglife.pojo.Player;

public class VH extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_type) TextView txtType;

    @BindView(R.id.frame_type) FrameLayout frameType;

    @BindView(R.id.img_pw) ImageView imgPlaneswalker;

    @BindView(R.id.txt_life_total) TextView txtLifeTotal;
    @BindView(R.id.btn_increase_life) ImageView btnIncreaseLife;
    @BindView(R.id.btn_decrease_life) ImageView btnDecreaseLife;

    @BindView(R.id.txt_poison_total) TextView txtPoisonTotal;
    @BindView(R.id.btn_increase_poison) ImageView btnIncreasePoison;
    @BindView(R.id.btn_decrease_poison) ImageView btnDecreasePoison;

    @BindView(R.id.txt_energy_total) TextView txtEnergyTotal;
    @BindView(R.id.btn_increase_energy) ImageView btnIncreaseEnergy;
    @BindView(R.id.btn_decrease_energy) ImageView btnDecreaseEnergy;

    private OnPlayerChangeListener onPlayerChangeListener;

    public VH(View parent) {
        super(parent);
        ButterKnife.bind(this, parent);

        frameType.setOnClickListener(v -> onPlayerChangeListener.onNameClick(getAdapterPosition()));

        txtLifeTotal.setOnClickListener(v -> onPlayerChangeListener.onTotalClick(getAdapterPosition()));
        txtPoisonTotal.setOnClickListener(v -> onPlayerChangeListener.onTotalClick(getAdapterPosition()));
        txtEnergyTotal.setOnClickListener(v -> onPlayerChangeListener.onTotalClick(getAdapterPosition()));

        btnIncreaseLife.setOnClickListener(v -> onPlayerChangeListener.onLifeIncreaseClick(getAdapterPosition()));
        btnDecreaseLife.setOnClickListener(v -> onPlayerChangeListener.onLifeDecreaseClick(getAdapterPosition()));

        btnIncreasePoison.setOnClickListener(v -> onPlayerChangeListener.onPoisonIncreaseClick(getAdapterPosition()));
        btnDecreasePoison.setOnClickListener(v -> onPlayerChangeListener.onPoisonDecreaseClick(getAdapterPosition()));

        btnIncreaseEnergy.setOnClickListener(v -> onPlayerChangeListener.onEnergyIncreaseClick(getAdapterPosition()));
        btnDecreaseEnergy.setOnClickListener(v -> onPlayerChangeListener.onEnergyDecreaseClick(getAdapterPosition()));
    }

    public void onBind(Player player) {
        String name = String.format("Planeswalker - %s", player.getType());
        Bitmap image = player.getBackground();
        String life = String.valueOf(player.getLifeCounters());
        String poison = String.valueOf(player.getPoisonCounters());
        String energy = String.valueOf(player.getEnergyCounters());

        txtType.setText(name);
        imgPlaneswalker.setImageBitmap(image);
        txtLifeTotal.setText(life);
        txtPoisonTotal.setText(poison);
        txtEnergyTotal.setText(energy);
    }

    public void setOnPlayerChangeListener(OnPlayerChangeListener onPlayerChangeListener) {
        this.onPlayerChangeListener = onPlayerChangeListener;
    }
}
