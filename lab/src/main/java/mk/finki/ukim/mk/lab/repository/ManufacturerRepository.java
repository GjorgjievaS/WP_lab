package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    private List<Manufacturer> manufacturerList;

    public ManufacturerRepository() {
        this.manufacturerList = new ArrayList<>();

        manufacturerList.add(new Manufacturer("Nike", "NY NY", "NY NY"));
        manufacturerList.add(new Manufacturer("Adidas","DE","DE"));
        manufacturerList.add(new Manufacturer("Amazon","US","US"));
        manufacturerList.add(new Manufacturer("Nestle","CH","CH"));
        manufacturerList.add(new Manufacturer("Toyota","JPN","JPN"));
    }

    public List<Manufacturer> findAll(){
        return this.manufacturerList;
    }

    public Optional<Manufacturer> findById(Long id){
        return this.manufacturerList.stream()
                .filter(m->m.getId().equals(id))
                .findFirst();
    }
}
