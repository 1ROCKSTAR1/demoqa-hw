package apiui.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookResp {
    private List<BookItem> books;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookItem {
        private String isbn;
    }
}

