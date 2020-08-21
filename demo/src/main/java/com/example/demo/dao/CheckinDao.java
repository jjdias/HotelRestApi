package com.example.demo.dao;

import com.example.demo.model.Checkin;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public class CheckinDao {

    private final JdbcTemplate jdbcTemplate;

    // Instantiating the jdbc template on spring.
    @Autowired
    public CheckinDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // GETTER



    // INSERT
    public int insertCheckin(UUID id, UUID clientId, Checkin checkin) {
        String sql = "INSERT INTO checkin(id, clientId, entrada, saida, adicionalVeiculo) " +
                "VALUES (?,?,?,?,?)";

        return jdbcTemplate.update(sql,
                id,
                clientId,
                checkin.getEntrada(),
                checkin.getSaida(),
                checkin.getAdicionalVeiculo());
    }


    // UPDATE
    public void doCheckOut(UUID clientId, LocalDate saida) {
        String sql = "UPDATE checkin " +
                "SET saida = ? " +
                "WHERE clientId = ? ";

        jdbcTemplate.update(sql, saida, clientId);
    }

    public List<Checkin> getClientesHospedados() {
        String sql = "SELECT checkin.id AS checkinId, checkin.entrada, checkin.adicionalVeiculo, " +
                "client.id, client.nome, client.cpf, client.telefone " +
                "FROM client " +
                "JOIN checkin ON client.id = checkin.clientId " +
                "WHERE checkin.saida IS NULL";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");

            UUID checkinId = UUID.fromString(resultSet.getString("checkinId"));
            LocalDate entrada = resultSet.getDate("entrada").toLocalDate();
            Boolean adicionalVeiculo = resultSet.getBoolean("adicionalVeiculo");

            Client client = new Client(
                    id,
                    nome,
                    cpf,
                    telefone
            );

            return new Checkin(checkinId, client, entrada, null, adicionalVeiculo, null);

        });

    }

    public List<Checkin> getClientesCheckedout() {
        String sql = "SELECT checkin.id AS checkinId, checkin.entrada, checkin.saida, checkin.adicionalVeiculo, " +
                "client.id, client.nome, client.cpf, client.telefone " +
                "FROM client " +
                "JOIN checkin ON client.id = checkin.clientId " +
                "WHERE checkin.saida IS NOT NULL";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");

            UUID checkinId = UUID.fromString(resultSet.getString("checkinId"));
            LocalDate entrada = resultSet.getDate("entrada").toLocalDate();
            LocalDate saida = resultSet.getDate("saida").toLocalDate();
            Boolean adicionalVeiculo = resultSet.getBoolean("adicionalVeiculo");

            Client client = new Client(
                    id,
                    nome,
                    cpf,
                    telefone
            );

            return new Checkin(checkinId, client, entrada, saida, adicionalVeiculo, null);

        });
    }


    // DELETE
    public int doCheckinEntryDelete(UUID checkinId) {
        // Deleting row using SQLs
        String sql = "DELETE FROM checkin " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, checkinId);
    }

}
