package apiui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookReq {
    private String userId;
    private List<IsbnItem> collectionOfIsbns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsbnItem {
        private String isbn;
    }

    // Удобный конструктор для одной книги
    public AddBookReq(String userId, String isbn) {
        this.userId = userId;
        this.collectionOfIsbns = List.of(new IsbnItem(isbn));
    }
}

