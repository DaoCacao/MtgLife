package core.legion.mtglife.main_screen;

public interface OnPlayerChangeListener {
    void onNameClick(int pos);

    void onTotalClick(int pos);

    void onLifeIncreaseClick(int pos);
    void onLifeDecreaseClick(int pos);

    void onPoisonIncreaseClick(int pos);
    void onPoisonDecreaseClick(int pos);

    void onEnergyIncreaseClick(int pos);
    void onEnergyDecreaseClick(int pos);
}
