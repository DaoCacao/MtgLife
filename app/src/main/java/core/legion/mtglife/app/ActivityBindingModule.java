package core.legion.mtglife.app;

import core.legion.mtglife.main_screen.MainActivity;
import core.legion.mtglife.main_screen.MainModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
