package entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long ID;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	protected Strecke strecke;

	@Transient
	protected Zahlung zahlung;


	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Strecke getStrecke() {
		return strecke;
	}

	public void setStrecke(Strecke strecke) {
		this.strecke = strecke;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
}
