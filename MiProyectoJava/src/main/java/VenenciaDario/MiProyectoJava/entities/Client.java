package VenenciaDario.MiProyectoJava.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "badname")
    private String badname;

    @Column(name = "docnumber")
    private String docnumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices;

    // Constructor vacío
    public Client() {
    }

    // Constructor con todos los campos excepto el id (ya que es autoincremental)
    public Client(String name, String badname, String docnumber, List<Invoice> invoices) {
        this.name = name;
        this.badname = badname;
        this.docnumber = docnumber;
        this.invoices = invoices;
    }

    // Constructor con ID (para objetos existentes)
    public Client(int id, String name, String badname, String docnumber) {
        this.id = id;
        this.name = name;
        this.badname = badname;
        this.docnumber = docnumber;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadname() {
        return badname;
    }

    public void setBadname(String badname) {
        this.badname = badname;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public void setDocnumber(String docnumber) {
        this.docnumber = docnumber;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    // Metodo toString para representación en cadena de la entidad
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", badname='" + badname + '\'' +
                ", docnumber='" + docnumber + '\'' +
                ", invoices=" + invoices +
                '}';
    }
}