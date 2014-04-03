package uk.ac.port.models;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.port.managers.ProjectManager;

public class ProjectTest {

	@Test
	public void selectTest() {
		assertEquals(ProjectManager.select(0).getId(), 0);
	}

}
