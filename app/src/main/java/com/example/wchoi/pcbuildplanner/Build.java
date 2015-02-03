package com.example.wchoi.pcbuildplanner;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 2015wchoi on 1/5/2015.
 */
public class Build implements Serializable{

    private String id;
    private String title;
    private ArrayList<String> cpu;
    private ArrayList<String> mobo;
    private ArrayList<String> ram;
    private ArrayList<String> gpu;
    private ArrayList<String> psu;
    private ArrayList<String> storage;
    private ArrayList<String> tower;
    private ArrayList<String> odd;
    private ArrayList<String> cpu_cooler;
    private ArrayList<String> others;

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

    public ArrayList<String> getCpu() {
        return cpu;
    }

    public void setCpu(ArrayList<String> cpu) {
        this.cpu = cpu;
    }

    public ArrayList<String> getMobo() {
        return mobo;
    }
    public void setMobo(ArrayList<String> mobo) {
        this.mobo = mobo;
    }

    public ArrayList<String> getRam() {
        return ram;
    }
    public void setRam(ArrayList<String> ram) {
        this.ram = ram;
    }

    public ArrayList<String> getGpu() {
        return gpu;
    }
    public void setGpu(ArrayList<String> gpu) {
        this.gpu = gpu;
    }

    public ArrayList<String> getPsu() {
        return psu;
    }
    public void setPsu(ArrayList<String> psu) {
        this.psu = psu;
    }

    public ArrayList<String> getStorage() {
        return storage;
    }
    public void setStorage(ArrayList<String> storage) {
        this.storage = storage;
    }

    public ArrayList<String> getTower() {
        return tower;
    }
    public void setTower(ArrayList<String> tower) {
        this.tower = tower;
    }

    public ArrayList<String> getOdd() {
        return odd;
    }
    public void setOdd(ArrayList<String> odd) {
        this.odd = odd;
    }

    public ArrayList<String> getCpu_cooler() {
        return cpu_cooler;
    }
    public void setCpu_cooler(ArrayList<String> cpu_cooler) {
        this.cpu_cooler = cpu_cooler;
    }

    public ArrayList<String> getOthers() {
        return others;
    }
    public void setOthers(ArrayList<String> others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
