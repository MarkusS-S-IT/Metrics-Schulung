package dev.sitconsulting.metrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ItemService {
    private final Random random;

    @Autowired
    public ItemService(){
        this.random = new Random();
    }

    public List<String> getListOfFirstItems() {
        try {
            // Takes a time to evaluate all "first items"
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return List.of("1.1", "1.2", "1.3");
    }

    public List<String> getListOfSecondItems() {
        try {
            // Takes a time to evaluate all "first items"
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return List.of("2.1", "2.2", "2.3");
    }
}
