import java.util.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SCS {
    static Scanner JObject = new Scanner (System.in);
    static String input;
    final static String HEADING1 = "System Control System";
    final static String Inquiry = "Choose a value to Inquiry";
    static boolean exitTime = false;
    static final String DB_URL = "jdbc:mysql://localhost:3306/tms"; 
    static final String USER = "root";
    static final String PASS = "";

//******************* Start of Audit Add  ********************************************************************************************************************************************/

public void sc_AudAdd_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Display. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {sc_AudAdd_brAdd(); break;}
            case 2: {sc_AudAdd_brModify(); break;}
            case 3: {sc_AudAdd_brDelete(); break;}
            case 4: {sc_AudAdd_brDisplay(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void sc_AudAdd_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudAdd_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO sc_AudAdd_br (");
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

JOptionPane.showMessageDialog(null, "Data added successfully to sc_AudAdd_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_AudAdd_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudAdd_br").getMetaData();
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
String updateStatement = "UPDATE sc_AudAdd_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in sc_AudAdd_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_AudAdd_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_AudAdd_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudAdd_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM sc_AudAdd_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from sc_AudAdd_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_AudAdd_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void sc_AudAdd_brDisplay()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Fetch all columns from sc_AudAdd_br table
    ResultSet rs = stmt.executeQuery("SELECT * FROM sc_AudAdd_br");
    StringBuilder rowData = new StringBuilder();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String value = rs.getString(i);
            rowData.append(columnName).append(": ").append(value).append("\n");
        }
        rowData.append("\n");
    }

    if (rowData.length() > 0) {
        JOptionPane.showMessageDialog(null, "Data from sc_AudAdd_br:\n" + rowData.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No data found in sc_AudAdd_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
}
//******************* End of Audit Add  ********************************************************************************************************************************************/

//******************* Start of Audit Update  ********************************************************************************************************************************************/


public void sc_AudUpd_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Display. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {sc_AudUpd_brAdd(); break;}
            case 2: {sc_AudUpd_brModify(); break;}
            case 3: {sc_AudUpd_brDelete(); break;}
            case 4: {sc_AudUpd_brDisplay(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void sc_AudUpd_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudUpd_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO sc_AudUpd_br (");
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

JOptionPane.showMessageDialog(null, "Data added successfully to sc_AudUpd_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_AudUpd_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudUpd_br").getMetaData();
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
String updateStatement = "UPDATE sc_AudUpd_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in sc_AudUpd_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_AudUpd_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_AudUpd_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudUpd_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM sc_AudUpd_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from sc_AudUpd_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_AudUpd_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void sc_AudUpd_brDisplay()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Fetch all columns from sc_AudUpd_br table
    ResultSet rs = stmt.executeQuery("SELECT * FROM sc_AudUpd_br");
    StringBuilder rowData = new StringBuilder();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String value = rs.getString(i);
            rowData.append(columnName).append(": ").append(value).append("\n");
        }
        rowData.append("\n");
    }

    if (rowData.length() > 0) {
        JOptionPane.showMessageDialog(null, "Data from sc_AudUpd_br:\n" + rowData.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No data found in sc_AudUpd_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
}

//******************* End of Audit Update  ********************************************************************************************************************************************/

//******************* Start of Audit Delete  ********************************************************************************************************************************************/

public void sc_AudDel_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Display. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {sc_AudDel_brAdd(); break;}
            case 2: {sc_AudDel_brModify(); break;}
            case 3: {sc_AudDel_brDelete(); break;}
            case 4: {sc_AudDel_brDisplay(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void sc_AudDel_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudDel_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO sc_AudDel_br (");
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

JOptionPane.showMessageDialog(null, "Data added successfully to sc_AudDel_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_AudDel_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudDel_br").getMetaData();
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
String updateStatement = "UPDATE sc_AudDel_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in sc_AudDel_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_AudDel_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_AudDel_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_AudDel_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM sc_AudDel_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from sc_AudDel_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_AudDel_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void sc_AudDel_brDisplay()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Fetch all columns from sc_AudDel_br table
    ResultSet rs = stmt.executeQuery("SELECT * FROM sc_AudDel_br");
    StringBuilder rowData = new StringBuilder();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String value = rs.getString(i);
            rowData.append(columnName).append(": ").append(value).append("\n");
        }
        rowData.append("\n");
    }

    if (rowData.length() > 0) {
        JOptionPane.showMessageDialog(null, "Data from sc_AudDel_br:\n" + rowData.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No data found in sc_AudDel_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
}
//******************* End of Audit Delete  ********************************************************************************************************************************************/

//******************* Start of Session Log  ********************************************************************************************************************************************/

public void sc_SessLog_br()
{
    int userOption;

    //Give the User Options
    while(!exitTime)
    {
        input = JOptionPane.showInputDialog(null,
                "1. Add. \n"
            +   "2. Modify. \n"
            +   "3. Delete. \n"
            +   "4. Display. \n"
            +   "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input);
        switch (userOption)
        {
            case 1: {sc_SessLog_brAdd(); break;}
            case 2: {sc_SessLog_brModify(); break;}
            case 3: {sc_SessLog_brDelete(); break;}
            case 4: {sc_SessLog_brDisplay(); break;}
            case 5: {exitTime = true; break;}
        }//end of switch case
    }//end of while loop     
}

public void sc_SessLog_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_SessLog_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO sc_SessLog_br (");
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

JOptionPane.showMessageDialog(null, "Data added successfully to sc_SessLog_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_SessLog_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_SessLog_br").getMetaData();
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
String updateStatement = "UPDATE sc_SessLog_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in sc_SessLog_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_SessLog_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_SessLog_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_SessLog_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM sc_SessLog_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from sc_SessLog_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_SessLog_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void sc_SessLog_brDisplay()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Fetch all columns from sc_SessLog_br table
    ResultSet rs = stmt.executeQuery("SELECT * FROM sc_SessLog_br");
    StringBuilder rowData = new StringBuilder();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String value = rs.getString(i);
            rowData.append(columnName).append(": ").append(value).append("\n");
        }
        rowData.append("\n");
    }

    if (rowData.length() > 0) {
        JOptionPane.showMessageDialog(null, "Data from sc_SessLog_br:\n" + rowData.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No data found in sc_SessLog_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
}

//******************* End of Session Log  ********************************************************************************************************************************************/

//******************* Start of System User  ********************************************************************************************************************************************/

public void sc_SysUser_br()
{
    int userOption;

    //Give the User Options
    while (!exitTime) {
        String input = JOptionPane.showInputDialog(null,
                "1. Add\n"
                        + "2. Modify\n"
                        + "3. Delete\n"
                        + "4. Display\n"
                        + "5. Quit", HEADING1, JOptionPane.QUESTION_MESSAGE);
        userOption = Integer.parseInt(input.trim());
        switch (userOption) {
            case 1:
                sc_SysUser_brAdd();
                break;
            case 2:
                sc_SysUser_brModify();
                break;
            case 3:
                sc_SysUser_brDelete();
                break;
            case 4:
                sc_SysUser_brDisplay();
                break;
            case 5:
                exitTime = true;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid option.");
        }
    }//end of while loop     
}

public void sc_SysUser_brAdd()
{
try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_sysuser_br").getMetaData();
int columnCount = rsmd.getColumnCount();

// Prompt the user to input data for each column
String[] inputData = new String[columnCount];
for (int i = 1; i <= columnCount; i++) {
    inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
}

// Construct the SQL INSERT statement
StringBuilder insertStatement = new StringBuilder("INSERT INTO sc_sysuser_br (");
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

JOptionPane.showMessageDialog(null, "Data added successfully to sc_sysuser_br");
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_SysUser_brModify()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
 Statement stmt = conn.createStatement()) {
// Retrieve column names
ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_SysUser_br").getMetaData();
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
String updateStatement = "UPDATE sc_SysUser_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

// Execute the UPDATE statement
int rowsAffected = stmt.executeUpdate(updateStatement);
if (rowsAffected > 0) {
    JOptionPane.showMessageDialog(null, "Row updated successfully in sc_SysUser_br");
} else {
    JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_SysUser_br");
}
} catch (SQLException e) {
e.printStackTrace();
}
}

public void sc_SysUser_brDelete()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement()) {
   // Retrieve column names
   ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM sc_SysUser_br").getMetaData();
   int columnCount = rsmd.getColumnCount();

   // Prompt the user to input the primary key value for deletion
   String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");

   // Construct the SQL DELETE statement
   String deleteStatement = "DELETE FROM sc_SysUser_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

   // Execute the DELETE statement
   int rowsAffected = stmt.executeUpdate(deleteStatement);
   if (rowsAffected > 0) {
       JOptionPane.showMessageDialog(null, "Row deleted successfully from sc_SysUser_br");
   } else {
       JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in sc_SysUser_br");
   }
} catch (SQLException e) {
   e.printStackTrace();
}

}

public void sc_SysUser_brDisplay()
{
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Fetch all columns from sc_SysUser_br table
    ResultSet rs = stmt.executeQuery("SELECT * FROM sc_SysUser_br");
    StringBuilder rowData = new StringBuilder();
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();

    while (rs.next()) {
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            String value = rs.getString(i);
            rowData.append(columnName).append(": ").append(value).append("\n");
        }
        rowData.append("\n");
    }

    if (rowData.length() > 0) {
        JOptionPane.showMessageDialog(null, "Data from sc_SysUser_br:\n" + rowData.toString());
    } else {
        JOptionPane.showMessageDialog(null, "No data found in sc_SysUser_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
}

//******************* End of System User  ********************************************************************************************************************************************/
}
