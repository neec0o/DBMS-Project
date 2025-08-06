package de.devnico.dbms.model;

import lombok.Getter;

@Getter
public class ConnectionRequest {
    private String dbType;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

}
