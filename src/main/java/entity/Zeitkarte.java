package entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Zeitkarte extends Ticket {

	@NotNull
	private Date gueltigAb;

	@NotNull
	private ZeitkartenTyp typ;

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() {
		return typ;
	}

	public void setTyp(ZeitkartenTyp typ) {
		this.typ = typ;
	}


	public Zeitkarte() {
	}

	public Zeitkarte(@NotNull Date gueltigAb, @NotNull ZeitkartenTyp typ) {
		this.gueltigAb = gueltigAb;
		this.typ = typ;
	}
}
