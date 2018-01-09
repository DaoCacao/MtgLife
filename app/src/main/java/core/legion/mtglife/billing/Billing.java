package core.legion.mtglife.billing;

import android.app.Activity;

import com.anjlab.android.iab.v3.BillingProcessor;

/**
 * Created by Legion on 02.01.2018.
 */

public class Billing implements IBilling {

    private String DONATE_1;    //R.string.donate_1
    private String DONATE_2;    //R.string.donate_2
    private String DONATE_3;    //R.string.donate_3
    private String DONATE_4;    //R.string.donate_4

    private BillingProcessor processor;

    public Billing(BillingProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void donate(Activity activity, Donations donation) {
        switch (donation) {
            case DONATE_1:
                processor.purchase(activity, DONATE_1);
                break;
            case DONATE_5:
                processor.purchase(activity, DONATE_2);
                break;
            case DONATE_10:
                processor.purchase(activity, DONATE_3);
                break;
            case DONATE_50:
                processor.purchase(activity, DONATE_4);
                break;
        }
    }
}
