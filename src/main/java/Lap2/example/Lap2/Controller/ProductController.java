package Lap2.example.Lap2.Controller;

import Lap2.example.Lap2.Service.IProducerService;
import Lap2.example.Lap2.Service.IProductService;
import Lap2.example.Lap2.ViewModel.ProducerViewModel;
import Lap2.example.Lap2.ViewModel.ProductViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> findbyId(@PathVariable int id) {
        var producerViewModel = service.findById(id);
        return ResponseEntity.ok(producerViewModel);
    }

    @GetMapping("/product/all")
    public ResponseEntity<ProductViewModel.ProductResponse> findAll(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                                    @RequestParam(required = false) String key,
                                                                    @RequestParam(required = false) Integer categoryId,
                                                                    @RequestParam(required = false) Integer producerId) {

        ProductViewModel.ProductRequest request = new ProductViewModel.ProductRequest();
        request.setPage(page);
        request.setPageSize(pageSize);
        request.setCategory_id(categoryId);
        request.setProducer_id(producerId);
        request.setKey(key);

        ProductViewModel.ProductResponse response = service.paging(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/product/{c_id}/{p_id}")
    public ResponseEntity<Object> DoInsert(@PathVariable int c_id,@PathVariable int p_id,@RequestBody ProductViewModel product) {
        var productViewModel = service.insert(c_id,p_id,product);
        return ResponseEntity.ok(productViewModel);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> DoUpdate(@PathVariable int id, @RequestBody ProductViewModel product) {
        var productViewModel = service.update(id,product);
        return ResponseEntity.ok(productViewModel);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> DoDelete(@PathVariable int id) {
        var productViewModel = service.delete(id);
        return ResponseEntity.ok(productViewModel);
    }
}
