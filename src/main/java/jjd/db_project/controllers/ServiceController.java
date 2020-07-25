package jjd.db_project.controllers;

import jjd.db_project.entity.Service;
import jjd.db_project.repository.ServiceRepository;
import jjd.db_project.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/service")
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final VisitRepository visitRepository;
    private int x;


    @Autowired
    public ServiceController(ServiceRepository serviceRepository, VisitRepository visitRepository) {
        this.serviceRepository = serviceRepository;
        this.visitRepository = visitRepository;
    }

    @GetMapping(value = "/add/{id}")
    public String formVisit(Model model, @PathVariable int id){
        this.x = id; //id visit
        model.addAttribute("service", new Service());
        return "add_service";
    }

    @PostMapping(value = "/add")
    public String addVisit(Model model, @ModelAttribute Service service){
        service.setVisit(visitRepository.findById(x).get());
        serviceRepository.save(service);
        model.addAttribute("visit", visitRepository.findById(x));
        return "visit";
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable int id){
        model.addAttribute("visit", visitRepository.findById(id));
        return "visit";
    }

    @GetMapping (value = "/remove/{id}")
    public String remove(Model model, @PathVariable int id){
        int visitId = serviceRepository.findById(id).get().getVisit().getId();
        serviceRepository.deleteById(id);
        model.addAttribute("visit", visitRepository.findById(visitId));
        return "visit";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("service", serviceRepository.findById(id).get());
        return "update_service";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute Service service, Model model) {
        Service serviceInDb = serviceRepository.findById(service.getId()).get();
        serviceInDb.setService(service.getService());
        serviceInDb.setPrice(service.getPrice());
        serviceRepository.save(serviceInDb);
        model.addAttribute("visit", visitRepository.findById(serviceInDb.getVisit().getId()));
        return "visit";
    }

}
