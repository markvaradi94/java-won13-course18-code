package ro.fasttrackit.course18;

import ro.fasttrackit.course18.reader.ShopItemReader;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<ShopItem> shopItems = ShopItemReader.readItemsFromFile("src/main/resources/shop-items.txt");
        Shop<ShopItem> shop = new Shop<>(shopItems);

        shop.totalPricePerClassification().entrySet()
                .forEach(System.out::println);

        Optional<ShopItem> leviOptional = shop.findByName("leviOptional");

        if (leviOptional.isPresent()) {
            ShopItem levi = leviOptional.get();
            System.out.println(levi);
        } else {
            System.out.println("nothing was found");
        }

        shop.findByName("Papuci de lana").ifPresent(System.out::println);
        shop.findByName("Papuci de lana")
                .ifPresentOrElse(System.out::println, () -> System.out.println("No item was received"));


        shop.findByName("Cookbook")
                .map(ShopItem::features)
                .ifPresentOrElse(System.out::println, () -> System.out.println("No item was received"));


        ShopItem item = shop.findById(50)
                .orElseThrow(() -> new RuntimeException("Could not find item with id %s".formatted(50)));

        System.out.println(item);

//        ShopItem item = null;
//
//        if (item != null) {
//            System.out.println(item);
//        } else {
//            System.out.println("No item was received");
//        }
    }
}