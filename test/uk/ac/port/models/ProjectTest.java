package uk.ac.port.models;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.port.managers.ProjectManager;

public class ProjectTest {
	
	@Test
	public void insertTest() {
		assertFalse(0 == ProjectManager.insert("TEST", "DESCRIPTION"));
	}
	
	@Test
	public void searchTest() {
		for (Project p : ProjectManager.search("TEST"))
			assertNotNull(p);
	}
	
	@Test
	public void deleteTest() {
		for (Project p : ProjectManager.search("TEST"))
			assertTrue(ProjectManager.delete(p));
	}

	@Test
	public void selectTest() {
		assertEquals(ProjectManager.select(0).getId(), 0);
	}
	
	@Test
	public void allTest() {
		for (Project p : ProjectManager.all())
			assertNotNull(p);
	}
}
