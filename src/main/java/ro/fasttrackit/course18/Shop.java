package ro.fasttrackit.course18;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.*;

public class Shop<T extends ShopItem> {
    private final List<T> items;

    public Shop(List<T> items) {
        this.items = ofNullable(items)
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }

    public Optional<T> findByName(String name) {
        return items.stream()
                .filter(item -> name.equalsIgnoreCase(item.name()))
                .findFirst();
    }

    public Optional<T> findById(int id) {
        return items.stream()
                .filter(item -> item.id() == id)
                .findFirst();
    }

    public List<T> filterByCategory(Category category) {
        return items.stream()
                .filter(item -> item.category() == category)
                .toList();
    }

    public Map<String, Double> nameToPrice() {
        return items.stream()
                .collect(toMap(ShopItem::name, ShopItem::price));
    }

    public Map<Classification, List<T>> groupByClassification() {
        return items.stream()
                .collect(groupingBy(ShopItem::classification));
    }

    public Map<Classification, List<T>> groupBySpecificClassification(Classification classification) {
        return items.stream()
                .filter(item -> item.classification() == classification)
                .collect(groupingBy(ShopItem::classification));
    }

    public Map<Classification, Double> totalPricePerClassification() {
        return items.stream()
                .collect(groupingBy(
                        ShopItem::classification,
                        summingDouble(ShopItem::price)
                ));
    }

    private boolean isOfCategory(T item, Category category) {
        return item.category() == category;
    }

    public List<T> getItems() {
        return unmodifiableList(items);
    }
}
