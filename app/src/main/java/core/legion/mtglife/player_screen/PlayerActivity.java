package core.legion.mtglife.player_screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import core.legion.mtglife.R;
import core.legion.mtglife.player_screen.adapter.PlaneswalkerAdapter;
import dagger.android.support.DaggerAppCompatActivity;

public class PlayerActivity extends DaggerAppCompatActivity implements PlayerMvp.View {

    @BindView(R.id.rv_planeswalkers) RecyclerView rwPlaneswalkers;

    @Inject LinearLayoutManager planeswalkersLayoutManager;
    @Inject PlaneswalkerAdapter planeswalkerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_screen_layout);
        ButterKnife.bind(this);

        rwPlaneswalkers.setLayoutManager(planeswalkersLayoutManager);
        rwPlaneswalkers.setAdapter(planeswalkerAdapter);
    }

    @Override
    public void closeActivity() {
        finish();
    }
}
