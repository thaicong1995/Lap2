package Lap2.example.Lap2.Service.Impl;
import Lap2.example.Lap2.Models.Producer;
import Lap2.example.Lap2.Repository.IProducerRepo;
import Lap2.example.Lap2.Service.IProducerService;
import Lap2.example.Lap2.ViewModel.ProducerViewModel;
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
public class ProducerService implements IProducerService<ProducerViewModel> {
    @Autowired
    private IProducerRepo repo;

    @Override
    public ProducerViewModel findById(int id) {
        var producer = repo.findById(id);
        return toModel(producer.get(), ProducerViewModel.class);
    }

    @Override
    public List<ProducerViewModel> findAll() {
        var producers = repo.findAll();
        return toModels(producers, ProducerViewModel.class);
    }

    @Override
    public ProducerViewModel insert(ProducerViewModel producer) {
        var p = toModel(producer, Producer.class);
        var saved = repo.save(p);
        return toModel(saved, ProducerViewModel.class);
    }

    @Override
    public ProducerViewModel update(int id, ProducerViewModel producer) {

        Optional<Producer> exist = repo.findById(id);
        if (exist.isEmpty()) {
            throw new RuntimeException("Producer not found for ID: " + id);
        }
        var p = exist.get();
        p.setName(producer.getName());
        p.setDescription(producer.getDescription());
        Producer updated = repo.save(p);
        return toModel(updated, ProducerViewModel.class);
    }

    @Override
    public ProducerViewModel delete(int id) {
        Optional<Producer> exist = repo.findById(id);
        if (exist.isEmpty()) {
            throw new RuntimeException("Producer not found for ID: " + id);
        }
        repo.deleteById(id);
        return null;
    }
}
