package com.task.readexcel;

import lombok.*;


public class CSV_ModelClass {
    String type;
    String sku;
    String name;
    String isPulished;
    String isFeatured;
    String visibility;

    public CSV_ModelClass() {
    }

    public CSV_ModelClass(String type, String sku, String name, String isPulished, String isFeatured, String visibility) {
        this.type = type;
        this.sku = sku;
        this.name = name;
        this.isPulished = isPulished;
        this.isFeatured = isFeatured;
        this.visibility = visibility;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsPulished() {
        return isPulished;
    }

    public void setIsPulished(String isPulished) {
        this.isPulished = isPulished;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "CSV_ModelClass{" +
                "type='" + type + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", isPulished='" + isPulished + '\'' +
                ", isFeatured='" + isFeatured + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
