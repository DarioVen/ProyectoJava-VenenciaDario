package VenenciaDario.MiProyectoJava.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "badname")
    private String badname;

    @Column(name = "docnumber")
    private String docnumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices;

    public Client() {
    }

    public Client(String name, String badname, String docnumber, List<Invoice> invoices) {
        this.name = name;
        this.badname = badname;
        this.docnumber = docnumber;
        this.invoices = invoices;
    }

    public Client(long id, String name, String badname, String docnumber) {
        this.id = id;
        this.name = name;
        this.badname = badname;
        this.docnumber = docnumber;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
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