
package employerinformation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployerInformation {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner scan = new Scanner(System.in);

		EmployerDao employerDao = new EmployerDaoImpl();

		System.out.println("Enter 1 to insert a Record: ");
		System.out.println("Enter 2 to update a Record");
		System.out.println("Enter 3 to Delete a Single Record: ");
		System.out.println("Enter 4 to Delete all Record: ");
		System.out.println("Enter 5 to to display a Single Record: ");
		System.out.println("Enter 6 to display all Records: ");

		int option = Integer.parseInt(scan.nextLine());

		switch (option) {
		case 1:
			List<Employer> empInsert = new ArrayList<Employer>();
			System.out.print("How many Employer Records do you want to insert: ");
			int numberOfEmployer = Integer.parseInt(scan.nextLine());

			for (int i = 0; i < numberOfEmployer; i++) {
				empInsert.add(new Employer());
				System.out.println("Please enter the ID Number");
				empInsert.get(i).setId(Integer.parseInt(scan.nextLine()));
				System.out.println("Please enter the first name");
				empInsert.get(i).setName(scan.nextLine());
				System.out.println("Please enter the Last name");
				empInsert.get(i).setLname(scan.nextLine());
				System.out.println("Please enter the Gender");
				empInsert.get(i).setGender(scan.nextLine());
				System.out.println("Please enter the Address");
				empInsert.get(i).setAddress(scan.nextLine());
				System.out.println("Please enter the City");
				empInsert.get(i).setCity(scan.nextLine());
				System.out.println("Please enter the Country");
				empInsert.get(i).setCountry(scan.nextLine());
				System.out.println("===========================================");
			}

			// Insert Records
			int resultInsert = employerDao.insertEmployer(empInsert);
			System.out.println(resultInsert + " Records have been inserted main");

			break;

		case 2:
			Employer empUpdate = new Employer();

			System.out.println("Please enter the ID Number for the Employer which needs to be update");
			empUpdate.setId(Integer.parseInt(scan.nextLine()));
			System.out.println("Please enter the first name");
			empUpdate.setName(scan.nextLine());
			System.out.println("Please enter the Last name");
			empUpdate.setLname(scan.nextLine());
			System.out.println("Please enter the Gender");
			empUpdate.setGender(scan.nextLine());
			System.out.println("Please enter the Address");
			empUpdate.setAddress(scan.nextLine());
			System.out.println("Please enter the City");
			empUpdate.setCity(scan.nextLine());
			System.out.println("Please enter the Country");
			empUpdate.setCountry(scan.nextLine());

			System.out.println("===========================================");
			// update a Record
			boolean resultUpdate = employerDao.updateRecord(empUpdate);

			if (resultUpdate == true) {
				System.out.print("The record has been updated");

			} else {

				System.out.print("No matching records found to be updated");

			}

			break;

		case 3:
			System.out.println("Please enter the ID of the Employer to delete a record");
			int id = Integer.parseInt(scan.nextLine());
			// Delete a Record
			boolean resultDelete = employerDao.deleteRecord(id);

			if (resultDelete) {
				System.out.println("Employer record with id " + id + " has been deleted");

			} else {
				System.out.println("The Id " + id + " is not assocaited with any record");
			}

			break;

		case 4:
			boolean resultDeleteAll = false;
			System.out.print("Warning this will delete all the records from the"
					+ " Database.Enter Yes to Continue and No to Abort ");
			String result = scan.nextLine();

			if (result.equalsIgnoreCase("Yes"))
				// calling method to delete All Records
				resultDeleteAll = employerDao.deleteAllRecords();
			if (resultDeleteAll) {
				System.out.println("All rows have been deleted form the table");

			} else {
				System.out.println("Table is empty");
			}

			break;

		case 5:

			System.out.print("PLease Enter an ID to display a record ");
			int idDisplayRecord = Integer.parseInt(scan.nextLine());
			// Display record
			Employer DisplayRecord = employerDao.displaySingleRecord(idDisplayRecord);
			System.out.printf("%-11s%-11s%-11s%-11s%-20s%-11s%s\n", "ID", "FirstName", "LastName", "Gender", "Address",
					"City", "Country");
			if (DisplayRecord == null) {
				System.out.println("No maching records were found");
			} else {

				System.out.printf("%-11s%-11s%-11s%-11s%-20s%-11s%s\n", DisplayRecord.getId(), DisplayRecord.getName(),
						DisplayRecord.getLname(), DisplayRecord.getGender(), DisplayRecord.getAddress(),
						DisplayRecord.getCity(), DisplayRecord.getCountry());

				/*
				 * System.out.printf("%-11s%-11s%-11s%-11s%-11s%-11s%s\n",
				 * DisplayRecord.getId(), DisplayRecord.getName(), DisplayRecord.getLname(),
				 * DisplayRecord.getLname(), DisplayRecord.getLname(), DisplayRecord.getCity(),
				 * DisplayRecord.getCountry());
				 */
			}
			break;

		case 6:

			// Display all available records
			List<Employer> displayAllRecordsResult = employerDao.displayAllRecords();

			System.out.printf("%-11s%-11s%-11s%-11s%-20s%-11s%s\n", "ID", "FirstName", "LastName", "Gender", "Address",
					"City", "Country");
			if (displayAllRecordsResult == null) {
				System.out.println("Table is Empty, No records found");
			} else {

				for (Employer e : displayAllRecordsResult) {
					System.out.printf("%-11s%-11s%-11s%-11s%-20s%-11s%s\n", e.getId(), e.getName(), e.getLname(),
							e.getGender(), e.getAddress(), e.getCity(), e.getCountry());
				}
			}

			break;

		}

		// close Scanner
		scan.close();
		System.out.print("Session Ended");

	}
}