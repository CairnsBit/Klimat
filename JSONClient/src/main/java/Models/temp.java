package Models;

import java.io.Serializable;

public class temp implements Serializable{

    private int id;
    private float hum;
    private float temp;
    private int lum;
    private int usage;

    public temp(){}

    public temp(int id, float temp, float hum, int lum, int usage) {
        this.id = id;
        this.temp = temp;
        this.hum = hum;
        this.lum = lum;
        this.usage = usage;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public float getHum() {
        return hum;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getLum() {
        return lum;
    }

    public void setLum(int lum) {
        this.lum = lum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}