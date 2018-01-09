package core.legion.mtglife.model.planeswalker_provider;

import dagger.Binds;
import dagger.Module;

@Module
public interface ResourceProviderModule {

    @Binds
    Provider provider(ResourceProvider resourceProvider);
}
