package com.skfaisal.cameraform.model;

import java.util.ArrayList;
import java.util.List;

public class ItemParentModel {
    private String title;
    private List<ItemChildModel> itemChildModelArrayList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemChildModel> getItemChildModelArrayList() {
        return itemChildModelArrayList;
    }

    public void setItemChildModelArrayList(List<ItemChildModel> itemChildModelArrayList) {
        this.itemChildModelArrayList = itemChildModelArrayList;

    }
}
