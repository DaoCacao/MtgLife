package core.legion.mtglife.main_screen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import core.legion.mtglife.R;
import core.legion.mtglife.model.database.Database;
import core.legion.mtglife.pojo.Player;

public class PlayerAdapter extends RecyclerView.Adapter<VH> {

    private List<Player> players;
    private OnPlayerChangeListener onPlayerChangeListener;

    public PlayerAdapter(OnPlayerChangeListener onPlayerChangeListener, Database database) {
        this.onPlayerChangeListener = onPlayerChangeListener;
        players = database.getPlayers();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO--> replace to VH constructor
        VH holder = new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.planeswalker_item, parent, false));
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
