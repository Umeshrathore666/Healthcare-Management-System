/*E-HealthCare-Management-System is a console based application which is built using java.This application helps in management of Patients, doctors, admin in a easy and comfortable way.using this Application patients can quickly Sign up, Login, view his/her profile, view doctors, book Appointment, view Report, choose doctor, view Appointments, give feedback, pay online and logout. Admin can add Doctors,view patients list, view Doctors list,remove doctors, see feedback given by patients,view reports,logout.Doctor can login, view profile, viewAppointments, Attend Patients and logout. */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class HathCare01 {

    public static void main(String[] args) {

        Patient01 patient = new Patient01();
        Doctor01 doctor = new Doctor01();
        Admin01 admin = new Admin01();

        System.out.format("|-----------------------------------------------------------------------------------|\n");
        System.out.format("|                                *=======Welcome-To=======*                         | \n");
        System.out.format("|                  **====▂▃▅▇█▓▒░MEDANTA THE MEDICITY░▒▓█▇▅▃▂====**                 | \n");
        System.out.format("|-----------------------------------------------------------------------------------|");
        System.out.println();
        try {
            Connection str = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital", "root", "root");
            Statement dr4 = str.createStatement();
            Scanner fin = new Scanner(System.in);
            while (true) { // choose hospital Department
                System.out.println("\t\t\t********CHOOSE YOUR OPTION**********");
                System.out.println(
                        "====================================================================================");
                System.out.println("\t1. ***PATIENT***\n");
                System.out.println("\t2. ***DOCTOR***\n");
                System.out.println("\t3. ***ADMIN***\n");
                System.out.println("---------------------------------------------------------------------");
                int opt;
                System.out.print("Your Input is:-");
                if (fin.hasNextInt()) {
                    opt = fin.nextInt();
                    System.out.println("---------------------------------------------------------------------");
                } else {
                    fin.nextLine();
                    continue;
                }
                /***********************************************************************************************/
                switch (opt) {
                    case 1:
                        System.out.println("==================================================================");
                        while (true) {
                            System.out.println("1.\t* Signup For Patient");
                            System.out.println("2.\t* Login For Patient");
                            int ent;
                            System.out.print("Your Input is:-");
                            if (fin.hasNextInt()) {
                                ent = fin.nextInt();
                            } else {
                                fin.nextLine();
                                continue;
                            }
                            switch (ent) {
                                case 1:
                                    System.out.println("*****==Welcome to Patient Signup==*****");
                                    patient.Signup_for_Patient(dr4);
                                    System.out.println("Do You Want to Continue Login. For Press 1 Otherwise 2 ");
                                    int sinlog = fin.nextInt();
                                    if (sinlog == 1) {
                                        patient.Login_page_For_Patient(dr4);
                                    } else if (sinlog == 2) {
                                        System.out.println("\t*********Thankyou*********");
                                    } else {
                                        System.out.println("\t-----Please Enter Valid Key------");
                                    }

                                    break;
                                case 2:
                                    System.out.println("\t*******Welcome to Login for Patient********");
                                    patient.patient_Logo();
                                    patient.Login_page_For_Patient(dr4);
                                    break;
                                default:
                                    System.out.println("please try Later.......");
                            }

                        }
                        /*************************************************************************************************************************/
                    case 2:
                        doctor.Doctor_Logo();
                        System.out.println("*****==Welcome to Login for Doctor==*****");
                        doctor.Login_page_For_Doctor(dr4);
                        break;
                    /****************************************************************************************************************************** */
                    case 3:
                        admin.admin_Logo();
                        System.out.println("*****==Welcome to Login for Admin==*****");
                        admin.Login_page_For_Admin(dr4);
                        break;
                    default:
                        System.out.println("please try later,");
                }

            }
            /******************************************************************************************************/
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}