package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Menge {

	private UUID idMenge;
	private Map<UUID, Element> elemente = new HashMap<>();

	public Menge(UUID idMenge, Map<UUID, Element> elemente) {
		this.idMenge = idMenge;
		this.elemente = elemente;

	}

	public UUID getIdMenge() {
		return idMenge;
	}

	public void setIdMenge(UUID idMenge) {
		this.idMenge = idMenge;
	}

	public Map<UUID, Element> getElemente() {
		return elemente;
	}

	public void setElemente(Map<UUID, Element> elemente) {
		this.elemente = elemente;
	}

}
