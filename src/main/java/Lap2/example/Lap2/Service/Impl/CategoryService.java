package Lap2.example.Lap2.Service.Impl;

import Lap2.example.Lap2.MapDto;
import Lap2.example.Lap2.Models.Category;
import Lap2.example.Lap2.Repository.ICategoryRepo;
import Lap2.example.Lap2.Service.ICategoryService;
import Lap2.example.Lap2.ViewModel.CategoryViewModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static Lap2.example.Lap2.MapDto.*;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService implements ICategoryService<CategoryViewModel> {
    @Autowired
    private ICategoryRepo repo;


    @Override
    public CategoryViewModel findById(int id) {
        var categories = repo.findById(id);
        return toModel(categories.get(), CategoryViewModel.class);
    }

    @Override
    public List<CategoryViewModel> findAll() {
        var categories = repo.findAll();
        return toModels(categories, CategoryViewModel.class);
    }

    @Override
    public CategoryViewModel insert(CategoryViewModel category) {
        var c = toModel(category, Category.class);
        var saved = repo.save(c);
        return toModel(saved, CategoryViewModel.class);
    }

    @Override
    public CategoryViewModel update(int id, CategoryViewModel category) {

        Optional<Category> exist= repo.findById(id);
        if (exist.isEmpty()) {
            throw new RuntimeException("Category not found for ID: " + id);
        }
        var c = exist.get();
        c.setName(category.getName());
        Category updatedCategory = repo.save(c);
        return toModel(updatedCategory, CategoryViewModel.class);
    }

    @Override
    public CategoryViewModel delete(int id) {
        Optional<Category> exist = repo.findById(id);
        if (exist.isEmpty()) {
            throw new RuntimeException("Category not found for ID: " + id);
        }
        repo.deleteById(id);
        return null;
    }
}
