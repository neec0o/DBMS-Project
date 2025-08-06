package de.devnico.dbms.model;

public class ConnectionRequest {
    private String dbType;
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    public String getDbType() {
        return dbType;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
