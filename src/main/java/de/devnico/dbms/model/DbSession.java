package de.devnico.dbms.model;

import lombok.Getter;

import java.sql.Connection;

@Getter
public class DbSession {
    private final String id;
    private final Connection connection;

    public DbSession(String id, Connection connection) {
        this.id = id;
        this.connection = connection;
    }

}
