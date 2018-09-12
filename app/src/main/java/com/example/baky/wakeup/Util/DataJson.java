package com.example.baky.wakeup.Util;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataJson {
    @SerializedName("item")
    public List<item> items;
    public class item{
        @SerializedName("category")
        public String category;
        @SerializedName("obsrValue")
        public int obsrValue;
    }
}
