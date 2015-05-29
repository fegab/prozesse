package model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//Elemente haben prinzipiell immer: id, name, sense

public interface IElement {

	Set<Relation> Relation = new HashSet<>();

	// id
	public UUID getId();

	public void setId(UUID id);

	// name
	public String getname();

	public void setname(String name);

	// sense
	public Sense getSense();

	public void setSense();

}
