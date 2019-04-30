package com.example.demo.server;

import com.example.demo.entity.ItemIntroduce;


public interface ItemServer {

    ItemIntroduce getItemById(int id);
}
