package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value="/{from}")
    public @ResponseBody List<ItemDto> getItemsByDate(@PathVariable("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate) {
        System.out.println(fromDate);
        List<ItemDto> list = this.itemService.getAllItems(fromDate);
        return list;
    }


    @PostMapping(value = "/{from}")
    public ResponseEntity<?> createItem(@RequestBody ItemDto itemDto, @PathVariable("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {
        try {
            this.itemService.createItem(itemDto, fromDate);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> changeCompleted(@RequestBody ItemDto itemDto) {
        try {
            this.itemService.editItem(itemDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
