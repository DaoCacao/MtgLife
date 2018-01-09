package core.legion.mtglife.pojo;


import android.graphics.Bitmap;

public class Player {

    private String name;
    private String type;
    private Bitmap background;

    private int lifeCounters;
    private int poisonCounters;
    private int energyCounters;

    public Player() {
        reset();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Bitmap getBackground() {
        return background;
    }
    public void setBackground(Bitmap background) {
        this.background = background;
    }

    public int getLifeCounters() {
        return lifeCounters;
    }
    public void setLifeCounters(int lifeCounters) {
        this.lifeCounters = lifeCounters;
    }
    public void increaseLifeCount() {
        lifeCounters++;
    }
    public void decreaseLifeCount() {
        lifeCounters--;
    }

    public int getPoisonCounters() {
        return poisonCounters;
    }
    public void setPoisonCounters(int poisonCounters) {
        this.poisonCounters = poisonCounters;
    }
    public void increasePoisonCount() {
        poisonCounters++;
    }
    public void decreasePoisonCount() {
        poisonCounters--;
    }

    public int getEnergyCounters() {
        return energyCounters;
    }
    public void setEnergyCounters(int energyCounters) {
        this.energyCounters = energyCounters;
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
