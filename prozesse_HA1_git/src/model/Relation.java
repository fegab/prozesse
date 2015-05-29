package model;


public class Relation {

	public Element startElement;
	public Element endElement;

	public Sense sense;

	public Relation(Sense sense, Element startElement, Element endElement) {
		this.sense = sense;
		this.startElement = startElement;
		this.endElement = endElement;

	}

	public Sense getSense() {
		return sense;
	}

	public void setSense(Sense sense) {
		this.sense = sense;
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
}