package Lap2.example.Lap2.Service;

import Lap2.example.Lap2.Models.Producer;
import Lap2.example.Lap2.ViewModel.ProducerViewModel;

import java.util.List;

public interface IProducerService <T> {
    T findById(int id);

    List<T> findAll();

    T insert(ProducerViewModel producer);

    T update(int id, ProducerViewModel producer);

    T delete(int id);
}