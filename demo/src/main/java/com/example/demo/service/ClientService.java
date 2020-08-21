package com.example.demo.service;

import com.example.demo.dao.ClientDao;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Esta anotação ira instanciar a classe para que possamos injetar ela em outras classes.
// @Component também funciona mas quero que essa classe seja tratada como serviço.
@Service
public class ClientService {

//    private final ClientDao clientDao;
    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    // GETTERS
    // ALL
    public List<Client> getAllClients(){
        return clientDao.selectAllClients();
    }
    // cpf
    public Optional<Client> getClientByCpf(String cpf){
        return clientDao.selectClientByCpf(cpf);
    }
    // nome
    public Optional<Client> getClientByName(String nome){
        return clientDao.getClientByName(nome);
    }
    // tel
    public Optional<Client> getClientByPhone(String telefone) {
        return clientDao.getClientByTelefone(telefone);
    }

    // INSERTS
    public void insertClient(Client client){
        insertClient(null, client);
    }
    // A interface ira por defeito gerar um id aleatoria e inserir os dados.
    void insertClient(UUID clientId, Client client){
        UUID newId = Optional.ofNullable(clientId).orElse(UUID.randomUUID()) ;
        clientDao.insertClient(newId,client);
    }

//    List<ClientStays> getAllStaysForClient(String clientCpf) {
//        return postgresClientDataAccess.selectAllClientsStays(clientCpf);
//    }

    // UPDATE
    public int updateClientByCpf(String cpf, String nome){
        return clientDao.updateClientNameByCpf(cpf, nome);
    }

    // DELETE
    public int deleteClientByCpf(String cpf){
        return clientDao.deleteClientByCpf(cpf);
    }

    public Optional<Client> find(Client clientSearch) {

        if (clientSearch.getCpf()!=null){
            return clientDao.selectClientByCpf(clientSearch.getCpf());
        }
        return Optional.empty();
    }


}
