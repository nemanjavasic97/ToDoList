package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.Item;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ItemService {
    List<ItemDto> getAllItems(Date date);
    Item createItem(ItemDto itemDto, Date fromDate) throws ParseException;
    void editItem(ItemDto itemDto) throws ParseException;
}
