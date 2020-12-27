package com.example.demo.service.impl;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemDto> getAllItems(Date date) {

        List<Item> items = this.itemRepository.findAllByDate(date);
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item i: items){
            System.out.println(i.isCompleted());
            ItemDto id1 = this.mapItemToItemDto(i);
            if(!i.isCompleted())
            itemDtos.add(id1);
        }
        return itemDtos;
    }

    @Override
    public Item createItem(ItemDto itemDto, Date fromDate) throws ParseException {
        Item item1 = new Item();
        item1.setName(itemDto.getName());
        item1.setCompleted(false);
        item1.setDate(fromDate);
        return this.itemRepository.save(item1);
    }

    @Override
    public void editItem(ItemDto itemDto) throws ParseException {
        Item itemToEdit = this.itemRepository.getOne(itemDto.getId());
        //itemToEdit.setName(itemDto.getName());
        itemToEdit.setCompleted(true);
        //itemToEdit.setDate(itemDto.getDate());
        this.itemRepository.save(itemToEdit);
    }

    private ItemDto mapItemToItemDto(Item i) {
        ItemDto idto = new ItemDto();
        idto.setId(i.getId());
        idto.setCompleted(i.isCompleted());
        idto.setDate(i.getDate());
        idto.setName(i.getName());
        return idto;
    }

}
