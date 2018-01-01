package core.legion.mtglife.billing;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

public class BillingHandler implements BillingProcessor.IBillingHandler {

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
//        Toast.makeText(MainActivity.this, "Purchased!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
//        Toast.makeText(MainActivity.this, "Something wrong, try later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBillingInitialized() {

    }
}
