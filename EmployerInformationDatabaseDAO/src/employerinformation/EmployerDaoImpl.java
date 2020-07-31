package employerinformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerDaoImpl implements EmployerDao {

	public int insertEmployer(List<Employer> empInsert) {

		Connection con = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		String sqlInsert;
		int result = 0;

		try {
			sqlInsert = "INSERT INTO Employer(EmployerID,FirstName,LastName,Gender,Address,City,Country) VALUES (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sqlInsert);

			for (Employer e : empInsert) {
				pstmt.setInt(1, e.getId());
				pstmt.setString(2, e.getName());
				pstmt.setString(3, e.getLname());
				pstmt.setString(4, e.getGender());
				pstmt.setString(5, e.getAddress());
				pstmt.setString(6, e.getCity());
				pstmt.setString(7, e.getCountry());

				pstmt.addBatch();
			}
			int rowsAffectedIns[] = pstmt.executeBatch();
			result = rowsAffectedIns.length;

		} catch (SQLException e) {

			System.out.println("Error Code=" + e.getErrorCode());
			System.out.println("SQL State=" + e.getSQLState());
			System.out.println("Message =" + e.getMessage());

		}

		finally {
			DatabaseConnection.closeConnection(pstmt, con);
		}
		return result;
	}

	public boolean updateRecord(Employer empUpdate) {

		Connection con = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		String sqlInsert;
		int recordUpdateresult = 0;
		try {

			sqlInsert = "UPDATE Employer SET FirstName=?,LastName=?,Gender=?,Address=?,City=?,Country=? WHERE EmployerID=?";
			pstmt = con.prepareStatement(sqlInsert);

			pstmt.setString(1, empUpdate.getName());
			pstmt.setString(2, empUpdate.getLname());
			pstmt.setString(3, empUpdate.getGender());
			pstmt.setString(4, empUpdate.getAddress());
			pstmt.setString(5, empUpdate.getCity());
			pstmt.setString(6, empUpdate.getCountry());
			pstmt.setInt(7, empUpdate.getId());

			recordUpdateresult = pstmt.executeUpdate();

		} catch (SQLException e) {

			System.out.println("Error Code=" + e.getErrorCode());
			System.out.println("SQL State=" + e.getSQLState());
			System.out.println("Message =" + e.getMessage());
		}

		finally {
			DatabaseConnection.closeConnection(pstmt, con);
		}
		if (recordUpdateresult > 0)
			return true;
		return false;
	}

	public boolean deleteRecord(int id) {

		Connection con = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		String sqlInsert;
		boolean resultDelete = false;

		try {
			sqlInsert = "DELETE FROM Employer WHERE EmployerID=?";
			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setInt(1, id);
			int deletedRecord = pstmt.executeUpdate();

			if (deletedRecord > 0) {
				resultDelete = true;

			} else {
				resultDelete = false;
			}

		} catch (SQLException e) {

			System.out.print("Error Code=" + e.getErrorCode());
			System.out.println("SQL State=" + e.getSQLState());
			System.out.println("Message =" + e.getMessage());

		}

		finally {
			DatabaseConnection.closeConnection(pstmt, con);
		}
		return resultDelete;
	}

	public boolean deleteAllRecords() {

		Connection con = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		String sqlInsert;
		boolean resultDeleteAllRecord = false;

		try {

			sqlInsert = "DELETE FROM Employer";
			pstmt = con.prepareStatement(sqlInsert);
			int deletedRecords = pstmt.executeUpdate();
			if (deletedRecords > 0) {
				resultDeleteAllRecord = true;

			} else {
				resultDeleteAllRecord = false;
			}

		} catch (SQLException e) {

			System.out.println("Error Code=" + e.getErrorCode());
			System.out.println("SQL State=" + e.getSQLState());
			System.out.println("Message =" + e.getMessage());
		} finally {
			DatabaseConnection.closeConnection(pstmt, con);
		}
		return resultDeleteAllRecord;
	}

	public Employer displaySingleRecord(int id) {

		Connection con = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sqlInsert;
		Employer emp = new Employer();

		try {
			sqlInsert = "SELECT * FROM  Employer WHERE EmployerID=? ";
			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			System.out.println();
			if (rs.next() == false) {
				/*
				 * Still researching this topic, That is returning null in this case a good idea
				 * or not for now I will just return null
				 */
				emp = null;
			} else {

				do {

					emp.setId(rs.getInt(1));
					emp.setName(rs.getString(2));
					emp.setLname(rs.getString(3));
					emp.setGender(rs.getString(4));
					emp.setAddress(rs.getString(5));
					emp.setCity(rs.getString(6));
					emp.setCountry(rs.getString(7));

				} while (rs.next());
			}
		} catch (SQLException e) {

			System.out.print("Error Code=" + e.getErrorCode());
			System.out.println("SQL State=" + e.getSQLState());
			System.out.println("Message =" + e.getMessage());

		}

		finally {
			DatabaseConnection.closeConnection(pstmt, con);
		}
		return emp;

	}

	public List<Employer> displayAllRecords() {

		Connection con = DatabaseConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sqlInsert;
		List<Employer> empDisplayResult = new ArrayList<Employer>();

		try {
			sqlInsert = "SELECT * FROM  Employer";
			pstmt = con.prepareStatement(sqlInsert);
			rs = pstmt.executeQuery();
			System.out.println();

			if (rs.next() == false) {

				/*
				 * Still researching this topic, That is returning null in this case a good idea
				 * or not for now I will just return null
				 */
				empDisplayResult = null;
			}

			else {
				do {
					Employer emp = new Employer();

					emp.setId(rs.getInt(1));
					emp.setName(rs.getString(2));
					emp.setLname(rs.getString(3));
					emp.setGender(rs.getString(4));
					emp.setAddress(rs.getString(5));
					emp.setCity(rs.getString(6));
					emp.setCountry(rs.getString(7));

					empDisplayResult.add(emp);

				} while (rs.next());
			}

		} catch (SQLException e) {

			System.out.print("Error Code=" + e.getErrorCode());
			System.out.println("SQL State=" + e.getSQLState());
			System.out.println("Message =" + e.getMessage());

		}

		finally {
			DatabaseConnection.closeConnection(pstmt, con);
		}
		return empDisplayResult;

	}

}
