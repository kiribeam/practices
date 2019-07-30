package com.example.model;
import java.util.*;

public class BeerExpert{
  public List getBrands(String color){
    List brands = new ArrayList();
    if(color.equals("amber")){
      brands.add("jack amber");
      brands.add("Red moose");
    }else{
      brands.add("jail pale");
      brands.add("gout stout");
    }
    return brands;
  }
}
