package model;

import java.util.HashSet;
import java.util.Set;

public class Relation {

	public Element startElement;
	public Element endElement;

	public Sense sense;

	private Set<Relation> startElements = new HashSet<>();
	private Set<Relation> endElements = new HashSet<>();

	public Relation(Sense sense, Element startElement, Element endElement) {
		this.sense = sense;
		this.startElement = startElement;
		this.endElement = endElement;

	}
}