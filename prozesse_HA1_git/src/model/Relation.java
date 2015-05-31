package model;

import java.util.UUID;


public class Relation {

	private UUID idRelation;
	public Element startElement;
	public Element endElement;

	public Sense sense;

	public Relation(Sense sense, Element startElement, Element endElement) {
		this.sense = sense;
		this.startElement = startElement;
		this.endElement = endElement;
		
		startElement.getRelations().add(this);
		endElement.getRelations().add(this);
	}

	public Sense getSense() {
		return sense;
	}

	public void setSense(Sense sense) {
		this.sense = sense;
	}

	
	public UUID getIdRelation() {
		return idRelation;
	}

	public void setIdRelation(UUID idRelation) {
		this.idRelation = idRelation;
	}

	public Element getStartElement() {
		return startElement;
	}

	public void setStartElement(Element startElement) {
		this.startElement = startElement;
	}

	public Element getEndElement() {
		return endElement;
	}

	public void setEndElement(Element endElement) {
		this.endElement = endElement;
	}
	
	@Override
	public String toString() {
		return startElement.getSense().getName() + " <- " + sense.getName() + " -> " + endElement.getSense().getName();
	}
}