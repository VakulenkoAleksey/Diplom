package jjd.db_project.controllers;


import jjd.db_project.entity.Visit;
import jjd.db_project.repository.CarRepository;
import jjd.db_project.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/visit")
public class VisitController {

    private final VisitRepository visitRepository;
    private final CarRepository carRepository;
    private int x;

    @Autowired
    public VisitController(VisitRepository visitRepository, CarRepository carRepository) {
        this.visitRepository = visitRepository;
        this.carRepository = carRepository;
    }

    @GetMapping(value = "/add/{id}")
    public String formVisit(Model model,  @PathVariable int id){
        x = id; //id car
        model.addAttribute("visit", new Visit());
        return "add_visit";
    }

    @PostMapping(value = "/add")
    public String addVisit(Model model, @ModelAttribute Visit visit){
        visit.setCar(carRepository.findById(x).get());
        visitRepository.save(visit);
        model.addAttribute("car", carRepository.findById(x));
        return "car";
    }

    @GetMapping(value = "/car/{id}")
    public String showVisits(Model model, @PathVariable int id){
        model.addAttribute("car", carRepository.findById(id));
        return "car";
    }

    @GetMapping(value = "/showOne/{id}")
    public String show(Model model, @PathVariable int id){
        model.addAttribute("car", carRepository.findById(id));
        return "car";
    }


    @GetMapping (value = "/remove/{id}")
    public String remove(@PathVariable int id, Model model){
        int i = visitRepository.findById(id).get().getCar().getId();
        visitRepository.deleteById(id);
        model.addAttribute("car", carRepository.findById(i));
        return "car";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("visit", visitRepository.findById(id).get());
        return "update_visit";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute Visit visit, Model model) {
        Visit visitInDb = visitRepository.findById(visit.getId()).get();
        visitInDb.setDateOfVisit(visit.getDateOfVisit());
        visitInDb.setMileage(visit.getMileage());
        visitRepository.save(visitInDb);
        model.addAttribute("car", carRepository.findById(visitInDb.getCar().getId()));
        return "car";
    }


}
