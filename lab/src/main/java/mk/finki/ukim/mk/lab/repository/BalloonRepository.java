package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
}
