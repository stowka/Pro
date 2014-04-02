package uk.ac.port.models;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private int id;
	private String description;
	private String creationTime, modificationTime;
	private List<Molecule> molecules;
	
	public Project() {
		
	}
	
	public Project(int id) {
		this.id = id;
	}
	
	public Project(String description, String creationTime, String modificationTime, ArrayList<Molecule> molecules) {
		this.description = description;
		this.creationTime = creationTime;
		this.modificationTime = modificationTime;
		this.molecules = molecules;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}

	public List<Molecule> getMolecules() {
		return molecules;
	}

	public void setMolecules(ArrayList<Molecule> molecules) {
		this.molecules = molecules;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", description=" + description
				+ ", creationTime=" + creationTime + ", modificationTime="
				+ modificationTime + ", molecules=" + molecules.size() + "]";
	}
	
	
}