package core.legion.mtglife;

class Player {

    private int lifeTotal;
    private int poisonCounters;
    private int backgroundRes;

    Player() {
        lifeTotal = 20;
        poisonCounters = 0;
        backgroundRes = 0;
    }

    int getLifeTotal() {
        return lifeTotal;
    }
    int getPoisonCounters() {
        return poisonCounters;
    }
    int getBackgroundRes() {
        return backgroundRes;
    }

    void setLifeTotal(int lifeTotal) {
        this.lifeTotal = lifeTotal;
    }
    void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }
    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }

    void increaseLifeBy(int count) {
        lifeTotal += count;
    }
    void decreaseLifeBy(int count) {
        lifeTotal -= count;
    }

    void increasePoisonCountBy(int count) {
        poisonCounters += count;
    }
    void decreasePoisonCountBy(int count) {
        poisonCounters -= count;
    }
}
