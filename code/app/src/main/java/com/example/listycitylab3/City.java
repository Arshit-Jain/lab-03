package com.example.listycitylab3;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private String province;
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }
    public String getName() {
        return name;
    }
    public String getProvince() {
        return province;
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public void setProvince(String provinceName) {
        this.province = provinceName;
    }
}
