package core.legion.mtglife.models;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import core.legion.mtglife.main_screen.OnPlayerChangeListener;
import core.legion.mtglife.pojo.Player;

public class PlayerAdapter extends RecyclerView.Adapter<VH> {

    private List<Player> players;
    private OnPlayerChangeListener onPlayerChangeListener;

    public PlayerAdapter(OnPlayerChangeListener onPlayerChangeListener) {
        this.onPlayerChangeListener = onPlayerChangeListener;
        players = new ArrayList<>();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH holder = new VH(parent);
        holder.setOnPlayerChangeListener(onPlayerChangeListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.onBind(players.get(position));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
