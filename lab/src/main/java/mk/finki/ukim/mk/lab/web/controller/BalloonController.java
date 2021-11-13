package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/balloons")
public class BalloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("balloons",this.balloonService.listAll());
        return "listBalloons";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam Long manufacturerId,
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam(required = false) Long id){

        Manufacturer manufacturer = this.manufacturerService.findById(manufacturerId).get();
        if (id == null) {
            this.balloonService.save(name,description,manufacturer);
        } else {
            this.balloonService.update(name,description,manufacturer,id);
        }
        return "redirect:/balloons";
    }

    @GetMapping("/delete/{id}")
    public String deleteBalloon(@PathVariable Long id){
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id,Model model){

        if (!this.balloonService.findById(id).isPresent()) {
            return "redirect:/balloons?error=Balloon with id: " + id + " was not found";
        }
        Balloon balloon = this.balloonService.findById(id).get();
        model.addAttribute("balloon",balloon);
        model.addAttribute("manufacturers",this.manufacturerService.findAll());
        return "add-balloon";
    }

    @GetMapping("/add-form")
    public String getAllBalloonPage(Model model) {
        model.addAttribute("manufacturers",this.manufacturerService.findAll());
        return "add-balloon";
    }
}
