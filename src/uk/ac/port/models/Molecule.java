package uk.ac.port.models;

public class Molecule {
	private int id;
	private String name, smiles;
	
	public Molecule() {
		
	}
	
	public Molecule(int id) {
		this.id = id;
	}
	
	public Molecule(String name, String smiles) {
		this.name = name;
		this.smiles = smiles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmiles() {
		return smiles;
	}

	public void setSmiles(String smiles) {
		this.smiles = smiles;
	}

	@Override
	public String toString() {
		return "Molecule [id=" + id + ", name=" + name + ", smiles=" + smiles
				+ "]";
	}
}
