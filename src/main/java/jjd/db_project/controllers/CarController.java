package jjd.db_project.controllers;

import jjd.db_project.entity.Car;
import jjd.db_project.entity.Client;
import jjd.db_project.repository.CarRepository;
import jjd.db_project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/car")
public class CarController {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private int id;

    @Autowired
    public CarController(CarRepository repository, ClientRepository clientRepository) {
        this.carRepository = repository;
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/add/{id}")
    public String formCar(Model model, @PathVariable int id) {
        this.id = id; //id client
        model.addAttribute("car", new Car());
        model.addAttribute(clientRepository.findById(id));
        return "add_car";
    }

    @PostMapping(value = "/add")
    public String addCar(Model model, @ModelAttribute Car car) {
        car.setClient(clientRepository.findById(id).get());
        carRepository.save(car);
        model.addAttribute("client", clientRepository.findById(id));
        return "client";
    }


    @RequestMapping(value = "/remove/{id}")
    public String remove(@PathVariable int id, Model model) {
        int i = carRepository.findById(id).get().getClient().getId();
        carRepository.deleteById(id);
        model.addAttribute("client", clientRepository.findById(i));
        return "client";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "update_car";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute Car car, Model model) {
        Car carInDb = carRepository.findById(car.getId()).get();
        carInDb.setModel(car.getModel());
        carInDb.setVin(car.getVin());
        carInDb.setGosnumber(car.getGosnumber());
        carRepository.save(carInDb);
        int i = carInDb.getClient().getId();
        model.addAttribute("client", clientRepository.findById(carInDb.getClient().getId()));
        System.out.println(i);
        return "client";
    }

}
