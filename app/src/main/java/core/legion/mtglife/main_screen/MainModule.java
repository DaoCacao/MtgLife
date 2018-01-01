package core.legion.mtglife.main_screen;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import core.legion.mtglife.models.PlayerAdapter;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = MainModule.Bindings.class)
public class MainModule {

    @Module
    public interface Bindings {
        @Binds
        MainMvp.Presenter presenter(MainPresenter presenter);
    }

    @Provides
    MainMvp.View view(MainActivity activity) {
        return activity;
    }

    @Provides
    GridLayoutManager gridLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    @Provides
    PlayerAdapter adapter(MainMvp.Presenter presenter) {
        return new PlayerAdapter(presenter);
    }
}
