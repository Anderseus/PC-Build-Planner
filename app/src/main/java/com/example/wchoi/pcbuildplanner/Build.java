package com.example.wchoi.pcbuildplanner;

/**
 * Created by 2015wchoi on 1/5/2015.
 */
public class Build {

    private String id;
    private String title;
    private String[] cpu;
    private String[] mobo;
    private String[] gpu;
    private String[] psu;
    private String[] storage;
    private String[] tower;
    private String[] ram;
    private String[] odd;
    private String[] cpu_cooler;
    private String[] others;

    Build(String buildId, String buildTitle) {
        id = buildId;
        title = buildTitle;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getCpu() {
        return cpu;
    }

    public void setCpu(String[] cpu) {
        this.cpu = cpu;
    }

    public String[] getMobo() {
        return mobo;
    }
    public void setMobo(String[] mobo) {
        this.mobo = mobo;
    }

    public String[] getGpu() {
        return gpu;
    }
    public void setGpu(String[] gpu) {
        this.gpu = gpu;
    }

    public String[] getPsu() {
        return psu;
    }
    public void setPsu(String[] psu) {
        this.psu = psu;
    }

    public String[] getStorage() {
        return storage;
    }
    public void setStorage(String[] storage) {
        this.storage = storage;
    }

    public String[] getTower() {
        return tower;
    }
    public void setTower(String[] tower) {
        this.tower = tower;
    }

    public String[] getRam() {
        return ram;
    }
    public void setRam(String[] ram) {
        this.ram = ram;
    }

    public String[] getOdd() {
        return odd;
    }
    public void setOdd(String[] odd) {
        this.odd = odd;
    }

    public String[] getCpu_cooler() {
        return cpu_cooler;
    }
    public void setCpu_cooler(String[] cpu_cooler) {
        this.cpu_cooler = cpu_cooler;
    }

    public String[] getOthers() {
        return others;
    }
    public void setOthers(String[] others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
