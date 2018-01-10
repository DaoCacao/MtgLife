package core.legion.mtglife.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import core.legion.mtglife.R;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module(includes = PreferenceModule.Bindings.class)
public class PreferenceModule {

    @Module
    public interface Bindings {
        @Binds
        Preferences preferences(AppPreferences preferences);
    }

    @Provides
    SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), MODE_PRIVATE);
    }
}
