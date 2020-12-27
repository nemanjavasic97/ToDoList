package com.example.demo.controller;

import com.example.demo.dto.ItemDto;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value="/{from}" , method=RequestMethod.GET)
    public @ResponseBody List<ItemDto> getItemsByDate(@PathVariable("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate) {
        System.out.println(fromDate);
        List<ItemDto> lista = this.itemService.getAllItems(fromDate);
        return lista;
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

    @PutMapping(value = "/{from}")
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
