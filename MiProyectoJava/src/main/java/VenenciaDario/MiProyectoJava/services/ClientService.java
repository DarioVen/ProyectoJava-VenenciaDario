package VenenciaDario.MiProyectoJava.services;

import VenenciaDario.MiProyectoJava.entities.Client;
import VenenciaDario.MiProyectoJava.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public Optional<Client> getClient(Long id) {
        return this.clientRepository.findById(id);
    }

    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }
    public Optional<Client> updateClient(Long id, Client client) {
        Optional<Client> clientDB = this.clientRepository.findById(id);

        if(clientDB.isEmpty()) {
            return Optional.empty();
        }
        clientDB.get().setId(client.getId());
        clientDB.get().setName(client.getName());
        clientDB.get().setBadname(client.getBadname());
        clientDB.get().setDocnumber(client.getDocnumber());
        clientDB.get().setInvoices(client.getInvoices());

        this.clientRepository.save(clientDB.get());

        return clientDB;
    }

    public Optional<Client> deleteClient(Long id) {
        Optional<Client> clientDB = this.clientRepository.findById(id);

        if(clientDB.isEmpty()) {
            return Optional.empty();
        }

        this.clientRepository.deleteById(id);

        return clientDB;
    }
}




