package core.legion.mtglife.billing;

import core.legion.mtglife.R;

public enum Donations {
    DONATE_1(R.string.donate_1),
    DONATE_2(R.string.donate_2),
    DONATE_3(R.string.donate_3),
    DONATE_4(R.string.donate_4);

    private int id;

    Donations(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}