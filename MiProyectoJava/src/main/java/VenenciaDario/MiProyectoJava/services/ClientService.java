package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private final JdbcTemplate jdbc;

    public ClientService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

            public List<Client> getAllClients() {
                try {
                    return jdbc.query(
                            "SELECT * FROM CLIENTS",
                    (rs, rowNum) -> new Client(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("badname"),
                            rs.getString("docnumber")
                    )
            );
        } catch (Exception e) {
            // Log the exception and handle it appropriately
            throw new RuntimeException("Failed to retrieve clients", e);
        }
    }

    public void createClient(Client client) {
        try {
            jdbc.update(
                    "INSERT INTO CLIENTS (name, badname, docnumber) VALUES (?, ?, ?)",
                    client.getName(),
                    client.getBadname(),
                    client.getDocnumber()
            );
        } catch (Exception e) {
            // Log the exception and handle it appropriately
            throw new RuntimeException("Failed to create client", e);
        }
    }
}
