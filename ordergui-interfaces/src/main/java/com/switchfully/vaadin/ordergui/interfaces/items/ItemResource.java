package com.switchfully.vaadin.ordergui.interfaces.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemResource {

    private RestTemplate restTemplate;
    private static final String BACKENDURL = "http://localhost:9000/items";


    @Autowired
    public ItemResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ItemResource() {
    }

    public List<Item> getItems() {
        Item[] items = restTemplate.getForObject(BACKENDURL, Item[].class);
        return Arrays.asList(items);
    }


    public Item create(Item item) {
        return restTemplate.postForObject(BACKENDURL,item,Item.class);
    }
}
