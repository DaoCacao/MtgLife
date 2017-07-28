package core.legion.mtglife.models;

public class Player {

    private int lifeCounters;
    private int poisonCounters;
    private int energyCounters;

    public Player() {
        lifeCounters = 20;
        poisonCounters = 0;
    }

    public int getLifeCounters() {
        return lifeCounters;
    }
    public void setLifeCounters(int lifeCounters) {
        this.lifeCounters = lifeCounters;
    }
    public void increaseLifeBy(int count) {
        lifeCounters += count;
    }
    public void decreaseLifeBy(int count) {
        lifeCounters -= count;
    }

    public int getPoisonCounters() {
        return poisonCounters;
    }
    public void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }
    public void increasePoisonCountBy(int count) {
        poisonCounters += count;
    }
    public void decreasePoisonCountBy(int count) {
        poisonCounters -= count;
    }

    public int getEnergyCounters() {
        return energyCounters;
    }
    public void setEnergyCounters(int energyCounters) {
        this.energyCounters = energyCounters;
    }
    public void increaseEnergyCountBy(int count) {
        energyCounters += count;
    }
    public void decreaseEnergyCountBy(int count) {
        energyCounters -= count;
    }
}
