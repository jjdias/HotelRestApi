package com.example.demo.api;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/client")
@RestController
public class ClientController {

    // Connection to service
    private final ClientService clientService;

    // Instantiation
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // GETTERS
    // GET all
    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    // GET specific client
    // cpf
    @GetMapping(path = "documento/{cpf}")
    public Client getClientByCpf(
            @PathVariable("cpf") String cpf){
        return clientService.getClientByCpf(cpf)
                .orElse(null);
    }
    // nome
    @GetMapping(path = "nome/{nome}")
    public Client getClientByName(
            @PathVariable("nome") String nome){
        return clientService.getClientByName(nome)
                .orElse(null);
    }
    // tel
    @GetMapping(path = "telefone/{telefone}")
    public Client getClientByPhone(
            @PathVariable("telefone") String telefone){
        return  clientService.getClientByPhone(telefone)
                .orElse(null);
    }

   // CREATE
    @PostMapping
    public void addNewClient(@RequestBody @Valid Client client){
        clientService.insertClient(client);
    }

    // UPDATE
    @PutMapping(path = "{cpf}")
    public void updateClientByCpf(@PathVariable("cpf") String cpf
            ,@Valid @NonNull @RequestBody Client client){
        clientService.updateClientByCpf(cpf, client.getName());
    }

    // DELETE
    @DeleteMapping(path = "{cpf}")
    public void deleteClientByCpf(@PathVariable("cpf") String cpf){
        clientService.deleteClientByCpf(cpf);
    }




}
