package ro.fasttrackit.course18;

import lombok.Getter;
import ro.fasttrackit.course18.exception.DataParsingException;

import java.util.Arrays;

@Getter
public enum Category {
    NEW("new"),
    ON_SALE("sale"),
    REFURBISHED("refurbished");

    private final String code;

    Category(String code) {
        this.code = code;
    }

    public static Category of(String code) {
        return Arrays.stream(Category.values())
                .filter(classification -> classification.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new DataParsingException("Could not set category for value: %s".formatted(code)));
    }
}
