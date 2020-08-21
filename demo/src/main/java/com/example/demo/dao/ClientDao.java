package com.example.demo.dao;

import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class ClientDao {

    private final JdbcTemplate jdbcTemplate;

    // Instantiating the jdbc template on spring.
    @Autowired
    public ClientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GETTER
    // GET all
    public List<Client> selectAllClients() {
        String sql = "SELECT id, nome, cpf, telefone FROM client";

        // We use query for returning us a list
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");
            return new Client(
                    id,
                    nome,
                    cpf,
                    telefone);
        });
    }
    // GETTER specific client
    public Optional<Client> selectClientByCpf(String cpf) {

        // The quesiton marl is where we pass the cpf
        String sql = "SELECT id, nome, cpf, telefone " +
                "FROM client " +
                "WHERE cpf = ?";

        // Here we use queryForObject to return only the object
        Client client = jdbcTemplate.queryForObject(sql, new Object[]{cpf}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String clientCpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");
            return new Client(
                    id,
                    nome,
                    clientCpf,
                    telefone);
        });
        return Optional.ofNullable(client);
    }

    public Optional<Client> getClientByName(String nome) {
        // The quesiton marl is where we pass the name
        String sql = "SELECT id, nome, cpf, telefone " +
                "FROM client " +
                "WHERE nome = ?";

        // Here we use queryForObject to return only the object
        Client client = jdbcTemplate.queryForObject(sql, new Object[]{nome}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String clientNome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");
            return new Client(
                    id,
                    clientNome,
                    cpf,
                    telefone);
        });
        return Optional.ofNullable(client);
    }

    public Optional<Client> getClientByTelefone(String telefone) {
        // The quesiton marl is where we pass the phone
        String sql = "SELECT id, nome, cpf, telefone " +
                "FROM client " +
                "WHERE telefone = ?";

        // Here we use queryForObject to return only the object
        Client client = jdbcTemplate.queryForObject(sql, new Object[]{telefone}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String clientTelefone = resultSet.getString("telefone");
            return new Client(
                    id,
                    nome,
                    cpf,
                    clientTelefone);
        });
        return Optional.ofNullable(client);
    }



    // INSERT
    public int insertClient(UUID id, Client client) {

        String sql = "INSERT INTO client(id, nome, cpf, telefone) VALUES (?,?,?,?)";

        return jdbcTemplate.update(sql, id, client.getName(), client.getCpf(), client.getTelefone());
    }

    // UPDATE
    // The ideal is to catch the user using the unique id but for now cpf is easier for me to work with
    public int updateClientNameByCpf(String cpf, String nome) {
        // Updating the values in the table with SQLs update method
        String sql = "UPDATE client " +
                        "SET nome = ? " +
                        "WHERE cpf = ?";
        return jdbcTemplate.update(sql, nome, cpf);
    }

    // DELETE
    public int deleteClientByCpf(String cpf) {
        // Deleting row using SQLs
        String sql = "DELETE FROM client " +
                "WHERE cpf = ?";
        return jdbcTemplate.update(sql, cpf);
    }


}
