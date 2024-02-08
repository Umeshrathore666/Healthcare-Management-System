import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.script.ScriptContext;

public class Admin01 {
    String userid;
    String passid;
    Scanner a = new Scanner(System.in);

    public static void main(String[] args) {
        Admin01 g = new Admin01();
        try {
            Connection str = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hospital", "root", "root");
            Statement dr4 = str.createStatement();
            g.Doctor_List(dr4);
            g.view_Patients_List(dr4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /********************************************************************************************************************/
    public void admin_Logo() {
        System.out.format("|-----------------------------------------------------------------------------------|\n");
        System.out.format("|                       *=======Welcome-To-Admin-Department=======*                 | \n");
        System.out.format("|                              **====Admin DashBoard====**                          | \n");
        System.out.format("|-----------------------------------------------------------------------------------|");
        System.out.println();
    }

    /********************************************************************************************************************/
    public void view_Admin_Profile(Statement dr4) {
        try {
            System.out.print("Enter the AdminID: ");
            int aid = a.nextInt();
            // System.out.print("Enter the name of Admin: ");
            // a.nextLine();
            // String name = a.nextLine();
            // System.out.print("Enter the Contact: ");
            // String cnn = a.nextLine();
            // System.out.print("Enter the Email-ID: ");
            // String em = a.nextLine();
            String ur = ("SELECT*FROM Admin_Informetion WHERE AdminID = " + aid);

            ResultSet result3 = dr4.executeQuery(ur);
            while (result3.next()) {
                System.out.println("Name Of Admin: " + result3.getString("Name_Admin"));
                System.out.println("Contact: " + result3.getString("Contact"));
                System.out.println("Email-ID:" + result3.getString("EmailID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /********************************************************************************************************************/
    public void Login_page_For_Admin(Statement dr4) {
        try {
            System.out.print("Enter the Username: ");
            String user = a.nextLine();
            this.userid = user;
            System.out.print("Enter the Password: ");
            String pass = a.nextLine();
            this.passid = pass;

            String ee = ("select*from Admin_Informetion where AdminID='" + userid + "' AND Contact='" + passid + "'");
            ResultSet re = dr4.executeQuery(ee);
            if (re.next()) {

                System.out.println("*****==========Login- Successfully========*****");
                while (true) {
                    System.out.println("*********--------Choose Option for Admin-------**********");
                    System.out.println("1.\t* Admin View Own Profile");
                    System.out.println("2.\t* Admin Can Add Doctor ");
                    System.out.println("3.\t* Admin Can Remove Doctor");
                    System.out.println("4.\t* Admin Can Remove Patient");
                    System.out.println("5.\t* Admin Can See Patient List");
                    System.out.println("6.\t* Admin Can See Doctor List");
                    System.out.println("7.\t* Admin Can See Feedback Of Patient");
                    System.out.println("8.\t* Admin Can See Reports of Patient");
                    System.out.println("9.\t* Do You Want to LogOut ");
                    System.out.println(
                            "--------------------------------------------------------------------");
                    int adopt;
                    System.out.print("Your Input is:-");
                    if (a.hasNextInt()) {
                        adopt = a.nextInt();
                        System.out.println(
                                "--------------------------------------------------------------------");
                    } else {
                        a.nextLine();
                        continue;
                    }
                    switch (adopt) {
                        case 1:
                            System.out
                                    .println("\t ****---____Welcome to  Admin View Own Profile____---****");
                            view_Admin_Profile(dr4);
                            break;
                        case 2:
                            System.out.println("\t=========Welcome to  Admin Can Add Doctor==========");
                            Add_Doctor(dr4);
                            break;
                        case 3:
                            System.out.println("*****==Welcome to Admin Can Remove Doctor==*****");
                            Remove_Doctor(dr4);
                            break;
                        case 4:
                            System.out.println(
                                    "\t ****---____Welcome to Admin Can Remove Patient____---****");
                            Remove_Patient(dr4);
                            break;
                        case 5:
                            System.out
                                    .println("\t=========Welcome to Admin Can See Patient List==========");
                            view_Patients_List(dr4);
                            break;
                        case 6:
                            System.out.println("*****== Welcome to Admin Can See Doctor List ==*****");
                            Doctor_List(dr4);
                            break;
                        case 7:
                            System.out
                                    .println(
                                            "\t=======Welcome to Admin Can See Feedback Of Patient ========");
                            see_feedback_Admin(dr4);
                            break;
                        case 8:
                            System.out
                                    .println("*****== Welcome to Admin Can See Reports of Patient ==*****");
                            view_reports(dr4);
                            break;
                        case 9:
                            a.close();
                            System.out.println("\t\t_______****Thankyou**** _______");
                            System.exit(0);
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

    /**************************************************************************************************************************/
    public void Add_Doctor(Statement dr4) {
        try {
            System.out.print("Enter the ID No: ");
            int s = a.nextInt();
            System.out.print("Enter Doctor name: ");
            a.nextLine();
            String dc = a.nextLine();
            System.out.print("Enter the Department: ");
            String dp = a.nextLine();
            System.out.print("Enter the Contact No: ");
            String dw = a.nextLine();
            System.out.print("Enter the City : ");
            String dj = a.nextLine();
            System.out.print("Enter the Gender: ");
            String dg = a.nextLine();
            System.out.print("Enter the Email-ID: ");
            String em = a.nextLine();
            System.out.print("Enter the Entry Fee: ");
            int f = a.nextInt();
            String se = ("INSERT INTO Doctor_Informetion(DoctorID,Name_Doctor,Department,Contact,City,Gender,EmailID,Entry_Fee) VALUES('"
                    + s + "','" + dc + "','" + dp + "','" + dw + "','" + dj + "','" + dg + "','" + em + "','" + f
                    + "')");
            int dq = dr4.executeUpdate(se);
            if (dq > 0) {
                System.out.println("\t****Add Doctor Successfully****");
            } else {
                System.out.println("----Data is already Exist----");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some Data - Invalid Data");
        }
    }

    /**********************************************************************************************************************/
    public void Remove_Doctor(Statement dr4) {
        try {
            System.out.print("Enter the Doctor Id: ");
            int dr2 = a.nextInt();
            String tt1 = ("DELETE FROM Doctor_Informetion WHERE DoctorID = " + dr2);
            int result2 = dr4.executeUpdate(tt1);
            if (result2 > 0) {
                System.out.println(" ===*****Delete Doctor Data sucssecefully****=== ");
            } else {
                System.out.println("Invalid Doctor-ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*******************************************************************************************************/
    public void Remove_Patient(Statement dr4) {
        try {
            System.out.print("Enter the Patient Id: ");
            int pr1 = a.nextInt();
            String tt2 = ("DELETE FROM Patient_Informetion WHERE PatientID = " + pr1);
            int result2 = dr4.executeUpdate(tt2);
            if (result2 > 0) {
                System.out.println("\t---***Delete Patient Data sucssecefully***--- ");
            } else {
                System.out.println("Invalid Patient ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /************************************************************************************************************************/
    public void view_Patients_List(Statement dr4) {
        try {
            System.out.print("Please Enter 1 to show All-Patient-List: ");
            int l = a.nextInt();
            if (l == 1) {
                String vp = ("select*from Patient_Informetion ");
                ResultSet vir = dr4.executeQuery(vp);
                while (vir.next()) {
                    System.out.println("Patient ID:" + vir.getString("PatientID"));
                    System.out.println("Name Of Patient:" + vir.getString("Name_Patient"));
                    System.out.println("Tye of Doctor Department:" + vir.getString("Contact_Number"));
                    System.out.println("Address of Patient:" + vir.getString("City"));
                    System.out.println("*****************==========Done=========******************");
                }
            } else {
                System.out.println("You Have a Enter Invalid Number ");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=====*****Invalid Data Please Insert Correct Data******=====");
        }
    }

    /************************************************************************************************************************/
    public void Doctor_List(Statement dr4) {
        try {
            System.out.print("Please Enter 1 to show All-Doctor-List: ");
            int l = a.nextInt();
            if (l == 1) {
                String vd = ("SELECT*from Doctor_Informetion");
                ResultSet rp = dr4.executeQuery(vd);
                while (rp.next()) {
                    System.out.println("Doctor ID:" + rp.getString("DoctorID"));
                    System.out.println("Name Of Doctor:" + rp.getString("Name_Doctor"));
                    System.out.println("Tye of Doctor Department:" + rp.getString("Department"));
                    System.out.println("Doctor Entry_Fee:" + rp.getString("Entry_Fee"));
                    System.out.println("*****************==========Done=========******************");
                }
            } else {
                System.out.println("You Have a Enter Invalid Number ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("=====*****Invalid Data Please Insert Correct Data******=====");
        }
    }

    /************************************************************************************************************************/
    public void see_feedback_Admin(Statement dr4) {
        try {
            System.out.print("Enter the PatientID: ");
            int ad = a.nextInt();
            String adp = ("select*from feedback where PatientID= " + ad);
            ResultSet rev = dr4.executeQuery(adp);
            while (rev.next()) {
                System.out.println("\t Doctor Nature:" + rev.getString("Doc_Nature"));
                System.out.println("\t Patient Location :" + rev.getString("Location"));
                System.out.println("\t Patient Comment :" + rev.getString("PatientComment"));
            }
            System.out.println("========================********=======================");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("*******Invalid-Data******");
        }

    }

    /***********************************************************************************************************************************/
    public void view_reports(Statement dr4) {
        try {
            System.out.print("Please Enter 1 to show ll-Reports: ");
            int l = a.nextInt();
            if (l == 1) {
                String rto = ("select *from reports ");
                ResultSet rt = dr4.executeQuery(rto);
                while (rt.next()) {
                    System.out.println("\t Report-ID :" + rt.getString("ReportID"));
                    System.out.println("\t Appointment-ID :" + rt.getString("appointmentID"));
                    System.out.println("\t  Patient-ID :" + rt.getString("PatientID"));
                    System.out.println("\t  Doctor-ID :" + rt.getString("DoctorID"));
                    System.out
                            .println("\t What Medicine Prescribed your Doctor  :" + rt.getString("MedicinePrescribed"));
                    System.out.println("\t The Doctor Comment is: " + rt.getString("Doctor_Comment"));
                    System.out.println("***************============================***************");
                }
            } else {
                System.out.println("*******Thankyou********");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("*********Please Enter the valid Data********");
        }

    }
}
/***********************************************************************************************************************************/
