package dec.sitconsulting.metrics.controller;

import dec.sitconsulting.metrics.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/first")
    public List<String> getFirstItemlist() {
        return itemService.getListOfFirstItems();
    }

    @GetMapping("/second")
    public List<String> getSecondItemList() {
        return itemService.getListOfSecondItems();
    }
}
