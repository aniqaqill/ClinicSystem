import java.util.ArrayList;
import java.util.Scanner;

public class ClinicApp {
	public static void main(String[] args) {

		ArrayList<Medication> medication = new ArrayList<Medication>();
		ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		Scanner input = new Scanner(System.in);
		int choice, medicNum;

		Medication m1 = new Medication("Aspirin tablet", "Reduce blood clotting\t", 20.00, "1");
		Medication m2 = new Medication("Allergy shots", "\tImprove immune system\t", 150.50, "2");
		Medication m3 = new Medication("Antihistamines", "Reduce blood clotting\t", 40.80, "3");
		Medication m4 = new Medication("Nasal sprays", "\tEase nasal congestion\t", 70.00, "4");
		Medication m5 = new Medication("Calamine lotion", "Relieve itchy skin\t", 25.50, "5");

		Doctor dr1 = new Doctor("Dr. Arif Izzuddin, M.D, M.S.", 30.0, "Dermatologists", "A-115", "N");
		Doctor dr2 = new Doctor("Dr. Hafiz Hakim, M.D", 25.00, "Infectious diseases", "C-311", "M");
		Doctor dr3 = new Doctor("Dr. Nuha Faqihah, M.D, M.S", 40.00, "Paediatrician", "B-214", "A");

		medication.add(m1);
		medication.add(m2);
		medication.add(m3);
		medication.add(m4);
		medication.add(m5);

		doctorList.add(dr1);
		doctorList.add(dr2);
		doctorList.add(dr3);

		String name;
		String hp;
		String add;
		String date = "";
		double time = 0.0;
		int age = 0;

		System.out.println("******** KLINIK MESRA ********");
		System.out.print("Enter patient name: ");
		name = input.nextLine();

		System.out.print("Enter patient address : ");
		add = input.nextLine();

		System.out.print("Enter patient phone number: ");
		hp = input.nextLine();

		System.out.print("Enter patient phone age: ");
		age = input.nextInt();

		Consultation app = new Consultation(date, time);

		Patient pt = new Patient(name, add, hp, age, app);

		do {
			System.out.println("\n******** KLINIK MESRA ********");
			System.out.println("1.  Consultation Detail");
			System.out.println("2.  Medication Details");
			System.out.println("3.  Payment Detail");
			System.out.println("4.  Exit");

			System.out.print("\nPlease enter your choice (1-4) : ");
			choice = input.nextInt();
			System.out.println();

			switch (choice) {
				case 1:
					System.out.println("******** Consultation Detail ********");
					System.out.println();
					System.out.print("Enter date (dd/mm/yy): ");
					date = input.next();
					System.out.print("Enter time (24hour format):");
					time = input.nextDouble();
					System.out.println();

					app = new Consultation(date, time);
					pt = new Patient(name, add, hp, age, app);

					int pick;
					System.out.println("Doctor Detail:");
					System.out.println();

					if (time > Availability.M.getStartTime() && time <= Availability.M.getEndTime()) {

						for (Doctor dr : doctorList) {
							if (dr.time.equals(Availability.M)) {
								pt.meetDr(dr);
								pt.consultSet(app);
								dr.displayDoctor();
							}
						}
					} else if (time > Availability.A.getStartTime() && time <= Availability.A.getEndTime()) {

						for (Doctor dr : doctorList) {
							if (dr.time.equals(Availability.A)) {
								pt.meetDr(dr);
								pt.consultSet(app);
								dr.displayDoctor();
							}
						}

					} else if (time > Availability.N.getStartTime() && time <= Availability.N.getEndTime()) {

						for (Doctor dr : doctorList) {
							if (dr.time.equals(Availability.N)) {
								pt.meetDr(dr);
								pt.consultSet(app);
								dr.displayDoctor();
							}
						}
					}
					break;

				case 2:
					System.out.println("LIST OF MEDICATION ");
					System.out.println("Medication\t\t\t\tDescription\t\t\tPrice(RM)");

					for (int i = 0; i < medication.size(); i++) {
						Medication m = medication.get(i);
						System.out.println((i + 1) + "." + m.getMedic() + "\t\t" + m.getDescription() + "\t\t" + m.getPrice());
					}

					String buy;
					int qty = 0;
					System.out.print("\n How many type of medicine?: ");
					medicNum = input.nextInt();

					for (int i = 0; i < medicNum; i++) {

						System.out.println("");
						System.out.print("Enter medicine id: ");
						buy = input.next();
						System.out.print("Enter quantity: ");
						qty = input.nextInt();

						for (Medication medic : medication) {

							if (medic.getID().equals(buy)) {
								pt.buyMedication(medic);
								medic.setQty(qty);
								System.out.println("Item : " + medic.getMedic() + "\t\t Quantity : " + qty);
							}
						}
					}

					break;

				case 3:
					System.out.println("<<<< PAYMENT DETAIL >>>>\n");
					pt.display();
					break;

				default:
					System.out.println("Thank you for using our system");
					break;
			}

		} while (choice != 4);
	}
}
