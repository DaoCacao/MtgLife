package core.legion.mtglife.player_screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.legion.mtglife.R;
import core.legion.mtglife.pojo.Player;

public class PlaneswalkerVH extends RecyclerView.ViewHolder {

    @BindView(R.id.img_pw) ImageView image;
    @BindView(R.id.tv_pw) TextView name;

    public PlaneswalkerVH(ViewGroup parent, OnPlayerClickListener onPlayerClickListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.planeswalker_item, parent, false));
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> onPlayerClickListener.onClick(getAdapterPosition()));
    }

    public void bind(Player player) {
        image.setImageDrawable(player.getBackground());
        name.setText(player.getName());
    }
}
