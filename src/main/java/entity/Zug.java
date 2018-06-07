package entity;
import Validation.DifferentStartEndConstraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;

@Entity
@DifferentStartEndConstraint
public class Zug implements StartEndBahnhof{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@NotNull
	private Date startZeit;

	private int sitzPlaetze = 500;

	private int fahrradStellplaetze = 50;

	private int rollStuhlPlaetze = 10;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Bahnhof start;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Bahnhof ende;


	public Zug() {
	}

	public Zug(@NotNull Date startZeit, int sitzPlaetze, int fahrradStellplaetze, int rollStuhlPlaetze, @NotNull Bahnhof start, @NotNull Bahnhof ende) {
		this.startZeit = startZeit;
		this.sitzPlaetze = sitzPlaetze;
		this.fahrradStellplaetze = fahrradStellplaetze;
		this.rollStuhlPlaetze = rollStuhlPlaetze;
		this.start = start;
		this.ende = ende;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Date getStartZeit() {
		return startZeit;
	}

	public void setStartZeit(Date startZeit) {
		this.startZeit = startZeit;
	}

	public int getSitzPlaetze() {
		return sitzPlaetze;
	}

	public void setSitzPlaetze(int sitzPlaetze) {
		this.sitzPlaetze = sitzPlaetze;
	}

	public int getFahrradStellplaetze() {
		return fahrradStellplaetze;
	}

	public void setFahrradStellplaetze(int fahrradStellplaetze) {
		this.fahrradStellplaetze = fahrradStellplaetze;
	}

	public int getRollStuhlPlaetze() {
		return rollStuhlPlaetze;
	}

	public void setRollStuhlPlaetze(int rollStuhlPlaetze) {
		this.rollStuhlPlaetze = rollStuhlPlaetze;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}


}
