package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    private List<Balloon> balloons;

    public BalloonRepository(){
        this.balloons = new ArrayList<>();
        balloons.add(new Balloon("Red Balloon", "Red Balloon Description"));
        balloons.add(new Balloon("Green Balloon", "Green Balloon Description"));
        balloons.add(new Balloon("Yellow Balloon", "Yellow Balloon Description"));
        balloons.add(new Balloon("Blue Balloon", "Blue Balloon Description"));
    }

    public List<Balloon> findAllBalloons() {
        return this.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloons.stream()
                .filter(b->b.getName().contains(text) || b.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer){
        Balloon balloon = new Balloon(name,description,manufacturer);
        this.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public Optional<Balloon> update(String name,String description,Manufacturer manufacturer,Long id){
        this.balloons.removeIf(i->i.getId().equals(id));
        Balloon balloon = new Balloon(name,description,manufacturer);
        this.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public boolean deleteById(Long id){
        return this.balloons.removeIf(i->i.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id){
        return this.balloons.stream()
                .filter(i->i.getId().equals(id))
                .findFirst();
    }
}
