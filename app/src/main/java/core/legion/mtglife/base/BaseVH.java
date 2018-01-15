package core.legion.mtglife.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import core.legion.mtglife.pojo.Player;

public abstract class BaseVH extends RecyclerView.ViewHolder{

    public BaseVH(View itemView) {
        super(itemView);
    }

    public abstract void bind(Player player);
}
