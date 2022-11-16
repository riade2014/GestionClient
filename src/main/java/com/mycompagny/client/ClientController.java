package com.mycompagny.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {
    @Autowired private ClientService service;

    @GetMapping("/clients")
    public String showClientList(Model model){
        List<Client> listClients = service.listAll();
        model.addAttribute("listClients",listClients);

        return  "clients";
    }

    @GetMapping("/clients/new")
    public String showNewForm(Model model){
        model.addAttribute("client",new Client());
        model.addAttribute("pageTitle", "Add new client");
        return "client_form";
    }

    @PostMapping("/clients/save")
    public String saveClient(Client client, RedirectAttributes ra){
        service.save(client);
        ra.addFlashAttribute("message", "The client has been save successfully");
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
        try {
           Client client = service.get(id);
           model.addAttribute("client",client);
           model.addAttribute("pageTitle", "Edit client (Id "+id+")");
           return "client_form";
        } catch (ClientNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/clients";
        }
    }

    @GetMapping("/clients/delete/{id}")
    public String deleteClient(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","The client with id : " + id + " has been deleted successfully");
        } catch (ClientNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/clients";
    }
}
