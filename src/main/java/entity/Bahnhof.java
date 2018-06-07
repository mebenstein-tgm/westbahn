package entity;

import Validation.BahnhofNameConstraint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Bahnhof implements Comparable<Bahnhof> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@NotNull
	@BahnhofNameConstraint
	private String name;

	@NotNull
	private int absPreisEntfernung;

	@NotNull
	private int absKmEntfernung;

	@NotNull
	private int absZeitEntfernung;

	@NotNull
	private boolean kopfBahnhof;

	public Bahnhof() {
	}

	public Bahnhof(@NotNull String name, @NotNull int absPreisEntfernung, @NotNull int absKmEntfernung, @NotNull int absZeitEntfernung, @NotNull boolean kopfBahnhof) {
		this.name = name;
		this.absPreisEntfernung = absPreisEntfernung;
		this.absKmEntfernung = absKmEntfernung;
		this.absZeitEntfernung = absZeitEntfernung;
		this.kopfBahnhof = kopfBahnhof;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAbsPreisEntfernung() {
		return absPreisEntfernung;
	}

	public void setAbsPreisEntfernung(int absPreisEntfernung) {
		this.absPreisEntfernung = absPreisEntfernung;
	}

	public int getAbsKmEntfernung() {
		return absKmEntfernung;
	}

	public void setAbsKmEntfernung(int absKmEntfernung) {
		this.absKmEntfernung = absKmEntfernung;
	}

	public int getAbsZeitEntfernung() {
		return absZeitEntfernung;
	}

	public void setAbsZeitEntfernung(int absZeitEntfernung) {
		this.absZeitEntfernung = absZeitEntfernung;
	}

	public boolean isKopfBahnhof() {
		return kopfBahnhof;
	}

	public void setKopfBahnhof(boolean kopfBahnhof) {
		this.kopfBahnhof = kopfBahnhof;
	}

	public int compareTo(Bahnhof o) {
		return o.getName().compareTo(getName());
	}
}
