package com.example.demo.api;

import com.example.demo.model.Checkin;
import com.example.demo.model.Client;
import com.example.demo.service.CheckinService;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/checkin")
@RestController
public class CheckinController {

    // Connection to service
    private final CheckinService checkinService;

    // Instantiation
    @Autowired
    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }


   // CREATE
    @PostMapping
    public void addNewCheckin(@RequestBody @Valid Checkin checkin){
        checkinService.addNewCheckin(checkin);
    }

    // UPDATE
    @PutMapping(path = "checkout/{cpf}")
    public void updateCheckOut(@PathVariable("cpf") String cpf,
                               @RequestBody Checkin chekin){
        checkinService.doCheckOut(cpf, chekin.getSaida());
    }

    // GET
    @GetMapping(path = "clientes/hospedados")
    public List<Checkin> getClientesHospedados(){
        return checkinService.getClientesHospedados();
    }

    @GetMapping(path = "clientes/checkedout")
    public List<Checkin> getClientesCheckout(){
        return checkinService.getClientesCheckedout();
    }

    // DELETE
    @DeleteMapping(path = "delete/{id}")
    public void deleteCheckinEntry(@PathVariable("id") UUID checkinId){
        checkinService.doCheckinEntryDelete(checkinId);
    }
}
