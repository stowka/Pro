package uk.ac.port.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uk.ac.port.lib.SDBH;
import uk.ac.port.models.Molecule;
import uk.ac.port.models.Project;

public class ProjectManager {
	private static final Connection CONN = SDBH.getConnection();

	public static Project select(int id) {
		String name = null;
		String description = null;
		String creationTime = null;
		String modificationTime = null;

		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			pstm = CONN.prepareStatement("SELECT * FROM project WHERE id = ?");
			pstm.setInt(1, id);
			rset = pstm.executeQuery();
			rset.next();
			name = rset.getString("name");
			description = rset.getString("description");
			creationTime = rset.getString("creationTime");
			modificationTime = rset.getString("modificationTime");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Project p = new Project(id, name, description, creationTime,
				modificationTime);
		addMolecules(p);
		return p;
	}
	
	public static List<Project> search(String pattern) {
		int id = 0;
		String name = null;
		String description = null;
		String creationTime = null;
		String modificationTime = null;

		PreparedStatement pstm = null;
		ResultSet rset = null;

		List<Project> projects = new ArrayList<Project>();

		try {
			pstm = CONN.prepareStatement("SELECT * FROM project WHERE name LIKE ?");
			pstm.setString(1, "'%" + pattern + "%'");
			rset = pstm.executeQuery();
			while (rset.next()) {
				id = rset.getInt("id");
				name = rset.getString("name");
				description = rset.getString("description");
				creationTime = rset.getString("creationTime");
				modificationTime = rset.getString("modificationTime");
				projects.add(new Project(id, name, description, creationTime,
						modificationTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Project p : projects)
			addMolecules(p);
		return projects;
	}

	public static boolean update(Project p) {
		PreparedStatement pstm = null;
		boolean ok = true;
		
		try {
			pstm = CONN.prepareStatement("UPDATE project SET name = ?, description = ? WHERE id = ?");
			pstm.setString(1, p.getName());
			pstm.setString(2, p.getDescription());
			pstm.setInt(3, p.getId());
			pstm.executeUpdate();
			System.out.println("Row updated");
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				ok = false;
			}
		}	
		return ok;
	}

	public static boolean delete(Project p) {
		PreparedStatement pstm = null;
		boolean ok = true;
		
		try {
			pstm = CONN.prepareStatement("DELETE FROM project WHERE id = ?");
			pstm.setInt(1, p.getId());
			pstm.executeUpdate();
			System.out.println("Row deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				ok = false;
			}
		}
		return ok;
	}

	public static int insert(String name, String description) {
		PreparedStatement pstm = null;
		try {
			pstm = CONN
					.prepareStatement("INSERT INTO project (name, description) VALUES (?, ?)");
			pstm.setString(1, name);
			pstm.setString(2, description);
			pstm.executeUpdate();
			System.out.println("New row in `project`");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lastInsertId();
	}
	
	public static int lastInsertId() {
		int id = 0;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = CONN.createStatement();
			rset = stmt.executeQuery("SELECT TOP 1 id FROM project ORDER BY id DESC");
			if (rset.next())
				id = rset.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public static List<Project> all() {
		int id = 0;
		String name = null;
		String description = null;
		String creationTime = null;
		String modificationTime = null;

		PreparedStatement pstm = null;
		ResultSet rset = null;

		List<Project> projects = new ArrayList<Project>();

		try {
			pstm = CONN.prepareStatement("SELECT * FROM project");
			rset = pstm.executeQuery();
			while (rset.next()) {
				id = rset.getInt("id");
				name = rset.getString("name");
				description = rset.getString("description");
				creationTime = rset.getString("creationTime");
				modificationTime = rset.getString("modificationTime");
				projects.add(new Project(id, name, description, creationTime,
						modificationTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for (Project p : projects)
			addMolecules(p);
		return projects;
	}

	public static void addMolecules(Project p) {
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			pstm = CONN
					.prepareStatement("SELECT * FROM molecule WHERE projectId = ?");
			pstm.setInt(1, p.getId());
			rset = pstm.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String name = rset.getString("name");
				String smiles = rset.getString("smiles");
				p.addMolecule(new Molecule(id, name, smiles));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean save() {
		Statement stmt = null;
		boolean ok = true;
		
		try {
			stmt = CONN.createStatement();
			stmt.executeUpdate("SHUTDOWN");
		} catch (SQLException e) {
			e.printStackTrace();
			ok = false;
		} finally {
			try {
				stmt.close();
				CONN.close();
			} catch (SQLException e) {
				e.printStackTrace();
				ok = false;
			}
		}		
		return ok;
	}
}
