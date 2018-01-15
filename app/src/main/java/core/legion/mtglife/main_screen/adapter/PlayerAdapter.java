package core.legion.mtglife.main_screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import core.legion.mtglife.base.BaseVH;
import core.legion.mtglife.pojo.Player;

public class PlayerAdapter extends RecyclerView.Adapter<BaseVH> {

    private OnPlayerChangeListener onPlayerChangeListener;
    private List<Player> players;

    public PlayerAdapter(OnPlayerChangeListener onPlayerChangeListener, List<Player> players) {
        this.onPlayerChangeListener = onPlayerChangeListener;
        this.players = players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardVH(parent, onPlayerChangeListener);
    }

    @Override
    public void onBindViewHolder(BaseVH holder, int position) {
        holder.bind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
