package core.legion.mtglife.player_screen;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import core.legion.mtglife.model.database.Database;
import core.legion.mtglife.player_screen.adapter.PlaneswalkerAdapter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = PlayerModule.Bindings.class)
public class PlayerModule {

    @Module
    public interface Bindings {
        @Binds
        PlayerMvp.View view(PlayerActivity activity);

        @Binds
        PlayerMvp.Presenter presenter(PlayerPresenter presenter);
    }

    @Provides
    LinearLayoutManager planeswalkersLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    PlaneswalkerAdapter planeswalkerAdapter(PlayerMvp.Presenter presenter, Database database) {
        return new PlaneswalkerAdapter(presenter, database.getPlaneswalkers());
    }
}
