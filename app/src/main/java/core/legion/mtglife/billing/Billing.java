package core.legion.mtglife.billing;

import android.app.Activity;

import com.anjlab.android.iab.v3.BillingProcessor;

import core.legion.mtglife.R;

/**
 * Created by Legion on 02.01.2018.
 */

public class Billing implements IBilling {

    private BillingProcessor processor;

    public Billing(BillingProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void donate(Activity activity, Donations donation) {
        processor.purchase(activity, activity.getString(donation.getId()));
    }
}
