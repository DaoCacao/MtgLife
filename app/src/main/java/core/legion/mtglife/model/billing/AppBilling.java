package core.legion.mtglife.model.billing;

import android.app.Activity;

import com.anjlab.android.iab.v3.BillingProcessor;

import javax.inject.Inject;

/**
 * Created by Legion on 02.01.2018.
 */

public class AppBilling implements Billing {

    private BillingProcessor processor;

    @Inject
    public AppBilling(BillingProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void donate(Activity activity, Donations donation) {
        processor.purchase(activity, activity.getString(donation.getId()));
    }
}
