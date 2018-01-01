package core.legion.mtglife.app;

import core.legion.mtglife.repository.RepositoryModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AppModule.class, RepositoryModule.class, ActivityBindingModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<AppLoader> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AppLoader> {
        abstract Builder add(AppModule appModule);
    }
}