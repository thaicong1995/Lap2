package Lap2.example.Lap2.Controller;

import Lap2.example.Lap2.Service.ICategoryService;
import Lap2.example.Lap2.ViewModel.CategoryViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private ICategoryService service;

    @GetMapping("/category/{id}")
    public ResponseEntity<Object> findbyId(@PathVariable int id) {
        var categoryViewModel = service.findById(id);
        return ResponseEntity.ok(categoryViewModel);
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Object>> findAll() {
        var categoryViewModel = service.findAll();
        return ResponseEntity.ok(categoryViewModel);
    }

    @PostMapping("/category")
    public ResponseEntity<Object> DoInsert(@RequestBody CategoryViewModel category) {
        var categoryViewModel = service.insert(category);
        return ResponseEntity.ok(categoryViewModel);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Object> DoUpdate(@PathVariable int id, @RequestBody CategoryViewModel category) {
        var categoryViewModel = service.update(id,category);
        return ResponseEntity.ok(categoryViewModel);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Object> DoDelete(@PathVariable int id) {
        var categoryViewModel = service.delete(id);
        return ResponseEntity.ok(categoryViewModel);
    }

}
