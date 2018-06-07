package entity;
import Validation.CurrentDateConstraint;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Sonderangebot {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	private int kontingent = 999;

	@NotNull
	@CurrentDateConstraint
	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5f;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket tickets;

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public int getKontingent() {
		return kontingent;
	}

	public void setKontingent(int kontingent) {
		this.kontingent = kontingent;
	}

	public Date getStartZeit() {
		return startZeit;
	}

	public void setStartZeit(Date startZeit) {
		this.startZeit = startZeit;
	}

	public int getDauer() {
		return dauer;
	}

	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	public float getPreisNachlass() {
		return preisNachlass;
	}

	public void setPreisNachlass(float preisNachlass) {
		this.preisNachlass = preisNachlass;
	}

	public Ticket getTickets() {
		return tickets;
	}

	public void setTickets(Ticket tickets) {
		this.tickets = tickets;
	}
}
