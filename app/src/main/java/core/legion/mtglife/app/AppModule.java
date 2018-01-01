package core.legion.mtglife.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {
    private Context context;

    AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context context() {
        return context;
    }
}
