package de.devnico.dbms.service;

import de.devnico.dbms.model.DbSession;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DatabaseService {
    public List<Map<String, Object>> getTableContent(DbSession session, String dbName, String tableName, int page, int size, String search) throws SQLException {
        Connection conn = session.getConnection();
        Statement stmt = conn.createStatement();

        stmt.execute("USE " + dbName);

        String baseQuery = "SELECT * FROM " + tableName;
        if (search != null && !search.isEmpty()) {
            ResultSet columnsRs = conn.getMetaData().getColumns(dbName, null, tableName, null);
            List<String> searchColumns = new ArrayList<>();
            while (columnsRs.next()) {
                searchColumns.add(columnsRs.getString("COLUMN_NAME"));
            }

            String likeClause = searchColumns.stream()
                    .map(col -> col + " LIKE '%" + search + "%'")
                    .collect(Collectors.joining(" OR "));

            baseQuery += " WHERE " + likeClause;
        }

        // Pagination
        int offset = page * size;
        baseQuery += " LIMIT " + size + " OFFSET " + offset;

        ResultSet rs = stmt.executeQuery(baseQuery);
        ResultSetMetaData meta = rs.getMetaData();

        List<Map<String, Object>> rows = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                row.put(meta.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }

        return rows;
    }


}
