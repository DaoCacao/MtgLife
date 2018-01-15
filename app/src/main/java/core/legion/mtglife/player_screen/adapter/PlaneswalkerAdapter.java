package core.legion.mtglife.player_screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import core.legion.mtglife.pojo.Player;

public class PlaneswalkerAdapter extends RecyclerView.Adapter<PlaneswalkerVH> {

    private OnPlayerClickListener onPlayerClickListener;
    private List<Player> players;

    public PlaneswalkerAdapter(OnPlayerClickListener onPlayerClickListener, List<Player> players) {
        this.onPlayerClickListener = onPlayerClickListener;
        this.players = players;
    }

    @Override
    public PlaneswalkerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaneswalkerVH(parent, onPlayerClickListener);
    }

    @Override
    public void onBindViewHolder(PlaneswalkerVH holder, int position) {
        holder.bind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
