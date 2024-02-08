import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Doctor01 {
    String userid;
    String passid;
    Scanner ty = new Scanner(System.in);

    public static void main(String[] args) {
        Doctor01 iu = new Doctor01();
        try {
            Connection str = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital", "root", "root");
            Statement dr4 = str.createStatement();
            iu.Login_page_For_Doctor(dr4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Doctor_Logo() {
        System.out.format("|----------------------------------------------------------------------------------|\n");
        System.out.format("|                       =======Welcome-To-Doctor-Department=======                 |\n");
        System.out.format("|                              ====Doctor DashBoard====                            |\n");
        System.out.format("|----------------------------------------------------------------------------------|\n");
        System.out.println();
    }

    /**************************************************************************************************************/
    public void Login_page_For_Doctor(Statement dr4) {
        try {
            System.out.print("Enter the Username:");
            String user = ty.nextLine();
            this.userid = user;
            System.out.print("Enter the Password:");
            String pass = ty.nextLine();
            this.passid = pass;

            String ee = ("select*from Doctor_Informetion where DoctorID='" + userid + "' AND Contact='" + passid
                    + "'");
            ResultSet re = dr4.executeQuery(ee);
            if (re.next()) {

                System.out.println("*****==========Login- Successfully========*****");
                while (true) {
                    System.out.println("1.\t* Doctor View Own Profile");
                    System.out.println("2.\t* Doctor view Appointments ");
                    System.out.println("3.\t* Doctor Generate Report for Patient");
                    System.out.println("4.\t* Do You Want to LogOut ");
                    System.out.println(
                            "--------------------------------------------------------------------");

                    int doopt;
                    System.out.print("Your Input is:-");
                    if (ty.hasNextInt()) {
                        doopt = ty.nextInt();
                        System.out.println(
                                "--------------------------------------------------------------------");
                    } else {
                        ty.nextLine();
                        continue;
                    }
                    switch (doopt) {
                        case 1:
                            System.out
                                    .println("\t ****---____Welcome to Doctor View Own Profile____---****");
                            view_Doctor(dr4);
                            break;
                        case 2:
                            System.out.println("\t=========Welcome to  Doctor view Appointments==========");
                            view_Appointment_for_Doctor(dr4);
                            break;
                        case 3:
                            System.out
                                    .println("*****==Welcome to Doctor Generate Report for Patient==*****");
                            doctor_generate_Report_for_Patient(dr4);
                            break;
                        case 4:
                            ty.close();
                            System.exit(0);
                            System.out.println("__________****Thankyou**** _______");
                            break;
                        default:
                            System.out.println(" Please try later...............!!!");
                    }
                }
            } else {
                System.out.println("====Please Enter Correct UserId & Password====");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /********************************************************************************************************************/
    public void view_Doctor(Statement dr4) {
        try {
            System.out.print("Please Enter the Doctor_Id:");
            int sd = ty.nextInt();
            System.out.println("--------------------------------------------------------");
            String vd = ("SELECT * FROM Doctor_Informetion WHERE DoctorID=" + sd);
            ResultSet rs = dr4.executeQuery(vd);
            while (rs.next()) {
                System.out.println("Doctor ID:" + rs.getString("DoctorID"));
                System.out.println("Name Of Doctor:" + rs.getString("Name_Doctor"));
                System.out.println("Tye of Doctor Department" + rs.getString("Department"));
                System.out.println("Contact Detail:  " + rs.getString("Contact"));
                System.out.println("City:  " + rs.getString("City"));
                System.out.println("Doctor of Gender:  " + rs.getString("Gender"));
                System.out.println("Doctor of EmailID  " + rs.getString("EmailID"));
                System.out.println("*************==========Done=========******************");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("=====*****Invalid Data Please Insert Correct Data******=====");
        }
    }

    /*************************************************************************************************************/
    public void ChooseDoctor(Statement dr4) {
        System.out.println(
                "*** Choose Doctor Type According to your problem!! ***\n 1.Bones_Specialist \n 2.Heart_Specialist \n 3.Kidney_Specialist");
        int ch = ty.nextInt();
        switch (ch) {
            case 1: {
                System.out.println("\t*** Enter The Doctor-ID which You Want to Choose ***");
                int choosedID = ty.nextInt();
                try {
                    ResultSet re = dr4.executeQuery("select * from Doctor_Informetion where Department='Bones'");
                    while (re.next()) {
                        System.out.println("\t* Doctor_ID :     " + re.getString("DoctorID"));
                        System.out.println("\t* Name_Doctor :    " + re.getString("Name_Doctor"));
                        System.out.println("\t* EmailID:" + re.getString("EmailID"));
                        System.out.println("\t* Entry_Charge :  " + re.getInt(" Entry_Fee"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
                break;
            case 2: {
                System.out.println("\t*** Enter The Doctor-ID which You Want to Choose ***");
                int choosedID = ty.nextInt();
                try {
                    ResultSet re = dr4.executeQuery("select * from Doctor_Informetion where Department='Heart'");
                    while (re.next()) {
                        System.out.println("\t* Doctor_ID :     " + re.getString("DoctorID"));
                        System.out.println("\t* Name_Doctor :    " + re.getString("Name_Doctor"));
                        System.out.println("\t* EmailID:" + re.getString("EmailID"));
                        System.out.println("\t* Entry_Charge :  " + re.getInt(" Entry_Fee"));

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
            }
                break;
            case 3: {
                System.out.println("\t*** Enter The Doctor-ID which You Want to Choose ***");
                int choosedID = ty.nextInt();
                try {
                    ResultSet re = dr4.executeQuery("select * from Doctor_Informetion where Department='Kidney'");
                    while (re.next()) {
                        System.out.println("\t* Doctor_ID :     " + re.getString("DoctorID"));
                        System.out.println("\t* Name_Doctor :    " + re.getString("Name_Doctor"));
                        System.out.println("\t* EmailID:" + re.getString("EmailID"));
                        System.out.println("\t* Entry_Charge :  " + re.getInt("Entry_Fee"));

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
                break;
            default:
                System.out.println("*********Invalid choose Option*********");
        }

    }

    /******************************************************************************************************************************/
    public void view_Appointment_for_Doctor(Statement dr4) {
        try {
            System.out.print("Enter the DoctorID:-   ");
            int d = ty.nextInt();
            String dr = ("select*from Appointments where DoctorID= " + d);
            ResultSet vr = dr4.executeQuery(dr);
            while (vr.next()) {
                System.out.println("\t AppointmentID:-" + vr.getString("AppointmentID"));
                System.out.println("\t Patient Problem:-" + vr.getString("Problem"));
                System.out.println("\t Patient-ID:-" + vr.getString("PatientID"));
                System.out.println("\t Doctor-Name:-" + vr.getString("Problem"));
                System.out.println("\t Doctor_Type:-" + vr.getString("Department"));
                System.out.println("\t Doctor_Fee:-" + vr.getString("Entry_Fee"));
                System.out.println("\t Appointment-Date:-" + vr.getString("Ap_Date"));
                System.out.println("************==================================***************");
            }
            System.out.println("For Attend the Paitent");
            System.out.print("Please enter the Patient-ID: ");
            int pid = ty.nextInt();
            String bb = ("select*from Patient_informetion where PatientID= " + pid);
            ResultSet pd = dr4.executeQuery(bb);
            while (pd.next()) {
                System.out.println("Name of Patient:" + pd.getString("Name_Patient"));
                System.out.println("Gender:" + pd.getString("Gender"));
                System.out.println("Contact Number:" + pd.getString("Contact_Number"));
                System.out.println("BloodGroup of Patient :" + pd.getString("BloodGroup"));
                System.out.println("Address:" + pd.getString("City"));
            }
            System.out.println("Do you want to attend the patient Press 1 Otherwise press 2");
            int att = ty.nextInt();
            if (att == 1) {
                Attend_Patient(dr4);
            } else {
                System.out.println("");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("**********Invalid PatientID********");

        }
    }

    /***************************************************************************************************************/
    public void doctor_generate_Report_for_Patient(Statement dr4) {
        try {
            System.out.print("Enter the  ReportID: ");
            int rep = ty.nextInt();
            System.out.print("Enter the  appointmentID: ");
            int aep = ty.nextInt();
            System.out.print("Enter the  patientID: ");
            int pes = ty.nextInt();
            System.out.print("Enter the  DoctorID: ");
            int dec = ty.nextInt();
            System.out.println(" Enter the MedicinePrescribed for Patient:");
            ty.nextLine();
            String medip = ty.nextLine();
            System.out.println("  Enter the Additional Information: ");
            ty.nextLine();
            String Dcom = ty.nextLine();
            System.out.println("Enter the 1 Generate Report............");
            int y = ty.nextInt();
            String repo = ("insert into reports(ReportID,appointmentID,patientID,DoctorID,MedicinePrescribed,Doctor_Comment) values('"
                    + rep + "','" + aep + "','" + pes + "','" + dec + "','" + medip + "','" + Dcom + "') ");
            int zz = dr4.executeUpdate(repo);
            if (zz > 0) {
                System.out.println("**********Generate Report**********");
            } else {
                System.out.println("----Please Enter the Valid data----");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /******************************************************************************************************************/
    public void Attend_Patient(Statement dr4) {
        try {
            doctor_generate_Report_for_Patient(dr4);
        } catch (Exception r1) {
            r1.printStackTrace();
            System.out.println(r1.getMessage());
        }
    }
}
