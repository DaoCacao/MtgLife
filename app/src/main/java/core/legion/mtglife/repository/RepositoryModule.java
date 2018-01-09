package core.legion.mtglife.repository;

import core.legion.mtglife.billing.BillingModule;
import core.legion.mtglife.preferences.PreferenceModule;
import core.legion.mtglife.preferences.Preferences;
import core.legion.mtglife.resource_provider.ResourceProviderModule;
import dagger.Module;
import dagger.Provides;

@Module(includes = {BillingModule.class, PreferenceModule.class, ResourceProviderModule.class})
public class RepositoryModule {

    @Provides
    Repository repository(Preferences preferences) {
        return new AppRepository(preferences);
    }
}
