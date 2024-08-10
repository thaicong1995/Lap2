package Lap2.example.Lap2.ViewModel;

import Lap2.example.Lap2.Common.PagingModel;
import Lap2.example.Lap2.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewModel extends Product {
    private int category_id;
    private int producer_id;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductRequest extends PagingModel {
        private Integer category_id;
        private Integer producer_id;
        private String key;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductResponse {
        private List<Product> products = new ArrayList<>();
        private long total;
        private int totalPages;
    }
}


