package core.legion.mtglife;

class Player {

    private int lifeCounters;
    private int poisonCounters;
    private int energyCounters;

    private int backgroundRes;

    Player() {
        lifeCounters = 20;
        poisonCounters = 0;
        backgroundRes = 0;
    }

    int getLifeCounters() {
        return lifeCounters;
    }
    void setLifeCounters(int lifeCounters) {
        this.lifeCounters = lifeCounters;
    }
    void increaseLifeBy(int count) {
        lifeCounters += count;
    }
    void decreaseLifeBy(int count) {
        lifeCounters -= count;
    }

    int getPoisonCounters() {
        return poisonCounters;
    }
    void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }
    void increasePoisonCountBy(int count) {
        poisonCounters += count;
    }
    void decreasePoisonCountBy(int count) {
        poisonCounters -= count;
    }

    int getEnergyCounters() {
        return energyCounters;
    }
    void setEnergyCounters(int energyCounters) {
        this.energyCounters = energyCounters;
    }
    void increaseEnergyCountBy(int count) {
        energyCounters += count;
    }
    void decreaseEnergyCountBy(int count) {
        energyCounters -= count;
    }

    int getBackgroundRes() {
        return backgroundRes;
    }
    void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }




}
