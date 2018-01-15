package core.legion.mtglife.main_screen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;

import javax.inject.Named;

import core.legion.mtglife.R;
import core.legion.mtglife.main_screen.adapter.PlayerAdapter;
import core.legion.mtglife.model.database.Database;
import core.legion.mtglife.player_screen.PlayerActivity;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = MainModule.Bindings.class)
public class MainModule {

    @Module
    public interface Bindings {
        @Binds
        MainMvp.View view(MainActivity activity);

        @Binds
        MainMvp.Presenter presenter(MainPresenter presenter);
    }

    @Provides
    GridLayoutManager gridLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    @Provides
    PlayerAdapter adapter(MainMvp.Presenter presenter, Database database) {
        return new PlayerAdapter(presenter, database.getPlayers());
    }

    @Provides
    @Named(IntentsHelper.TO_PLAYMARKET_SCREEN)
    Intent playMarketIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(context.getString(R.string.link_play_market)));
        return intent;
    }
    @Provides
    @Named(IntentsHelper.TO_PLAYER_SCREEN)
    Intent playerIntent(Context context) {
        return new Intent(context, PlayerActivity.class);
    }
}
