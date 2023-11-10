package ro.fasttrackit.course18;

import java.util.List;

public interface ShopItem {
    int id();

    String name();

    double price();

    Category category();

    int quantity();

    Classification classification();

    List<String> features();
}
