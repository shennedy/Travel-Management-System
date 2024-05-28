import java.util.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class LMS {
    static Scanner JObject = new Scanner (System.in);
    static String input;
    final static String HEADING1 = "Logistics Management System";
    final static String Inquiry = "Choose a value to Inquiry";
    static boolean exitTime = false;
    static final String DB_URL = "jdbc:mysql://localhost:3306/tms"; 
    static final String USER = "root";
    static final String PASS = "";

//******************* Start of Route Summaries  ********************************************************************************************************************************************/
public void lm_rtesum_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_rtesum_brAdd(); break;}
            case 2: {lm_rtesum_brModify(); break;}
            case 3: {lm_rtesum_brDelete(); break;}
            case 4: {lm_rtesum_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_rtesum_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtesum_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_rtesum_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_rtesum_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rtesum_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtesum_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_rtesum_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_rtesum_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rtesum_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rtesum_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtesum_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_rtesum_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_rtesum_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rtesum_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_rtesum_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Route Code. \n"
            +   "2. Route Name. \n"
            +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CountCd column values from lm_rtesum_br table
                ResultSet rs = stmt.executeQuery("SELECT RteCd FROM lm_rtesum_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("RteCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "RteCd values from lm_rtesum_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No RteCd values found in lm_rtesum_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CountCd column values from lm_rtesum_br table
                ResultSet rs = stmt.executeQuery("SELECT RteName FROM lm_rtesum_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("RteName")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "RteName values from lm_rtesum_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No CountName values found in lm_rtesum_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
            
            case 3: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}
 
//******************* End of Route Summaries  ********************************************************************************************************************************************/

//******************* Start of Route Details ********************************************************************************************************************************************/
public void lm_rtedtls_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_rtedtls_brAdd(); break;}
            case 2: {lm_rtedtls_brModify(); break;}
            case 3: {lm_rtedtls_brDelete(); break;}
            case 4: {lm_rtedtls_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_rtedtls_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtedtls_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_rtedtls_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_rtedtls_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rtedtls_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtedtls_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_rtedtls_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_rtedtls_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rtedtls_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rtedtls_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtedtls_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_rtedtls_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_rtedtls_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rtedtls_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_rtedtls_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Route Code. \n"
            +   "2. Road/Street Code. \n"
            +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CountCd column values from lm_rtedtls_br table
                ResultSet rs = stmt.executeQuery("SELECT RteCd FROM lm_rtedtls_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("RteCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "RteCd values from lm_rtedtls_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No RteCd values found in lm_rtedtls_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CountCd column values from lm_rtedtls_br table
                ResultSet rs = stmt.executeQuery("SELECT StrRdCd FROM lm_rtedtls_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("StrRdCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "StrRdCd values from lm_rtedtls_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No CountName values found in lm_rtedtls_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
            
            case 3: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}
/*********************************** End of Route Details********************************************************************************************************************* */

/*********************************** Start of Vehicle Maintenance Companies ****************************************************************************************************/
public void lm_vehmntscomp_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_vehmntscomp_brAdd(); break;}
            case 2: {lm_vehmntscomp_brModify(); break;}
            case 3: {lm_vehmntscomp_brDelete(); break;}
            case 4: {lm_vehmntscomp_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_vehmntscomp_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_vehmntscomp_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_vehmntscomp_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_vehmntscomp_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_vehmntscomp_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_vehmntscomp_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_vehmntscomp_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_vehmntscomp_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_vehmntscomp_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_vehmntscomp_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_vehmntscomp_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_vehmntscomp_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_vehmntscomp_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_vehmntscomp_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_vehmntscomp_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Company Code. \n"
            +   "2. Company Name. \n"
            +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CompCd column values from lm_vehmntscomp_br table
                ResultSet rs = stmt.executeQuery("SELECT CompCd FROM lm_vehmntscomp_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("CompCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "CompCd values from lm_vehmntscomp_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No CompCd values found in lm_vehmntscomp_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CompName column values from lm_vehmntscomp_br table
                ResultSet rs = stmt.executeQuery("SELECT CompName FROM lm_vehmntscomp_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("CompName")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "CompName values from lm_vehmntscomp_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No CountName values found in lm_vehmntscomp_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
            
            case 3: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

/*********************************** End of Vehicle Maintenance Companies ****************************************************************************************************/

/*********************************** Start of Vehicle Maintenance Log ****************************************************************************************************/

public void lm_vehmaintlog_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_vehmaintlog_brAdd(); break;}
            case 2: {lm_vehmaintlog_brModify(); break;}
            case 3: {lm_vehmaintlog_brDelete(); break;}
            case 4: {lm_vehmaintlog_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_vehmaintlog_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_vehmaintlog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_vehmaintlog_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_vehmaintlog_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_vehmaintlog_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_vehmaintlog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_vehmaintlog_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_vehmaintlog_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_vehmaintlog_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_vehmaintlog_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_vehmaintlog_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_vehmaintlog_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_vehmaintlog_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_vehmaintlog_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_vehmaintlog_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Vehicle Maintenance Code. \n"
            +   "2. Vehicle Code. \n"
            +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CompCd column values from lm_vehmaintlog_br table
                ResultSet rs = stmt.executeQuery("SELECT VehMainCd FROM lm_vehmaintlog_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("VehMainCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "VehMainCd values from lm_vehmaintlog_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No VehMainCd values found in lm_vehmaintlog_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch VehCd column values from lm_vehmaintlog_br table
                ResultSet rs = stmt.executeQuery("SELECT VehCd FROM lm_vehmaintlog_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("VehCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "VehCd values from lm_vehmaintlog_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No VehCd values found in lm_vehmaintlog_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
            
            case 3: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

/*********************************** End of Vehicle Maintenance Log ****************************************************************************************************/

/*********************************** Start of Route Schedule ****************************************************************************************************/
public void lm_rtesch_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_rtesch_brAdd(); break;}
            case 2: {lm_rtesch_brModify(); break;}
            case 3: {lm_rtesch_brDelete(); break;}
            case 4: {lm_rtesch_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_rtesch_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtesch_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_rtesch_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_rtesch_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rtesch_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtesch_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_rtesch_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_rtesch_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rtesch_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rtesch_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rtesch_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_rtesch_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_rtesch_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rtesch_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_rtesch_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Route Schedule Code. \n"
            +   "2. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch CompCd column values from lm_rtesch_br table
                ResultSet rs = stmt.executeQuery("SELECT RteSchCd FROM lm_rtesch_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("RteSchCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "RteSchCd values from lm_rtesch_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No RteSchCd values found in lm_rtesch_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}
/*********************************** End of Route Schedule ****************************************************************************************************/

/*********************************** Start of Route Execution Logs ****************************************************************************************************/
public void lm_rteexlog_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_rteexlog_brAdd(); break;}
            case 2: {lm_rteexlog_brModify(); break;}
            case 3: {lm_rteexlog_brDelete(); break;}
            case 4: {lm_rteexlog_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_rteexlog_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rteexlog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_rteexlog_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_rteexlog_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rteexlog_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rteexlog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_rteexlog_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_rteexlog_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rteexlog_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rteexlog_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rteexlog_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_rteexlog_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_rteexlog_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rteexlog_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_rteexlog_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Route Schedule Code. \n"
            +   "2. Route Schedule Custodian Code. \n"
            +   "3. Route Execution Vehicle Code. \n"
            +   "4. Route Execution Assigned Route Code. \n"
            +   "5. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch RteSchCd column values from lm_rteexlog_br table
                ResultSet rs = stmt.executeQuery("SELECT RteSchCd FROM lm_rteexlog_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("RteSchCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "RteSchCd values from lm_rteexlog_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No RteSchCd values found in lm_rteexlog_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch RteCustCd column values from lm_rteexlog_br table
                ResultSet rs = stmt.executeQuery("SELECT RteCustCd FROM lm_rteexlog_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("RteCustCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "RteCustCd values from lm_rteexlog_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No RteCustCd values found in lm_rteexlog_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }
                case 3: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch RteVehCd column values from lm_rteexlog_br table
                    ResultSet rs = stmt.executeQuery("SELECT RteVehCd FROM lm_rteexlog_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("RteVehCd")).append("\n");
                    }
    
                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "RteVehCd values from lm_rteexlog_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No RteVehCd values found in lm_rteexlog_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                    case 4: {
                        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                                Statement stmt = conn.createStatement()) {
                        // Fetch RteSchAsRte column values from lm_rteexlog_br table
                        ResultSet rs = stmt.executeQuery("SELECT RteSchAsRte FROM lm_rteexlog_br");
                        StringBuilder countValues = new StringBuilder();
                        while (rs.next()) {
                            countValues.append(rs.getString("RteSchAsRte")).append("\n");
                        }
        
                        if (countValues.length() > 0) {
                            JOptionPane.showMessageDialog(null, "RteSchAsRte values from lm_rteexlog_br:\n" + countValues.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "No RteSchAsRte values found in lm_rteexlog_br");
                        }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        }
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}
/*********************************** End of Route Execution Logs ****************************************************************************************************/

/*********************************** Start of Road Incidents Log ****************************************************************************************************/

public void lm_rdinclog_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_rdinclog_brAdd(); break;}
            case 2: {lm_rdinclog_brModify(); break;}
            case 3: {lm_rdinclog_brDelete(); break;}
            case 4: {lm_rdinclog_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_rdinclog_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rdinclog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_rdinclog_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_rdinclog_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rdinclog_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rdinclog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_rdinclog_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_rdinclog_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rdinclog_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_rdinclog_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_rdinclog_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_rdinclog_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_rdinclog_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_rdinclog_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_rdinclog_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Incident Code. \n"
            +   "2. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch IncCd column values from lm_rdinclog_br table
                ResultSet rs = stmt.executeQuery("SELECT IncCd FROM lm_rdinclog_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("IncCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "IncCd values from lm_rdinclog_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No IncCd values found in lm_rdinclog_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

/*********************************** End of Road Incidents Log ****************************************************************************************************/

/*********************************** Start of FAQ****************************************************************************************************/

public void lm_faq_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Inquire. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {lm_faq_brAdd(); break;}
            case 2: {lm_faq_brModify(); break;}
            case 3: {lm_faq_brDelete(); break;}
            case 4: {lm_faq_brInquiry(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void lm_faq_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_faq_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO lm_faq_br (");
for (int i = 1; i <= columnCount; i++) {
    insertStatement.append(rsmd.getColumnName(i));
    if (i < columnCount) {
        insertStatement.append(", ");
    }
}
insertStatement.append(") VALUES (");
for (int i = 0; i < columnCount; i++) {
    insertStatement.append("'").append(inputData[i]).append("'");
    if (i < columnCount - 1) {
        insertStatement.append(", ");
    }
}
insertStatement.append(")");

// Execute the INSERT statement
stmt.executeUpdate(insertStatement.toString());

JOptionPane.showMessageDialog(null, "Data added successfully to lm_faq_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_faq_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_faq_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input the primary key value for modification
String primaryKey = JOptionPane.showInputDialog(null, "Enter the primary key value to modify:");

// Prompt the user to input the new values for each column
StringBuilder newValues = new StringBuilder();
for (int i = 1; i <= columnCount; i++) {
    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + rsmd.getColumnName(i) + ":");
    newValues.append(rsmd.getColumnName(i)).append(" = '").append(newValue).append("'");
    if (i < columnCount) {
        newValues.append(", ");
    }
}
// Construct the SQL UPDATE statement
String updateStatement = "UPDATE lm_faq_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in lm_faq_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_faq_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void lm_faq_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM lm_faq_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM lm_faq_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from lm_faq_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in lm_faq_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void lm_faq_brInquiry()
{
    int userOption;

    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Incident Code. \n"
            +   "2. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement()) {
                // Fetch QCd column values from lm_faq_br table
                ResultSet rs = stmt.executeQuery("SELECT QCd FROM lm_faq_br");
                StringBuilder countValues = new StringBuilder();
                while (rs.next()) {
                    countValues.append(rs.getString("QCd")).append("\n");
                }

                if (countValues.length() > 0) {
                    JOptionPane.showMessageDialog(null, "QCd values from lm_faq_br:\n" + countValues.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No QCd values found in lm_faq_br");
                }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
                }
            case 2: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

/*********************************** End of FAQ****************************************************************************************************/
}
