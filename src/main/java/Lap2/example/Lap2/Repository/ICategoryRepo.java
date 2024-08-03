package Lap2.example.Lap2.Repository;

import Lap2.example.Lap2.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Integer> {

}
