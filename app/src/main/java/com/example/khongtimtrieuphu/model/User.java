package com.example.khongtimtrieuphu.model;

public class User implements Comparable<User> {

    private String name;
    private String money;

    public User(String name, String money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public int compareTo(User user) {
        return user.getMoney().compareTo(this.money);
    }


}
