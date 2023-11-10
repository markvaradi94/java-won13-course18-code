package ro.fasttrackit.course18.reader;

import lombok.experimental.UtilityClass;
import ro.fasttrackit.course18.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static ro.fasttrackit.course18.Classification.*;

@UtilityClass
public class ShopItemReader {

    private static final Map<Classification, Function<String[], ShopItem>> itemBuilders = Map.of(
            BOOKS, ShopItemReader::buildBook,
            ELECTRONICS, ShopItemReader::buildElectronics,
            SPORTING_GOODS, ShopItemReader::buildSportingGoods
    );

    public static List<ShopItem> readItemsFromFile(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            return reader.lines()
                    .map(ShopItemReader::mapToShopItem)
                    .toList();
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private static ShopItem mapToShopItem(String line) {
        String[] tokens = line.split("[|]");
        Classification classification = Classification.of(tokens[4]);
        return itemBuilders.get(classification).apply(tokens);
    }

    private static Book buildBook(String[] tokens) {
        return Book.builder()
                .id(new Random().nextInt(1000))
                .name(tokens[0])
                .price(parseDouble(tokens[1]))
                .category(Category.of(tokens[2]))
                .quantity(parseInt(tokens[3]))
                .classification(BOOKS)
                .features(extractFeatures(tokens[5]))
                .build();
    }

    private static Electronics buildElectronics(String[] tokens) {
        return Electronics.builder()
                .id(new Random().nextInt(1000))
                .name(tokens[0])
                .price(parseDouble(tokens[1]))
                .category(Category.of(tokens[2]))
                .quantity(parseInt(tokens[3]))
                .classification(ELECTRONICS)
                .features(extractFeatures(tokens[5]))
                .build();
    }

    private static SportingGoods buildSportingGoods(String[] tokens) {
        return SportingGoods.builder()
                .id(new Random().nextInt(1000))
                .name(tokens[0])
                .price(parseDouble(tokens[1]))
                .category(Category.of(tokens[2]))
                .quantity(parseInt(tokens[3]))
                .classification(SPORTING_GOODS)
                .features(extractFeatures(tokens[5]))
                .build();
    }

    private static List<String> extractFeatures(String token) {
        String[] features = token.split(",");
        return Arrays.stream(features)
                .map(feat -> feat.replace("_", " "))
                .toList();
    }
}
