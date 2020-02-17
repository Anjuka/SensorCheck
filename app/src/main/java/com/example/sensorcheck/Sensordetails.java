package com.example.sensorcheck;

public class Sensordetails {

    String deviceName;
    String time;
    String id;
    float light;
    float temp;
    float presure;
    float humidity;
    float step;
    float stepCount;
    float proxyM;
    float accelator;

    public Sensordetails(String id, String deviceName, String time, float light, float temp, float presure, float humidity, float step, float stepCount, float proxyM,
                         float accelator) {
        this.deviceName = deviceName;
        this.time = time;
        this.light = light;
        this.temp = temp;
        this.presure = presure;
        this.humidity = humidity;
        this.id = id;
        this.step = step;
        this.stepCount =stepCount;
        this.proxyM = proxyM;
        this.accelator = accelator;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public float getStepCount() {
        return stepCount;
    }

    public void setStepCount(float stepCount) {
        this.stepCount = stepCount;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getLight() {
        return light;
    }

    public void setLight(float light) {
        this.light = light;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getPresure() {
        return presure;
    }

    public void setPresure(float presure) {
        this.presure = presure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getProxyM() {
        return proxyM;
    }

    public void setProxyM(float proxyM) {
        this.proxyM = proxyM;
    }

    public float getAccelator() {
        return accelator;
    }

    public void setAccelator(float accelator) {
        this.accelator = accelator;
    }
}
