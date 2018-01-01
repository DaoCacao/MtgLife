package core.legion.mtglife.models;

public class Player {

    private String name;

    private int lifeCounters;
    private int poisonCounters;
    private int energyCounters;

    public Player() {
        name = "Planeswalker";
        reset();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getLifeCounters() {
        return lifeCounters;
    }
    public void setLifeCounters(int lifeCounters) {
        this.lifeCounters = lifeCounters;
    }
    public void increaseLifeCount() {
        lifeCounters += 1;
    }
    public void decreaseLifeCount() {
        lifeCounters -= 1;
    }

    public int getPoisonCounters() {
        return poisonCounters;
    }
    public void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }
    public void increasePoisonCount() {
        poisonCounters += 1;
    }
    public void decreasePoisonCount() {
        poisonCounters -= 1;
    }

    public int getEnergyCounters() {
        return energyCounters;
    }
    public void setEnergyCounters(int energyCounters) {
        this.energyCounters = energyCounters;
    }
    public void increaseEnergyCount() {
        energyCounters += 1;
    }
    public void decreaseEnergyCount() {
        energyCounters -= 1;
    }

    public void reset() {
        lifeCounters = 20;
        poisonCounters = 0;
        energyCounters = 0;
    }
}
