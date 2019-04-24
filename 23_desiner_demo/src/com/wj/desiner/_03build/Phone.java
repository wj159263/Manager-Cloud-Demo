package com.wj.desiner._03build;

public class Phone {
    private Brand brand; //手机牌子
    private Color color;//手机颜色
    private Weight weight;//手机重量

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "牌子='" + brand.getName() + '\'' +
                ", 颜色='" + color.getName() + '\'' +
                ", 重量='" + weight.getName() + '\'' +
                '}';
    }
}
class Brand{
    private String name;

    public Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
class Color{
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

class Weight{
    private String name;

    public Weight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}