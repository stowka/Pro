package uk.ac.port.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
	private int id;
	private String name;
	private String description;
	private String creationTime, modificationTime;
	private List<Molecule> molecules;

	public Project(int id, String name, String description, String creationTime,
			String modificationTime) {
		this.id = id;
		this.description = description;
		this.creationTime = creationTime
				.substring(0, creationTime.length() - 3);
		this.modificationTime = modificationTime.substring(0,
				modificationTime.length() - 3);
		this.molecules = new ArrayList<Molecule>();
	}

	public static String format(String dateStr, String outputFormatStr) {
		String inputFormatStr = "yyyy-MM-dd HH:mm:ss.SSS";
		Date testDate = null;
		SimpleDateFormat inputSDF = new SimpleDateFormat(inputFormatStr);
		SimpleDateFormat outputSDF = new SimpleDateFormat(outputFormatStr);
		inputSDF.setLenient(false);
		outputSDF.setLenient(false);
		try {
			testDate = inputSDF.parse(dateStr);
			return outputSDF.format(testDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getModificationTime() {
		return modificationTime;
	}

	public List<Molecule> getMolecules() {
		return molecules;
	}

	public void addMolecule(Molecule molecule) {
		this.molecules.add(molecule);
	}

	public void removeMolecule(int index) {
		this.molecules.remove(index);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", description=" + description
				+ ", creationTime=" + format(creationTime, "MM/dd/yy - hh:mm") + ", modificationTime="
				+ format(modificationTime, "MM/dd/yy - HH:mm") + ", molecules=" + molecules.size() + "]";
	}

}