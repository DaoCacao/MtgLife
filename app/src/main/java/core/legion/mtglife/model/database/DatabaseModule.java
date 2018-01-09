package core.legion.mtglife.model.database;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface DatabaseModule {
    @Binds
    @Singleton
    Database database(MockDatabase mockDatabase);
}
