package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.Item;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ItemService {
    List<ItemDto> getAllItems(Date date);
    void createItem(ItemDto itemDto) throws ParseException;
}
