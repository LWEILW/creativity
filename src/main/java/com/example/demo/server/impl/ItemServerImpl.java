package com.example.demo.server.impl;

import com.example.demo.dao.ItemIntroduceMapper;
import com.example.demo.entity.ItemIntroduce;
import com.example.demo.server.ItemServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ItemServer")
public class ItemServerImpl implements ItemServer {

    @Autowired
    private ItemIntroduceMapper itemIntroduceMapper;

    @Override
    public ItemIntroduce getItemById(int id) {
        return itemIntroduceMapper.selectByPrimaryKey(id);
    }
}
