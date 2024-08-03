package Lap2.example.Lap2.Service;

import Lap2.example.Lap2.Models.Category;
import Lap2.example.Lap2.ViewModel.CategoryViewModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICategoryService<T> {
    T findById(int id);
    List<T> findAll();
    T insert(CategoryViewModel category);
    T update(int id, CategoryViewModel category);
    T delete(int id);
}
