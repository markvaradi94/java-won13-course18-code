package ro.fasttrackit.course18;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Builder
public record SportingGoods(
        int id,
        String name,
        double price,
        Category category,
        int quantity,
        Classification classification,
        List<String> features
) implements ShopItem {
    public SportingGoods {
        features = ofNullable(features)
                .orElseGet(ArrayList::new);
    }
}
