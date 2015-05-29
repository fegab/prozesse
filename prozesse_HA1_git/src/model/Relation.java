package model;

import java.util.HashSet;
import java.util.Set;

public class Relation {

	public Element startElement;
	public Element endElement;

	public Sense sense;

	public Relation(Sense sense, Element startElement, Element endElement) {
		this.sense = sense;
		this.startElement = startElement;
		this.endElement = endElement;

	}
}