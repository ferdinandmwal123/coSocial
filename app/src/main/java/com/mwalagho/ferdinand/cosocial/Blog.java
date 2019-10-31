package com.mwalagho.ferdinand.cosocial;

public class Blog {


    private String title;
    private String desc;
    private String image;

    public Blog(){} //empty constructor

    public Blog(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

}
