package core.legion.mtglife.main_screen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;

import core.legion.mtglife.R;
import core.legion.mtglife.app.scopes.ActivityScope;
import core.legion.mtglife.main_screen.adapter.OnPlayerChangeListener;
import core.legion.mtglife.main_screen.adapter.PlayerAdapter;
import core.legion.mtglife.model.database.Database;
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
        return new PlayerAdapter(presenter, database);
    }

    @Provides
    Intent intent(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(context.getString(R.string.link_play_market)));
        return intent;
    }
}
