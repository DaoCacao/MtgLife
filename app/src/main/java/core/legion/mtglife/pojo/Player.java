package core.legion.mtglife.pojo;


import android.graphics.drawable.Drawable;

public class Player {

    private String name;
    private String type;
    private Drawable background;

    private int lifeCounters;
    private int poisonCounters;
    private int energyCounters;

    public Player(String name, String type, Drawable background) {
        this.name = name;
        this.type = type;
        this.background = background;
        reset();
    }

    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public Drawable getBackground() {
        return background;
    }
    public int getLifeCounters() {
        return lifeCounters;
    }
    public int getPoisonCounters() {
        return poisonCounters;
    }
    public int getEnergyCounters() {
        return energyCounters;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setBackground(Drawable background) {
        this.background = background;
    }
    public void setLifeCounters(int lifeCounters) {
        this.lifeCounters = lifeCounters;
    }
    public void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }
    public void setEnergyCounters(int energyCounters) {
        this.energyCounters = energyCounters;
    }

    public void increaseLifeCount() {
        lifeCounters++;
    }
    public void decreaseLifeCount() {
        lifeCounters--;
    }

    public void increasePoisonCount() {
        poisonCounters++;
    }
    public void decreasePoisonCount() {
        poisonCounters--;
    }

    public void increaseEnergyCount() {
        energyCounters++;
    }
    public void decreaseEnergyCount() {
        energyCounters--;
    }

    public void reset() {
        lifeCounters = 20;
        poisonCounters = 0;
        energyCounters = 0;
    }
}
