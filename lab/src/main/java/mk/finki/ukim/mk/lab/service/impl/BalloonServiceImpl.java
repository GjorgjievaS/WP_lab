package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }


    @Override
    public List<Balloon> listAll() {
        return this.balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return this.balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {
        return this.balloonRepository.save(name, description, manufacturer);
    }

    @Override
    public Optional<Balloon> update(String name, String description, Manufacturer manufacturer, Long id) {
        return this.balloonRepository.update(name, description, manufacturer, id);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }
}
