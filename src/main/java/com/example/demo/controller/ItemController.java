package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{date}")
    public ResponseEntity<?> getAllByDate (@PathVariable("date") String date){
        try {
            String[] abs = date.split("-", 5);
            for (String a : abs) {
                System.out.println(a);
            }
            int year = Integer.parseInt(abs[2]);
            int month = Integer.parseInt(abs[1]);
            int day = Integer.parseInt(abs[0]);
            Date date1 = new Date(year-1900, month-1, day);
            System.out.println(date1);
            List<ItemDto> items = itemService.getAllItems(date1);
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) {
        try {
            this.itemService.createItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
