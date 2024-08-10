package Lap2.example.Lap2.Service.Impl;

import Lap2.example.Lap2.Models.Producer;
import Lap2.example.Lap2.Models.Product;
import Lap2.example.Lap2.Repository.ICategoryRepo;
import Lap2.example.Lap2.Repository.IProducerRepo;
import Lap2.example.Lap2.Repository.IProductRepo;
import Lap2.example.Lap2.Service.IProductService;
import Lap2.example.Lap2.ViewModel.ProducerViewModel;
import Lap2.example.Lap2.ViewModel.ProductViewModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static Lap2.example.Lap2.MapDto.toModel;
import static Lap2.example.Lap2.MapDto.toModels;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService implements IProductService<ProductViewModel> {
    @Autowired
    private IProductRepo repo;
    @Autowired
    private IProducerRepo repo_p;
    @Autowired
    private ICategoryRepo repo_c;

    @Override
    public ProductViewModel findById(int id) {
        var product = repo.findById(id);
        return toModel(product.get(), ProductViewModel.class);
    }

    @Override
    public ProductViewModel.ProductResponse paging(ProductViewModel.ProductRequest request) {
        int offset = (request.getPage() - 1) * request.getPageSize();

        request.getKey();

        List<Product> products = repo.Paging(request.getCategory_id(),
                request.getProducer_id(),
                request.getKey(),
                request.getPageSize(),
                offset);

        long totalItems = repo.countProducts(
                request.getCategory_id(),
                request.getProducer_id(),
                request.getKey()
        );

        int totalPages = (int) Math.ceil((double) totalItems / request.getPageSize());

        ProductViewModel.ProductResponse response = new ProductViewModel.ProductResponse();
        response.setProducts(products);
        response.setTotal((int) totalItems);
        response.setTotalPages(totalPages);

        return response;
    }


    @Override
    public ProductViewModel insert(int category_id, int producer_id,ProductViewModel product) {
        var exitst_c = repo_c.findById(category_id);
        var exitst_p = repo_p.findById(producer_id);
        if (exitst_c.isEmpty() || exitst_p.isEmpty()) {

            throw new RuntimeException("Not found");
        }

        var pr = toModel(product, Product.class);

        pr.setCategory(exitst_c.get());
        pr.setProducer(exitst_p.get());
        var saved = repo.save(pr);
        return toModel(saved, ProductViewModel.class);
    }

    @Override
    public ProductViewModel update(int product_id, ProductViewModel product) {

        var exit_pr = repo.findById(product_id);
        if (exit_pr.isEmpty()) {
            throw new RuntimeException("Not found");
        }

        var pr = exit_pr.get();
        pr.setName(product.getName());
        pr.setPrice(product.getPrice());
        pr.setDescription(product.getDescription());
        pr.setUrlImage(product.getUrlImage());
       if (product.getCategory_id() > 0){
           pr.setCategory(repo_c.findById(product.getCategory_id()).get());
       }
        if (product.getProducer_id() > 0){
            pr.setProducer(repo_p.findById(product.getProducer_id()).get());
        }

        var saved = repo.save(pr);
        return toModel(saved, ProductViewModel.class);
    }

    @Override
    public ProductViewModel delete(int id) {
        Optional<Product> exist = repo.findById(id);
        if (exist.isEmpty()) {
            throw new RuntimeException("Producer not found for ID: " + id);
        }
        repo.deleteById(id);
        return null;
    }
}
