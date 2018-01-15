package core.legion.mtglife.main_screen.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.legion.mtglife.R;
import core.legion.mtglife.base.BaseVH;
import core.legion.mtglife.pojo.Player;

public class CardFullVH extends BaseVH {

    @BindView(R.id.txt_type) TextView txtType;

    @BindView(R.id.frame_name) FrameLayout frameName;
    @BindView(R.id.frame_image) FrameLayout frameImage;
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

    public CardFullVH(ViewGroup parent, OnPlayerChangeListener onPlayerChangeListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_full_item, parent, false));
        ButterKnife.bind(this, itemView);

        frameName.setOnClickListener(v -> onPlayerChangeListener.onNameClick(getAdapterPosition()));
        frameImage.setOnClickListener(v -> onPlayerChangeListener.onNameClick(getAdapterPosition()));
        frameType.setOnClickListener(v -> onPlayerChangeListener.onNameClick(getAdapterPosition()));

        btnIncreaseLife.setOnClickListener(v -> onPlayerChangeListener.onLifeIncreaseClick(getAdapterPosition()));
        btnDecreaseLife.setOnClickListener(v -> onPlayerChangeListener.onLifeDecreaseClick(getAdapterPosition()));

        btnIncreasePoison.setOnClickListener(v -> onPlayerChangeListener.onPoisonIncreaseClick(getAdapterPosition()));
        btnDecreasePoison.setOnClickListener(v -> onPlayerChangeListener.onPoisonDecreaseClick(getAdapterPosition()));

        btnIncreaseEnergy.setOnClickListener(v -> onPlayerChangeListener.onEnergyIncreaseClick(getAdapterPosition()));
        btnDecreaseEnergy.setOnClickListener(v -> onPlayerChangeListener.onEnergyDecreaseClick(getAdapterPosition()));
    }

    public void bind(Player player) {
        String type = String.format("Planeswalker - %s", player.getType());
        Drawable image = player.getBackground();
        String life = String.valueOf(player.getLifeCounters());
        String poison = String.valueOf(player.getPoisonCounters());
        String energy = String.valueOf(player.getEnergyCounters());

        txtType.setText(type);
        imgPlaneswalker.setImageDrawable(image);
        txtLifeTotal.setText(life);
        txtPoisonTotal.setText(poison);
        txtEnergyTotal.setText(energy);
    }
}
