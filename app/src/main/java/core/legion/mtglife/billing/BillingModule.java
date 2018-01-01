package core.legion.mtglife.billing;

import android.content.Context;

import com.anjlab.android.iab.v3.BillingProcessor;

import dagger.Module;
import dagger.Provides;

@Module
public class BillingModule {

    private String DONATE_1;    //R.string.donate_1
    private String DONATE_2;    //R.string.donate_2
    private String DONATE_3;    //R.string.donate_3
    private String DONATE_4;    //R.string.donate_4
    private String LICENSE_KEY; //R.string.license_key

    BillingProcessor.IBillingHandler billingHandler() {
        return new BillingHandler();
    }

    @Provides
    BillingProcessor billingProcessor(Context context, BillingProcessor.IBillingHandler handler) {
        return new BillingProcessor(context, LICENSE_KEY, handler);
    }
}
