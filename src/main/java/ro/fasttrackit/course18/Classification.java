package ro.fasttrackit.course18;

import lombok.Getter;
import ro.fasttrackit.course18.exception.DataParsingException;

import java.util.Arrays;

@Getter
public enum Classification {
    ELECTRONICS("electronics"),
    BOOKS("books"),
    SPORTING_GOODS("sport");

    private final String code;

    Classification(String code) {
        this.code = code;
    }

    public static Classification of(String code) {
        return Arrays.stream(Classification.values())
                .filter(classification -> classification.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new DataParsingException("Could not set classification for value: %s".formatted(code)));
    }
}
