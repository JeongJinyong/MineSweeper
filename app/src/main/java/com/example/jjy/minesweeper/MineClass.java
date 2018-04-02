package com.example.jjy.minesweeper;

import android.view.View;

/**
 * Created by JJY on 2018-04-02.
 */

public class MineClass{
    String info = "";
    int visibility;

    public MineClass(String info){
        this.info = info;
        visibility = View.INVISIBLE;
    }

    public MineClass(String info,int visibility){
        this.info = info;
        this.visibility = visibility;
    }

}
