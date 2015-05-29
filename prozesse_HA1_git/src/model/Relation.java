package model;

public class Relation {

	public Element startElement;
	public Element endElement;

	public Sense sense;

	public Relation(Sense sense, Element startElement, Element endElement) {
		this.sense = sense;
		this.startElement = startElement;
		this.endElement = endElement;

//		startElement.outgoingRelation.add(this);
//		endElement.incomingRelation.add(this);

	}
}