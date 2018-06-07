package entity;
import Validation.CorrectEmailConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@NamedQueries({
        @NamedQuery(
                name = "ReservationsViaEmail",
                query = "SELECT reservierungen FROM entity.Benutzer b WHERE b.eMail = :email"
        ),
        @NamedQuery(
                name = "Montaskarte",
                query = "SELECT DISTINCT b From entity.Benutzer b INNER JOIN b.tickets ticket where ticket in (SELECT m FROM entity.Zeitkarte m WHERE m.typ = entity.ZeitkartenTyp.MONATSKARTE )"
        )
})

@Entity
public class Benutzer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @NotNull
    private String vorName;

    @NotNull
    private String nachName;

    @NotNull
    @CorrectEmailConstraint
    private String eMail;

    @NotNull
    private String passwort;

    @NotNull
    private String smsNummer;

    @NotNull
    private Long verbuchtePraemienMeilen;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservierung> reservierungen;

    public Benutzer() {
        tickets = new HashSet<>();
        reservierungen = new HashSet<>();
    }

    public Benutzer(@NotNull String vorName, @NotNull String nachName, @NotNull String eMail, @NotNull String passwort, @NotNull String smsNummer) {
        this.vorName = vorName;
        this.nachName = nachName;
        this.eMail = eMail;
        this.passwort = passwort;
        this.smsNummer = smsNummer;

        verbuchtePraemienMeilen = 0l;
        tickets = new HashSet<>();
        reservierungen = new HashSet<>();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getSmsNummer() {
        return smsNummer;
    }

    public void setSmsNummer(String smsNummer) {
        this.smsNummer = smsNummer;
    }

    public Long getVerbuchtePraemienMeilen() {
        return verbuchtePraemienMeilen;
    }

    public void setVerbuchtePraemienMeilen(Long verbuchtePraemienMeilen) {
        this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Reservierung> getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(Set<Reservierung> reservierungen) {
        this.reservierungen = reservierungen;
    }

    public void addReservierung(Reservierung reservierung) {
        reservierungen.add(reservierung);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
