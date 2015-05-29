package model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Element implements IElement {

	private UUID id;
	public final String name;
	public Sense sense;

	private Set<Relation> relations = new HashSet<>();

	public Element(UUID id, String name, Sense sense) {
		this.id = id;
		this.name = name;
		this.sense = sense;

	}

	@Override
	public UUID getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(UUID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getname() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setname(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sense getSense() {
		// TODO Auto-generated method stub
		return sense;
	}

	@Override
	public void setSense() {
		// TODO Auto-generated method stub

	}

}
