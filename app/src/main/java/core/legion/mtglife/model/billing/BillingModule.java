package core.legion.mtglife.model.billing;

import android.content.Context;

import com.anjlab.android.iab.v3.BillingProcessor;

import dagger.Module;
import dagger.Provides;

@Module
public class BillingModule {

    private String LICENSE_KEY; //R.string.license_key

    @Provides
    BillingProcessor.IBillingHandler billingHandler() {
        return new BillingHandler();
    }

    @Provides
    BillingProcessor billingProcessor(Context context, BillingProcessor.IBillingHandler handler) {
        return new BillingProcessor(context, LICENSE_KEY, handler);
    }

    @Provides
    Billing billing(BillingProcessor processor) {
        return new AppBilling(processor);
    }
}
