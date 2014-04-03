package uk.ac.port.app;

import uk.ac.port.managers.ProjectManager;
import uk.ac.port.models.Project;

public class App {
	public static void main(String[] args) {
		for(Project p : ProjectManager.all()) {
			System.out.println(p);
		}
	}
}
