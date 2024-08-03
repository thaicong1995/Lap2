package Lap2.example.Lap2.Controller;

import Lap2.example.Lap2.Service.ICategoryService;
import Lap2.example.Lap2.Service.IProducerService;
import Lap2.example.Lap2.ViewModel.CategoryViewModel;
import Lap2.example.Lap2.ViewModel.ProducerViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProducerController {
    @Autowired
    private IProducerService service;

    @GetMapping("/producer/{id}")
    public ResponseEntity<Object> findbyId(@PathVariable int id) {
        var producerViewModel = service.findById(id);
        return ResponseEntity.ok(producerViewModel);
    }

    @GetMapping("/producer/all")
    public ResponseEntity<List<Object>> findAll() {
        var producerViewModel = service.findAll();
        return ResponseEntity.ok(producerViewModel);
    }

    @PostMapping("/producer")
    public ResponseEntity<Object> DoInsert(@RequestBody ProducerViewModel producer) {
        var producerViewModel = service.insert(producer);
        return ResponseEntity.ok(producerViewModel);
    }

    @PutMapping("/producer/{id}")
    public ResponseEntity<Object> DoUpdate(@PathVariable int id, @RequestBody ProducerViewModel producer) {
        var producerViewModel = service.update(id,producer);
        return ResponseEntity.ok(producerViewModel);
    }

    @DeleteMapping("/producer/{id}")
    public ResponseEntity<Object> DoDelete(@PathVariable int id) {
        var producerViewModel = service.delete(id);
        return ResponseEntity.ok(producerViewModel);
    }
}
