package core.legion.mtglife.model.billing;

import android.content.Context;

import com.anjlab.android.iab.v3.BillingProcessor;

import core.legion.mtglife.R;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = BillingModule.Bindings.class)
public class BillingModule {

    @Module
    public interface Bindings {
        @Binds
        Billing billing(AppBilling billing);

        @Binds
        BillingProcessor.IBillingHandler billingHandler(BillingHandler billingHandler);
    }

    @Provides
    BillingProcessor billingProcessor(Context context, BillingProcessor.IBillingHandler handler) {
        return new BillingProcessor(context, context.getString(R.string.license_key), handler);
    }
}
