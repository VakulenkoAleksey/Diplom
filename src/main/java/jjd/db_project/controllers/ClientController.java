package jjd.db_project.controllers;

import jjd.db_project.entity.Client;
import jjd.db_project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {


    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(ClientRepository repository) {
        this.clientRepository = repository;
    }


    @GetMapping(value = "/add")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "add_client";
    }

    @PostMapping(value = "/add")
    public String addClient(Model model, @ModelAttribute Client client) {
        clientRepository.save(client);
        model.addAttribute("client", clientRepository.findById(client.getId()));
        return "client";
    }

    @GetMapping(value = "/showOne/{id}")
    public String show(Model model, @PathVariable int id) {
        model.addAttribute("client", clientRepository.findById(id));
        return "client";
    }

    @RequestMapping(value = "/showAll")
    public String showAll(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove(@PathVariable int id) {
        clientRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientRepository.findById(id).get());
        return "update_client";
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute Client client, Model model) {
        Client clientInDb = clientRepository.findById(client.getId()).get();
        clientInDb.setEmail(client.getEmail());
        clientInDb.setName(client.getName());
        clientInDb.setPhone(client.getPhone());
        clientRepository.save(clientInDb);
        model.addAttribute("client", clientRepository.findById(client.getId()));
        return "client";
    }

    @GetMapping("/phone")
    public String findByPhone(Model model, @RequestParam("phone") String phone) {
            model.addAttribute("client", clientRepository.findByPhone(phone));
            return "client";
    }
}