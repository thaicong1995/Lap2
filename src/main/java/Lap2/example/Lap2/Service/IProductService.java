package Lap2.example.Lap2.Service;

import Lap2.example.Lap2.ViewModel.ProductViewModel;

import java.util.List;

public interface IProductService<T> {
    T findById(int id);

    ProductViewModel.ProductResponse paging(ProductViewModel.ProductRequest request);

    T insert(int category_id, int producer_id,ProductViewModel product);

    T update(int product_id, ProductViewModel product);

    T delete(int id);
}
