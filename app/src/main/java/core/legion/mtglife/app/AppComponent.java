package core.legion.mtglife.app;

import android.content.Context;

import javax.inject.Singleton;

import core.legion.mtglife.app.scopes.ActivityScope;
import core.legion.mtglife.model.billing.Billing;
import core.legion.mtglife.model.billing.BillingModule;
import core.legion.mtglife.model.database.Database;
import core.legion.mtglife.model.database.DatabaseModule;
import core.legion.mtglife.main_screen.MainActivity;
import core.legion.mtglife.main_screen.MainModule;
import core.legion.mtglife.model.planeswalker_provider.ResourceProvider;
import core.legion.mtglife.model.planeswalker_provider.ResourceProviderModule;
import core.legion.mtglife.preferences.PreferenceModule;
import core.legion.mtglife.preferences.Preferences;
import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppComponent.ActivityBindingModule.class,
        AppModule.class,
        PreferenceModule.class,
        ResourceProviderModule.class,
        DatabaseModule.class,
        BillingModule.class,

})
interface AppComponent extends AndroidInjector<AppLoader> {
    Context context();
    Preferences preferences();
    ResourceProvider provider();
    Database database();
    Billing billing();

    @Module
    interface ActivityBindingModule {
        @ActivityScope
        @ContributesAndroidInjector(modules = MainModule.class)
        MainActivity mainActivity();
    }
}