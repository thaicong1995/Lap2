package Lap2.example.Lap2.Repository;

import Lap2.example.Lap2.Models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducerRepo extends JpaRepository<Producer, Integer> {

}
