package com.xiong.stockdiagnosticdiary.bean;

import com.xuexiang.xui.widget.picker.wheelview.interfaces.IPickerViewItem;

import java.util.List;

public class ProvinceInfo implements IPickerViewItem {
    private String name;
    private List<String> city;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCityList() {
        return city;
    }

    public void setCityList(List<String> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
