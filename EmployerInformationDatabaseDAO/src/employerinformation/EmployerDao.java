package employerinformation;

import java.util.List;

public interface EmployerDao {

	public int insertEmployer(List<Employer> emp);

	public boolean updateRecord(Employer empUpdate);

	public boolean deleteRecord(int id);

	public boolean deleteAllRecords();

	public Employer displaySingleRecord(int id);

	public List<Employer> displayAllRecords();

}
