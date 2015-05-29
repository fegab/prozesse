package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Menge {

	private UUID idMenge;
	private Map<UUID, Element> elemente = new HashMap<>();
	private Sense sense;
	
	
	public Menge(Sense sense) {
		this.idMenge = UUID.randomUUID();
		this.elemente = new HashMap<UUID, Element>();
		this.setSense(sense);
	}
	
	public Menge(Map<UUID, Element> elemente, Sense sense) {
		this.idMenge = UUID.randomUUID();
		this.elemente = elemente;
		this.setSense(sense);
	}

	public Menge(UUID idMenge, Map<UUID, Element> elemente, Sense sense) {
		this.idMenge = idMenge;
		this.elemente = elemente;
		this.setSense(sense);

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
	
	public void addElement(Element element) {
		this.elemente.put(element.getId(), element);
	}
	
	public void setElemente(Map<UUID, Element> elemente) {
		this.elemente = elemente;
	}

	public Sense getSense() {
		return sense;
	}

	public void setSense(Sense sense) {
		this.sense = sense;
	}

}
