import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class Patient01 {
    Scanner pc = new Scanner(System.in);
    public String userid;
    public String passid;

    public static void main(String[] args) {
        Patient01 tt = new Patient01();

        try {
            Connection str = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital", "root", "root");
            Statement dr4 = str.createStatement();
            tt.BookAppointment(dr4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*********************************************************************************************************************/
    public void patient_Logo() {
        System.out.format("|-----------------------------------------------------------------------------------|\n");
        System.out.format("|                       *=======Welcome-To-Patient-Department=======*               | \n");
        System.out.format("|                              **====Patient DashBoard====**                        | \n");
        System.out.format("|-----------------------------------------------------------------------------------|");
        System.out.println();
    }

    /********************************************************************************************************************/
    public void Signup_for_Patient(Statement dr4) {
        try {
            System.out.print("Enter patient_Id: ");
            int Id = pc.nextInt();
            System.out.print("Enter the patient Name: ");
            pc.nextLine();
            String name = pc.nextLine();
            System.out.print("Enter the Gender: ");
            String gen = pc.nextLine();
            System.out.print("Enter the Contact No: ");
            String cn = pc.nextLine();
            System.out.print("Enter the Age: ");
            int age = pc.nextInt();
            System.out.print("Enter the Blood-group: ");
            pc.nextLine();
            String bg = pc.nextLine();
            System.out.print("Enter the City: ");
            String ct = pc.nextLine();

            String se1 = ("INSERT INTO Patient_Informetion(PatientID,Name_Patient,Gender,Contact_Number,Age,BloodGroup,City) VALUES('"
                    + Id + "','" + name + "','"
                    + gen + "','" + cn + "','" + age + "','" + bg + "','" + ct + "')");
            int er = dr4.executeUpdate(se1);
            if (er > 0) {
                System.out.println("\t ***********Signup -Successfully**********");

            } else {
                System.out.println("Invalid-Data Please ReInsert Correct Data");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("This Data is already Exist");
        }

    }

    /*********************************************************************************************************************/

    public void Login_page_For_Patient(Statement dr4) {
        try {
            System.out.print("Enter the Username:");
            String user = pc.nextLine();
            this.userid = user;
            System.out.print("Enter the Password:");
            String pass = pc.nextLine();
            this.passid = pass;

            String ee = ("select*from Patient_Informetion where PatientID ='" + userid + "' AND Contact_Number='"
                    + passid + "' ");
            ResultSet re = dr4.executeQuery(ee);
            if (re.next()) {
                System.out.println("*****==========Login- Successfully========*****");
                while (true) {
                    System.out.println(
                            "*********--------Choose Option for Patient-------**********");
                    System.out.println("1.\t* Patient View Own  Profile");
                    System.out.println("2.\t* Book Appointment For Patient");
                    System.out.println("3.\t* Patient View Appointments");
                    System.out.println("4.\t* Patient View Reports");
                    System.out.println("5.\t* Patient Give Feedback For Doctor");
                    System.out.println("6.\t* Do You Want to LogOut ");
                    System.out.println(
                            "--------------------------------------------------------------------");
                    int paopt;
                    System.out.print("Your Input is:-");
                    if (pc.hasNextInt()) {
                        paopt = pc.nextInt();
                        System.out.println(
                                "--------------------------------------------------------------------");
                    } else {
                        pc.nextLine();
                        continue;
                    }

                    switch (paopt) {

                        case 1:

                            System.out.println(
                                    "\t=========Welcome to Patient Information========");
                            view_Patient_Profile(dr4);
                            break;
                        case 2:
                            System.out.println(
                                    "\t -----Welcome to Book Appointment for Patient------");
                            BookAppointment(dr4);
                            break;
                        case 3:
                            System.out.println(
                                    "\t=============Welcome to View Appointment for Patient==============");
                            view_Appointment_for_Patient(dr4);
                            break;
                        case 4:
                            System.out.println(
                                    "\t********** Welcome to View Patient Reports*********");
                            view_Patient_Report(dr4);
                            break;
                        case 5:
                            System.out.println(
                                    "\t ****---____Give Your Valuable Feedback____---****");
                            give_patient_feedback_for_doctor(dr4);
                            break;
                        case 6:
                            pc.close();
                            System.exit(0);
                            System.out.println("__________****Thankyou**** _______");
                            break;
                        default:
                            System.out.println(" Please try later...............!!!");
                    }
                }

            } else {
                System.out.println("----------Invalid user and password-----------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /********************************************************************************************************************/
    public void view_Patient_Profile(Statement dr4) {
        try {
            System.out.println("Enter PatientID:");
            int id = pc.nextInt();
            String pr = ("SELECT*FROM Patient_Informetion WHERE PatientID=" + id);
            ResultSet rp = dr4.executeQuery(pr);
            while (rp.next()) {
                System.out.println("Patient ID:" + rp.getString("PatientID"));
                System.out.println("Name of Patient:" + rp.getString("Name_Patient"));
                System.out.println("Gender:" + rp.getString("Gender"));
                System.out.println("Contact Number:" + rp.getString("Contact_Number"));
                System.out.println("BloodGroup of Patient :" + rp.getString("BloodGroup"));
                System.out.println("Address:" + rp.getString("City"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------Invalid ID--------");
        }
    }

    /*********************************************************************************************************************/
    public void view_Appointment_for_Patient(Statement dr4) {
        try {
            System.out.println("Enter the patientID:");
            int p = pc.nextInt();
            String dr = ("select*from Appointments where PatientId= " + p);
            ResultSet va = dr4.executeQuery(dr);
            while (va.next()) {
                System.out.println("\t AppointmentID:" + va.getString("AppointmentID"));
                System.out.println("\t Patient Problem:" + va.getString("Problem"));
                System.out.println("\t Doctor Name:" + va.getString("DoctorName"));
                System.out.println("\t Doctor-ID:" + va.getString("DoctorID"));
                System.out.println("\t Doctor_Type:" + va.getString("Department"));
                System.out.println("\t Doctor_Fee:" + va.getString("Entry_Fee"));
                System.out.println("\t Appointment-Date :" + va.getString("Ap_Date"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("**********Invalid PatientID********");

        }
    }

    /*********************************************************************************************************************/
    public void BookAppointment(Statement dr4) {
        try {
            System.out.println("Enter the AppointmentID:");
            int ap = pc.nextInt();
            System.out.println("Enter your Problem:");
            pc.nextLine();
            String pr = pc.nextLine();
            System.out.println("Enter The patientID");
            int pq = pc.nextInt();
            System.out.println("Enter The Doctor Name:");
            pc.nextLine();
            String dc = pc.nextLine();
            System.out.println("Enter the DoctorID:");
            int di = pc.nextInt();
            System.out.println("Enter the Doctor Type:");
            pc.nextLine();
            String dd = pc.nextLine();
            System.out.println("Enter the Doctor Fee:");
            int fe = pc.nextInt();

            String pa = ("insert into Appointments (AppointmentID,Problem,PatientId,DoctorName,DoctorID,Department,Entry_Fee,Ap_Date,PaymentStatus,Appointment_Status)values('"
                    + ap + "','" + pr + "','" + pq + "','" + dc + "','" + di + "','" + dd + "','" + fe
                    + "',now(),'Pending', 'Yes')");
            int result5 = dr4.executeUpdate(pa);
            if (result5 > 0) {
                System.out.println("Appointments Update successfully");
            } else {
                System.out.println("********Invalid data********");
            }
            System.out.print("Do you pay Doctor Fee 'yes' press 1 or 'No' press 2 : ");
            int fr = pc.nextInt();
            if (fr == 1) {
                String uo = ("update Appointments set PaymentStatus='Done'");
                int dn = dr4.executeUpdate(uo);
                if (dn > 0) {
                    System.out.println("-------___Payment is DONE___--------");
                }
            } else if (fr == 2) {
                System.out.println("Thankyou");
            } else {
                System.out.println("Please Enter the valid Number");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("This Id are already exist ");
        }

    }

    /*********************************************************************************************************************/
    public void bill_payment() {

        System.out.println("Doctor-Fees:");
        System.out.println("CARD-HOLDER Name: ");
        String cardHolderName = pc.nextLine();
        System.out.println("CARD-NUMBER : ");
        String card_no = pc.nextLine();
        System.out.println("Please Enter 1 to confirm Payment");
        int x = pc.nextInt();
        if (x == 1) {
            System.out.println("Your Payment is confirmed");

        } else {
            System.out.println("Your Appointment is cancelled");

        }

    }

    /*********************************************************************************************************************/

    public void give_patient_feedback_for_doctor(Statement dr4) {
        System.out.println("*********Please Fill The Following Feedback Form*********");
        System.out.println("Patient Id:");
        int pid = pc.nextInt();
        System.out.println("Nature Of The Doctor");
        pc.nextLine();
        String Doc_Nature = pc.nextLine();
        System.out.println("Enter Your Address below");
        String Location = pc.nextLine();
        System.out.println("Your Comment:");

        String YourComment = pc.nextLine();

        try {
            String ve = ("INSERT INTO feedback(PatientID , Doc_Nature, Location, PatientComment) VALUES ('" + pid
                    + "','" + Doc_Nature + "','" + Location + "','" + YourComment + "')");
            int result4 = dr4.executeUpdate(ve);
            if (result4 > 0) {
                System.out.println("Feedback Submitted Sucssecefully");
            } else {
                System.out.println("************Invalid PatientID************");
            }
            System.out.println("-****Thank You For Visiting Us****-");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /************************************************************************************************************************/
    public void view_Patient_Report(Statement dr4) {
        try {
            System.out.println("Enter your PatientID :");
            int pp = pc.nextInt();
            String top = ("select *from reports where patientID=" + pp);
            ResultSet rts = dr4.executeQuery(top);
            while (rts.next()) {
                System.out.println("\t Your Report ID :" + rts.getString("ReportID"));
                System.out.println("\t Your appointmentID :" + rts.getString("appointmentID"));
                System.out.println("\t Your DoctorID :" + rts.getString("DoctorID"));
                System.out.println(
                        "\t What Medicine Prescribed your Doctor  :" + rts.getString("MedicinePrescribed"));
                System.out.println("\t Other Comment of Doctor :" + rts.getString("Doctor_Comment"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("****Your Report is Not Generate****");

        }
        /************************************************************************************************************************/

        /************************************************************************************************************************/

    }
}
