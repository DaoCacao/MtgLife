package core.legion.mtglife;

public class Player {

    private int lifeTotal;
    private int poisonCounters;

    public Player() {
        lifeTotal = 20;
        poisonCounters = 0;
    }

    public int getLifeTotal() {
        return lifeTotal;
    }
    public int getPoisonCounters() {
        return poisonCounters;
    }

    public void setLifeTotal(int lifeTotal) {
        this.lifeTotal = lifeTotal;
    }
    public void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }

    public void increaseLifeBy(int count) {
        lifeTotal += count;
    }
    public void decreaseLifeBy(int count) {
        lifeTotal -= count;
    }

    public void increasePoisonCountBy(int count) {
        poisonCounters += count;
    }
    public void decreasePoisonCountBy(int count) {
        poisonCounters -= count;
    }
}
