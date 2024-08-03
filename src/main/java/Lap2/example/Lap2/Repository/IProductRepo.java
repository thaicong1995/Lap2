package Lap2.example.Lap2.Repository;

import Lap2.example.Lap2.Models.Product;
import Lap2.example.Lap2.ViewModel.ProductViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product " +
            "WHERE (:categoryId IS NULL OR category_id = :categoryId) " +
            "AND (:producerId IS NULL OR producer_id = :producerId) " +
            "ORDER BY id DESC " +
            "LIMIT :pageSize OFFSET :offset",
            nativeQuery = true)
    List<Product> Paging(@Param("categoryId") Integer categoryId,
                         @Param("producerId") Integer producerId,
                         @Param("pageSize") int pageSize,
                         @Param("offset") int offset);



    @Query(value = "SELECT COUNT(*) FROM product " +
            "WHERE (:categoryId IS NULL OR category_id = :categoryId) " +
            "AND (:producerId IS NULL OR producer_id = :producerId)",
            nativeQuery = true)
    long countProducts(@Param("categoryId") Integer categoryId,
                       @Param("producerId") Integer producerId);
}
