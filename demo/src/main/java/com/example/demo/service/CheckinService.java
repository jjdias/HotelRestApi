package com.example.demo.service;

import com.example.demo.dao.CheckinDao;
import com.example.demo.model.Checkin;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

// Esta anotação ira instanciar a classe para que possamos injetar ela em outras classes.
// @Component também funciona mas quero que essa classe seja tratada como serviço.
@Service
public class CheckinService {

//    private final ClientDao clientDao;
    private final CheckinDao checkinDao;
    private final ClientService clientService;

    @Autowired
    public CheckinService(CheckinDao checkinDao, ClientService clientService) {
        this.checkinDao = checkinDao;
        this.clientService = clientService;
    }


    public void addNewCheckin(Checkin checkin) {
        UUID newId = Optional.ofNullable(checkin.getId()).orElse(UUID.randomUUID()) ;

        Optional<Client> client = clientService.find(checkin.getClient());

        if (client.isEmpty()){
            throw new IllegalStateException("Hospede não encontrado.");
        }
        checkinDao.insertCheckin(newId, client.get().getId(), checkin);
    }

    public void doCheckOut(String cpf, LocalDate saida) {
        Client clientSearch = new Client(null,null, cpf, null);

        Optional<Client> client = clientService.find(clientSearch);

        if (client.isEmpty()){
            throw new IllegalStateException("Hospede não encontrado.");
        }

        checkinDao.doCheckOut(client.get().getId(), saida);
    }

    // GET cliente hospedados
    public List<Checkin> getClientesHospedados() {
        List<Checkin> checkinList = checkinDao.getClientesHospedados();

        for (Checkin checkin : checkinList){

            // conta acontece
            double conta = (double) 0;

            // Eu tava muito cansado a essa altura.
            // Get the stay period
            int period = periodCalculation(checkin.getEntrada(), null);


            // He might have made a reservation.
            if (period > 0) {

                // Make a list of dates
                List<LocalDate> listOfDates = listOfDates(period, checkin.getEntrada());

                // Calculate the bill
                conta = contaCalculator(listOfDates, checkin.getAdicionalVeiculo());
            }
            // Set conta to 0 if there are no charges or something if there are.
            checkin.setConta(conta);
        }

        return checkinList;

    }

    // GET of clients that already checked out
    public List<Checkin> getClientesCheckedout() {
        List<Checkin> checkinList = checkinDao.getClientesCheckedout();

        for (Checkin checkin : checkinList){

            // Initializing an empty bill variable
            double conta = (double) 0;

            // Get the stay period
            // Could just call the list and count it.
            int period = periodCalculation(checkin.getEntrada(), checkin.getSaida());

            // He might have made a reservation.
            if (period > 0) {

                // Make a list of dates
                List<LocalDate> listOfDates = listOfDates(period, checkin.getEntrada());

                // Calculate the bill
                conta = contaCalculator(listOfDates, checkin.getAdicionalVeiculo());
            }
            // Set conta to 0 if there are no charges or something if there are.
            checkin.setConta(conta);
        }

        return checkinList;
    }


    public int periodCalculation(LocalDate entrada, LocalDate saida){

        // If saida is not null return the calculation with saida
        if (saida != null) {
            return (int) DAYS.between(entrada, saida);
        }
        // If saida is null we assign it the now parameter
        return (int) DAYS.between(entrada, LocalDate.now());
    }

    public List<LocalDate> listOfDates(int period, LocalDate entrada){
        // Transform entry date into LocalDate


        // Calculate a list of dates
        List<LocalDate> listOfDates = new ArrayList<LocalDate>(period);
        for (int i = 0; i < period; i++) {
            LocalDate d = entrada.plusDays(i);
            listOfDates.add(d);
        }
        return listOfDates;
    }

    public double contaCalculator(List<LocalDate> listOfDates, boolean adicionalVeiculo){

        double conta = (double) 0;

        for (LocalDate day : listOfDates) {
            if (day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY) {
                conta += 210;
                if (adicionalVeiculo) {
                    conta += 20;
                }
            } else {
                conta += 210;
                if (adicionalVeiculo) {
                    conta += 15;
                }
            }
        }
        return conta;
    }


    //DELETE
    public int doCheckinEntryDelete(UUID checkinId) {
        return checkinDao.doCheckinEntryDelete(checkinId);
    }

}
