import java.util.*;
import java.io.*;

public class Driver {

	public static void ADVISE(Course[] COMP, Course[] CSCS, Course[] ELEC,
			Subject[][] standard, Subject[][] Student)
			throws FileNotFoundException {
		// translation from Course->Subject type

		// COMP_Courses (43 credits)
		Subject[] Comp = new Subject[COMP.length];
		courses[] ccomp = new courses[COMP.length];

		for (int i = 0; i < COMP.length; i++) {
			ccomp[i] = new courses(COMP[i].Name, COMP[i].Pre_req_1,
					COMP[i].Pre_req_2, false);
		}

		for (int i = 0; i < Comp.length; i++) {
			String n = COMP[i].Name;
			Comp[i] = new Subject(n, false);
		}
		// Specialization Courses (9 credits)
		Subject[] cscs = new Subject[CSCS.length];
		courses[] ccscs = new courses[CSCS.length];

		for (int i = 0; i < CSCS.length; i++) {
			ccscs[i] = new courses(CSCS[i].Name, CSCS[i].Pre_req_1,
					CSCS[i].Pre_req_2, false);
		}

		for (int i = 0; i < cscs.length; i++) {
			String n = CSCS[i].Name;
			cscs[i] = new Subject(n, false);
		}
		// ELECTIVES (12 credits)
		Subject[] elec = new Subject[ELEC.length];
		for (int i = 0; i < elec.length; i++) {
			String n = ELEC[i].Name;
			elec[i] = new Subject(n, false);
		}

		// checking for failed and passed courses in comp category
		int count_comp = 0;
		int done_credits_comp = 0;
		for (int i = 0; i < Student.length; i++) {
			for (int j = 0; j < Student[i].length; j++) {
				for (int k = 0; k < Comp.length; k++) {

					if (Comp[k].name.equals(Student[i][j].name)
							&& (Student[i][j].pass)) {
						Comp[k].pass = true;
						ccomp[k].pass = true;
						count_comp++;
						if (Comp[k].name.equals("COMP401")) {
							done_credits_comp += 1;
						} else {
							done_credits_comp += 3;
						}
					}
				}
			}
		}

		System.out.println("COMP: Total Courses Done:" + count_comp);
		System.out.println("Total Credits done: " + done_credits_comp);

		// checking for failed and passed courses in cscs category

		int count_cscs = 0;
		int done_credits_cscs = 0;
		for (int i = 0; i < Student.length; i++) {
			for (int j = 0; j < Student[i].length; j++) {
				for (int k = 0; k < cscs.length; k++) {
					if ((cscs[k].name.equals(Student[i][j].name))
							&& (Student[i][j].pass)) {
						cscs[k].pass = true;
						ccscs[k].pass = true;
						count_cscs++;
						done_credits_cscs += 3;
					}
				}
			}
		}

		System.out.println("CSCS: Total Courses Done:" + count_cscs);
		System.out.println("Total Credits done: " + done_credits_cscs);

		// checking for failed and passed courses in electives category

		int count_elec = 0;
		int done_credits_elec = 0;
		for (int i = 0; i < Student.length; i++) {
			for (int j = 0; j < Student[i].length; j++) {
				for (int k = 0; k < elec.length; k++) {
					if (elec[k].name.equals(Student[i][j].name)
							&& (Student[i][j].pass)) {
						elec[k].pass = true;
						count_elec++;
						done_credits_elec += 3;
					}
				}
			}
		}

		System.out.println("ELEC: Total Courses Done:" + count_elec);
		System.out.println("Total Credits done: " + done_credits_elec);

		System.out.println("\n\nDEGREE_PLAN");

		int rem_comp = 43 - done_credits_comp;
		int rem_cscs = 9 - done_credits_cscs;
		int rem_elec = 12 - done_credits_elec;

		System.out.println("COMP_Credits_Left: " + rem_comp);

		// courses[] Rcomp = new courses[rem_comp];

		System.out.println("CSCS_Credits_Left: " + rem_cscs);

		// courses[] Rcscs = new courses[rem_cscs];

		if (done_credits_elec < 12) {
			System.out.println("ELEC_Credits_Left: " + rem_elec);

			// courses []Relec=new courses[rem_elec];

		} else {
			System.out.println("ELEC: " + 0);
		}

		int total_credits_left = 0;
		if (done_credits_elec < 12) {
			total_credits_left = rem_comp + rem_cscs + rem_elec;
		} else {
			total_credits_left = rem_comp + rem_cscs;
		}
		System.out.println("Total Credits Left: " + total_credits_left);

		/*
		 * for (int i = 0; i < ccomp.length; i++) { ccomp[i].printCourses(); }
		 * 
		 * for (int i = 0; i < ccscs.length; i++) { ccscs[i].printCourses(); }
		 */

		// calculation of the future courses
		if (total_credits_left > 0) {

			if (rem_comp > 0) {

				courses[] Rcomp = new courses[15 - count_comp];

				int counter = 0;
				for (int i = 0; i < ccomp.length; i++) {
					if (!ccomp[i].pass) {
						Rcomp[counter] = new courses(ccomp[i].name,
								ccomp[i].Pre_req_1, ccomp[i].Pre_req_2, false);
						counter++;
					}
				}

				System.out
						.println("Remaining courses in comp: " + Rcomp.length);

				int req_sem = Rcomp.length / 3;
				// int req_sem=rem_comp/4;
				// req_sem++;
				System.out.println("Estimated Semesters Required for COMP: "
						+ req_sem);

				String planComp[][] = new String[req_sem][3];

				int ptr = 0;
				for (int i = 0; i < req_sem; i++) {
					for (int j = 0; j < 3; j++) {
						planComp[i][j] = Rcomp[ptr].name;
						ptr++;
					}
				}

				/*
				 * for(int i=0;i<req_sem;i++){ for(int j=0;j<3;j++){ for(int
				 * k=0;k<Rcomp.length;k++){ if(Rcomp[k].Pre_req_1.equals("NULL")
				 * && Rcomp[k].Pre_req_2.equals("NULL")){
				 * planComp[i][j]=Rcomp[k].name; }else
				 * if(Rcomp[k].Pre_req_1.equals("NULL")){ if(Rcomp[k].){
				 * 
				 * } } } } }
				 */

				for (int i = 0; i < planComp.length; i++) {
					System.out.println("Semester: Mid_way: " + i);
					System.out.print("Courses Order: ");
					for (int j = 0; j < planComp[i].length; j++) {
						System.out.print(planComp[i][j] + " ");
					}
					System.out.println();
				}

				/*
				 * for (int i = 0; i < Rcomp.length; i++) {
				 * Rcomp[i].printCourses(); }
				 */

			}

			if (rem_cscs > 0) {

				courses[] Rcscs = new courses[3 - count_cscs];

				int counter = 0;
				for (int i = 0; i < ccscs.length; i++) {
					if (!ccscs[i].pass) {
						Rcscs[counter] = new courses(ccscs[i].name,
								ccscs[i].Pre_req_1, ccscs[i].Pre_req_2, false);
						counter++;
					}
				}

				System.out
						.println("Remaining courses in cscs: " + Rcscs.length);

				if (Rcscs.length > 1) {
					System.out
							.println("Estimated Semesters Required for CSCS: " + 2);
					String planCscs[][] = new String[2][3];

					if (Rcscs.length == 2) {
						System.out.println("Could be taken in one semester");
						planCscs[0][1] = Rcscs[0].name;
						planCscs[0][0] = "other";
						planCscs[0][2] = "other";

						System.out.println("Courses order: " + planCscs[0][0]
								+ " " + planCscs[0][1] + " " + planCscs[0][2]);

						System.out
								.println("Could be taken in next to the 'one' semester");
						planCscs[1][0] = Rcscs[1].name;
						planCscs[1][1] = "other";
						planCscs[1][2] = "other";

						System.out.println("Courses order: " + planCscs[1][0]
								+ " " + planCscs[1][1] + " " + planCscs[1][2]);

						System.out.println("Could be taken together");
						planCscs[0][0] = Rcscs[0].name;
						planCscs[0][1] = "other";
						planCscs[0][2] = Rcscs[1].name;

						System.out.println("Courses order: " + planCscs[0][0]
								+ " " + planCscs[0][1] + " " + planCscs[0][2]);

					}

					if (Rcscs.length == 3) {
						planCscs[0][0] = Rcscs[0].name;
						planCscs[0][1] = "other";
						planCscs[0][2] = "other";

						System.out.println("Should be taken in a semester:");
						System.out.println("Courses order: " + planCscs[0][0]
								+ " " + planCscs[0][1] + " " + planCscs[0][2]);

						planCscs[1][0] = "other";
						planCscs[1][1] = Rcscs[1].name;
						planCscs[1][2] = Rcscs[2].name;

						System.out
								.println("Should be taken together in a semester:");
						System.out.println("Courses order: " + planCscs[1][0]
								+ " " + planCscs[1][1] + " " + planCscs[1][2]);

					}

					/*
					 * for(int i=0;i<2;i++){ for(int j=0;j<3;j++){ for(int
					 * k=0;k<Rcscs.length;k++){
					 * if(Rcscs[k].Pre_req_1.equals("NULL") &&
					 * Rcscs[k].Pre_req_2.equals("NULL")){
					 * planCscs[i][j]=Rcscs[k].name; }else
					 * if(Rcscs[k].Pre_req_1.equals("NULL")){ if(Rcscs[k].){
					 * 
					 * } } } } }
					 */

				} else {
					System.out
							.println("Estimated Semesters Required for CSCS: " + 1);
					String planCscs[][] = new String[1][3];

					System.out.println("Chould be taken in Any_Semster");
					planCscs[0][1] = Rcscs[0].name;
					planCscs[0][0] = "other";
					planCscs[0][2] = "other";

					System.out.println("Courses order: " + planCscs[0][0] + " "
							+ planCscs[0][1] + " " + planCscs[0][2]);

					/*
					 * for(int i=0;i<1;i++){ for(int j=0;j<3;j++){ for(int
					 * k=0;k<Rcscs.length;k++){
					 * if(Rcscs[k].Pre_req_1.equals("NULL") &&
					 * Rcscs[k].Pre_req_2.equals("NULL")){
					 * planCscs[i][j]=Rcscs[k].name; }else
					 * if(Rcscs[k].Pre_req_1.equals("NULL")){ if(Rcscs[k].){
					 * 
					 * } } } } }
					 */

				}
			}

			if (rem_elec > 0) {
				System.out
						.println("Your remaining credits in ELEC Category are: "
								+ rem_elec);
				System.out.println("Courses left: " + rem_elec / 3);

				System.out.println("Elective Courses Offered in Fall");

				for (int i = 0; i < ELEC.length; i++) {
					if(ELEC[i].Fall)
						System.out.println(ELEC[i].Name);
				}
				
				System.out.println("Elective Courses Offered in Spring");

				for (int i = 0; i < ELEC.length; i++) {
					if(ELEC[i].Spring)
						System.out.println(ELEC[i].Name);
				}
				
			}

		} else {
			System.out.println("Degree Already Completed");
			System.out
					.println("Total Credits Earned in CS Department: "
							+ (done_credits_comp + done_credits_cscs + done_credits_elec));
		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		Course[] COMP = new Course[15];
		Course[] CSCS = new Course[3];
		Course[] ELEC = new Course[14];

		// COMP courses
		Scanner scan = new Scanner(new File("COMP.txt"));
		int counter = 0;
		while (scan.hasNext()) {
			String name = scan.next();
			String credit = scan.next();
			String pr1 = scan.next();
			String pr2 = scan.next();
			String fall = scan.next();
			String spring = scan.next();

			if (fall.equals("F") && spring.equals("S")) {
				COMP[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else if (fall.equals("O") && spring.equals("S")) {
				COMP[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else if (fall.equals("S") && spring.equals("O")) {
				COMP[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else {
				COMP[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			}

			counter++;
		}
		scan.close();

		// specialization courses
		Scanner scan1 = new Scanner(new File("CSCS.txt"));
		counter = 0;
		while (scan1.hasNext()) {
			String name = scan1.next();
			String credit = scan1.next();
			String pr1 = scan1.next();
			String pr2 = scan1.next();
			String fall = scan1.next();
			String spring = scan1.next();

			if (fall.equals("F") && spring.equals("S")) {
				CSCS[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else if (fall.equals("O") && spring.equals("S")) {
				CSCS[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else if (fall.equals("S") && spring.equals("O")) {
				CSCS[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else {
				CSCS[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			}

			counter++;
		}
		scan1.close();

		// elective courses
		Scanner scan2 = new Scanner(new File("Electives.txt"));
		counter = 0;
		while (scan2.hasNext()) {
			String name = scan2.next();
			String credit = scan2.next();
			String pr1 = scan2.next();
			String pr2 = scan2.next();
			String fall = scan2.next();
			String spring = scan2.next();

			if (fall.equals("F") && spring.equals("S")) {
				ELEC[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else if (fall.equals("O") && spring.equals("S")) {
				ELEC[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else if (fall.equals("S") && spring.equals("O")) {
				ELEC[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			} else {
				ELEC[counter] = new Course(name, credit, pr1, pr2, fall, spring);
			}

			counter++;
		}
		scan2.close();

		// standard

		Scanner scan3 = new Scanner(new File("standard.txt"));

		Subject[][] standard = new Subject[8][6];

		counter = 0;
		while (scan3.hasNext()) {
			String n1 = scan3.next();
			String n2 = scan3.next();
			String n3 = scan3.next();
			String n4 = scan3.next();
			String n5 = scan3.next();
			String n6 = scan3.next();

			standard[counter][0] = new Subject(n1, true);
			standard[counter][1] = new Subject(n2, true);
			standard[counter][2] = new Subject(n3, true);
			standard[counter][3] = new Subject(n4, true);
			standard[counter][4] = new Subject(n5, true);
			standard[counter][5] = new Subject(n6, true);

			counter++;
		}

		scan3.close();

		/*
		 * //test_print///////////////////// for (int i = 0; i <
		 * standard.length; i++) { System.out.println("Semester"+(i+1)); for(int
		 * j=0; j<standard[i].length;j++){ standard[i][j].printSubject();
		 * System.out.println(); } System.out.println(); }
		 */
		// ///////end test_print////////////
		// input file that reads the status of present student

		Scanner scan4 = new Scanner(new File("input.txt"));
		String s = scan4.next();

		char[] temp = s.toCharArray();
		int semesters = temp[0] - 48;

		// System.out.println(semesters);
		Subject[][] Student = new Subject[semesters][6];

		counter = 0;
		while (scan4.hasNext()) {

			String n1 = scan4.next(); // subject-1
			String n2 = scan4.next();

			String n3 = scan4.next(); // subject-2
			String n4 = scan4.next();

			String n5 = scan4.next(); // subject-3
			String n6 = scan4.next();

			String n7 = scan4.next(); // subject-4
			String n8 = scan4.next();

			String n9 = scan4.next(); // subject-5
			String n10 = scan4.next();

			String n11 = scan4.next(); // subject-6
			String n12 = scan4.next();

			// subject-1
			if (n2.equals("P")) {
				Student[counter][0] = new Subject(n1, true);
			} else {
				Student[counter][0] = new Subject(n1, false);
			}

			// subject-2
			if (n4.equals("P")) {
				Student[counter][1] = new Subject(n3, true);
			} else {
				Student[counter][1] = new Subject(n3, false);
			}

			// subject-3
			if (n6.equals("P")) {
				Student[counter][2] = new Subject(n5, true);
			} else {
				Student[counter][2] = new Subject(n5, false);
			}

			// subject-4
			if (n8.equals("P")) {
				Student[counter][3] = new Subject(n7, true);
			} else {
				Student[counter][3] = new Subject(n7, false);
			}

			// subject-5
			if (n10.equals("P")) {
				Student[counter][4] = new Subject(n9, true);
			} else {
				Student[counter][4] = new Subject(n9, false);
			}

			// subject-6
			if (n12.equals("P")) {
				Student[counter][5] = new Subject(n11, true);
			} else {
				Student[counter][5] = new Subject(n11, false);
			}

			counter++;
		}

		scan4.close();



		System.out.println("Courses Map of Freshman Student (OPTIMAL PATH) ");
		for (int i = 0; i < standard.length; i++) {

			System.out.println("Semester" + (i + 1));

			for (int j = 0; j < standard[i].length; j++) {

				standard[i][j].printSubject(); // System.out.println();

			}

			System.out.println();
		}

		
		
		System.out.println("Courses Map of Present Student");
		for (int i = 0; i < Student.length; i++) {

			System.out.println("Semester" + (i + 1));

			for (int j = 0; j < Student[i].length; j++) {

				Student[i][j].printSubject(); // System.out.println();

			}

			System.out.println();
		}

		// calls the function for student to be advised
		ADVISE(COMP, CSCS, ELEC, standard, Student);
	}

}
