package core.legion.mtglife.main_screen.adapter;

public interface OnPlayerChangeListener {
    void onNameClick();
    void onTotalClick();

    void onLifeIncreaseClick(int pos);
    void onLifeDecreaseClick(int pos);

    void onPoisonIncreaseClick(int pos);
    void onPoisonDecreaseClick(int pos);

    void onEnergyIncreaseClick(int pos);
    void onEnergyDecreaseClick(int pos);
}
