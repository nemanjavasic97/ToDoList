package com.example.demo.service.impl;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
            System.out.println("asdasdasdasdas");
            ItemDto id1 = this.mapItemToItemDto(i);
            itemDtos.add(id1);
        }
        return itemDtos;
    }

    private ItemDto mapItemToItemDto(Item i) {
        ItemDto idto = new ItemDto();
        idto.setId(i.getId());
        idto.setCompleted(i.isCompleted());
        idto.setDate(i.getDate());
        idto.setName(i.getName());
        return idto;
    }

    @Override
    public void createItem(ItemDto itemDto) throws ParseException {
        System.out.println(itemDto.toString());

        Item item1 = new Item();
        item1.setName(itemDto.getName());
        item1.setCompleted(itemDto.isCompleted());
        item1.setId(itemDto.getId());
        item1.setDate(itemDto.getDate());
        this.itemRepository.save(item1);

    }
}
