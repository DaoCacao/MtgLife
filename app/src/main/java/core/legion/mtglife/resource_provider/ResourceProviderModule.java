package core.legion.mtglife.resource_provider;

import dagger.Binds;
import dagger.Module;

@Module
public interface ResourceProviderModule {

    @Binds
    ResourceProvider resourceProvider(AppResourceProvider resourceProvider);
}
