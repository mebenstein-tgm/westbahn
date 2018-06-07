package entity;

import javax.persistence.Embeddable;

@Embeddable
public enum StatusInfo {
	DELAYED,
	CANCELED,
	ONTIME;

}
