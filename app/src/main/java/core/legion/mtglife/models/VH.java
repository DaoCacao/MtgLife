package core.legion.mtglife.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.legion.mtglife.R;
import core.legion.mtglife.main_screen.OnPlayerChangeListener;
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

    public VH(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.pw_player_item, parent, false));
        ButterKnife.bind(this, parent);

        int position = getAdapterPosition();

        frameType.setOnClickListener(v -> onPlayerChangeListener.onNameClick(position));

        txtLifeTotal.setOnClickListener(v -> onPlayerChangeListener.onTotalClick(position));
        txtPoisonTotal.setOnClickListener(v -> onPlayerChangeListener.onTotalClick(position));
        txtEnergyTotal.setOnClickListener(v -> onPlayerChangeListener.onTotalClick(position));

        btnIncreaseLife.setOnClickListener(v -> onPlayerChangeListener.onLifeIncreaseClick(position));
        btnDecreaseLife.setOnClickListener(v -> onPlayerChangeListener.onLifeDecreaseClick(position));

        btnIncreasePoison.setOnClickListener(v -> onPlayerChangeListener.onPoisonIncreaseClick(position));
        btnDecreasePoison.setOnClickListener(v -> onPlayerChangeListener.onPoisonDecreaseClick(position));

        btnIncreaseEnergy.setOnClickListener(v -> onPlayerChangeListener.onEnergyIncreaseClick(position));
        btnDecreaseEnergy.setOnClickListener(v -> onPlayerChangeListener.onEnergyDecreaseClick(position));
    }

    public void onBind(Player player) {
        txtType.setText(String.format("Planeswalker - %s", player.getType()));

        imgPlaneswalker.setImageBitmap(player.getBackground());

        txtLifeTotal.setText(String.valueOf(player.getLifeCounters()));
        txtPoisonTotal.setText(String.valueOf(player.getPoisonCounters()));
        txtEnergyTotal.setText(String.valueOf(player.getEnergyCounters()));
    }

    public void setOnPlayerChangeListener(OnPlayerChangeListener onPlayerChangeListener) {
        this.onPlayerChangeListener = onPlayerChangeListener;
    }
}
