package model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Element {

	private UUID id;
	private Sense sense;

	private Set<Relation> relations = new HashSet<>();

	public Element(Sense sense) {
		this.id = UUID.randomUUID();
		this.sense = sense;
	}

	public Element(UUID id, Sense sense) {
		this.id = id;
		this.sense = sense;
	}

	public Set<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

	// public void addRelation(Relation relation) {
	// this.relations.add(relation);
	// }
	//
	// public void addRelation(Sense sense, Element element) {
	// this.relations.add(new Relation(sense, this, element));
	// }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Sense getSense() {
		return sense;
	}

	public void setSense(Sense sense) {
		this.sense = sense;
	}

	@Override
	public String toString() {
		return sense.getName();
	}
}
