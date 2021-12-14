package com.example.khongtimtrieuphu.model;

public class AmountOfMoney {
    private String money;
    private boolean isSelected;

    public AmountOfMoney(String money) {
        this.money = money;
        isSelected = false;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
