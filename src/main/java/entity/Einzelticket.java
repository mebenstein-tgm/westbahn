package entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Einzelticket extends Ticket {

	private TicketOption ticketOption;

	public TicketOption getTicketOption() {
		return ticketOption;
	}

	public Einzelticket() {
	}

	public void setTicketOption(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}

	public Einzelticket(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}
}
