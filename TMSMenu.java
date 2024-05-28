import java.util.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TMSMenu {
    static final String DB_URL = "jdbc:mysql://localhost:3306/tms"; // Include the name of the database
    static final String USER = "root";
    static final String PASS = "";
    static boolean exitTime = false;

    public static void TMS_Login_XO() {
        String Username, Password;

        Username = JOptionPane.showInputDialog(null, "Enter your Username", "Login Screen", JOptionPane.INFORMATION_MESSAGE);
        Password = JOptionPane.showInputDialog(null, "Enter your Password", "Login Screen", JOptionPane.INFORMATION_MESSAGE);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM sc_sysuser_br WHERE UserLogin='" + Username + "' AND UserPass='" + Password + "'");
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                TMS_Menu0_XO();
                exitTime = true;
            } else {
                JOptionPane.showMessageDialog(null, "USERNAME OR PASSWORD INCORRECT", "Login Screen", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void SC_Initialize_XO() {
        JOptionPane.showMessageDialog(null, "SC_Initialize_XO has fully loaded!");
        // Perform initialization tasks
    }

    public static void SC_SessLog_AO() {
        JOptionPane.showMessageDialog(null, "SC_SessLog_AO has fully loaded");
        // Perform session logging tasks
    }

    public static void TMS_Menu0_XO() {
        String[] options = { "CIS Options", "LMS Options", "SCS Options", "Exit" };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select an option:",
                "TMS Menu 0",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                TMS_Menu1_XO();
                break;
            case 1:
                TMS_Menu2_XO();
                break;
            case 2:
                TMS_Menu3_XO();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
        }
    }

    //Control Information Subsystem
    public static void TMS_Menu1_XO() {
        String[] options = { "Countries", "Province/States", "Locations", "Streets and Roads", "Transportation Vehicles", "Transportation Custodians", "Quit" };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select an option:",
                "CIS Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You Have Selected Countries...");
                CIS CISCount = new CIS(); CISCount.ci_count_br();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You Have Selected Province/States...");
                CIS CISProv = new CIS(); CISProv.ci_prov_br();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "You Have Selected Locations...");
                CIS CISLoc = new CIS(); CISLoc.ci_loc_br();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "You Have Selected Streets and Roads...");
                CIS CISStrRd = new CIS(); CISStrRd.ci_strrd_br();
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "You Have Selected Transportation Vehicles...");
                CIS CISTransVeh = new CIS(); CISTransVeh.ci_transveh_br();
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Transportation Custodians...");
                CIS CISCust = new CIS(); CISCust.ci_transcust_br();
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "Exiting...");
                break;
        }
    }

    //Logistic Management Subsystem
    public static void TMS_Menu2_XO() {
        String[] options = { "Route Summaries", "Route Details", "Vehicle Maintenance Companies", "Vehicle Maintenance Logs", "Route Schedules", "Route Execution Logs", "Road Incidents Logs", "Frequently Asked Questions", "Quit" };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select an option:",
                "LMS Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You Have Selected Route Summaries...");
                LMS LMSRteSum = new LMS(); LMSRteSum.lm_rtesum_br();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You Have Selected Route Details...");
                LMS LMSRteDtls = new LMS(); LMSRteDtls.lm_rtedtls_br();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "You Have Selected Vehicle Maintenance Company...");
                LMS LMSvehmntscomp = new LMS(); LMSvehmntscomp.lm_vehmntscomp_br();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "You Have Selected Vehicle Maintenance Logs...");
                LMS LMSvehmaintlog = new LMS(); LMSvehmaintlog.lm_vehmaintlog_br();
                break; 
            case 4:
                JOptionPane.showMessageDialog(null, "You Have Selected Route Schedule...");
                LMS LMSrtesch = new LMS(); LMSrtesch.lm_rtesch_br();
                break; 
            case 5:
                JOptionPane.showMessageDialog(null, "You Have Selected Route Execution Logs...");
                LMS LMSrteexlog = new LMS(); LMSrteexlog.lm_rteexlog_br();
                break; 
            case 6:
                JOptionPane.showMessageDialog(null, "You Have Selected Road Incident Logs...");
                LMS LMSrdinclog = new LMS(); LMSrdinclog.lm_rdinclog_br();
                break;
            case 7:
                JOptionPane.showMessageDialog(null, "You Have Selected FAQ...");
                LMS LMSfaq = new LMS(); LMSfaq.lm_faq_br();
                break;  
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
        }
    }

    //System Control Subsystem
    public static void TMS_Menu3_XO() {
        String[] options = { "Audit File for Addition", "Audit File for Update", "Audit File for Deletion", "System Message", "System User", "Session Log", "Session Mark", "System User" };
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select an option:",
                "SCS Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                JOptionPane.showMessageDialog(null, "You Have Selected Audit Add...");
                SCS SCSaudadd = new SCS(); SCSaudadd.sc_AudAdd_br();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, "You Have Selected Audit Update...");
                SCS SCSaudupd = new SCS(); SCSaudupd.sc_AudUpd_br();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "You Have Selected Audit Delete...");
                SCS SCSauddel = new SCS(); SCSauddel.sc_AudUpd_br();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "You Have Selected Session Log...");
                SCS SCSsesslog = new SCS(); SCSsesslog.sc_SessLog_br();
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "You Have Selected Session Log...");
                SCS SCSsysuser = new SCS(); SCSsysuser.sc_SysUser_br();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
        }
    }
}