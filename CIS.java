import java.util.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class CIS {
    static Scanner JObject = new Scanner (System.in);
    static String input;
    final static String HEADING1 = "Control Information System";
    final static String Inquiry = "Choose a value to Inquiry";
    static boolean exitTime = false;
    static final String DB_URL = "jdbc:mysql://localhost:3306/tms"; 
    static final String USER = "root";
    static final String PASS = "";

//******************* Start of Countries  ********************************************************************************************************************************************/
    
    public void ci_count_br()
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
                case 1: {ci_count_brAdd(); break;}
                case 2: {ci_count_brModify(); break;}
                case 3: {ci_count_brDelete(); break;}
                case 4: {ci_count_brInquiry(); break;}
                case 5: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     
    }

    public void ci_count_brAdd()
    {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_count_br").getMetaData();
    int columnCount = rsmd.getColumnCount();

    // Prompt the user to input data for each column
    String[] inputData = new String[columnCount];
    for (int i = 1; i <= columnCount; i++) {
        inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
    }

    // Construct the SQL INSERT statement
    StringBuilder insertStatement = new StringBuilder("INSERT INTO ci_count_br (");
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

    JOptionPane.showMessageDialog(null, "Data added successfully to ci_count_br");
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_count_brModify()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_count_br").getMetaData();
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
    String updateStatement = "UPDATE ci_count_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

    // Execute the UPDATE statement
    int rowsAffected = stmt.executeUpdate(updateStatement);
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Row updated successfully in ci_count_br");
    } else {
        JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_count_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_count_brDelete()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()) {
       // Retrieve column names
       ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_count_br").getMetaData();
       int columnCount = rsmd.getColumnCount();
   
       // Prompt the user to input the primary key value for deletion
       String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");
   
       // Construct the SQL DELETE statement
       String deleteStatement = "DELETE FROM ci_count_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";
   
       // Execute the DELETE statement
       int rowsAffected = stmt.executeUpdate(deleteStatement);
       if (rowsAffected > 0) {
           JOptionPane.showMessageDialog(null, "Row deleted successfully from ci_count_br");
       } else {
           JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_count_br");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
   
    }

    public void ci_count_brInquiry()
    {
        int userOption;

        while(!exitTime)
        {
            input = JOptionPane.showInputDialog(null,
                    "1. Country Code. \n"
                +   "2. Country Name. \n"
                +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            switch (userOption)
            {
                case 1: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch CountCd column values from ci_count_br table
                    ResultSet rs = stmt.executeQuery("SELECT CountCd FROM ci_count_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("CountCd")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CountCd values from ci_count_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CountCd values found in ci_count_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    }
                case 2: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch CountCd column values from ci_count_br table
                    ResultSet rs = stmt.executeQuery("SELECT CountName FROM ci_count_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("CountName")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CountName values from ci_count_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CountName values found in ci_count_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                
                case 3: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     


    }
    
//******************* End of Countries  **********************************************************************************************************************************************/

//******************* Start of Locations  *******************************************************************************************************************************************/

    public void ci_loc_br()
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
                case 1: {ci_loc_brAdd(); break;}
                case 2: {ci_loc_brModify(); break;}
                case 3: {ci_loc_brDelete(); break;}
                case 4: {ci_loc_brInquiry(); break;}
                case 5: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     
    }

    public void ci_loc_brAdd()
    {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_loc_br").getMetaData();
    int columnCount = rsmd.getColumnCount();

    // Prompt the user to input data for each column
    String[] inputData = new String[columnCount];
    for (int i = 1; i <= columnCount; i++) {
        inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
    }

    // Construct the SQL INSERT statement
    StringBuilder insertStatement = new StringBuilder("INSERT INTO ci_loc_br (");
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

    JOptionPane.showMessageDialog(null, "Data added successfully to ci_loc_br");
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_loc_brModify()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_loc_br").getMetaData();
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
    String updateStatement = "UPDATE ci_loc_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

    // Execute the UPDATE statement
    int rowsAffected = stmt.executeUpdate(updateStatement);
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Row updated successfully in ci_loc_br");
    } else {
        JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_loc_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_loc_brDelete()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()) {
       // Retrieve column names
       ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_loc_br").getMetaData();
       int columnCount = rsmd.getColumnCount();
   
       // Prompt the user to input the primary key value for deletion
       String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");
   
       // Construct the SQL DELETE statement
       String deleteStatement = "DELETE FROM ci_loc_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";
   
       // Execute the DELETE statement
       int rowsAffected = stmt.executeUpdate(deleteStatement);
       if (rowsAffected > 0) {
           JOptionPane.showMessageDialog(null, "Row deleted successfully from ci_loc_br");
       } else {
           JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_loc_br");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    public void ci_loc_brInquiry()
    {
        int userOption;

        while(!exitTime)
        {
            input = JOptionPane.showInputDialog(null,
                    "1. Location Code. \n"
                +   "2. Location Name. \n"
                +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            switch (userOption)
            {
                case 1: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch CountCd column values from ci_loc_br table
                    ResultSet rs = stmt.executeQuery("SELECT LocCd FROM ci_loc_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("LocCd")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "LocCd values from ci_loc_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CountCd values found in ci_loc_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    }
                case 2: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch CountCd column values from ci_loc_br table
                    ResultSet rs = stmt.executeQuery("SELECT CountName FROM ci_loc_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("LocName")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CountName values from ci_loc_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CountName values found in ci_loc_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                
                case 3: {exitTime = true; break;}
            }
            }//end of switch case
        }//end of while loop     
    
    //******************* End of Locations  *****************************************************************************************************************************************/

    //******************* Province/States  ******************************************************************************************************************************************/
        public void ci_prov_br()
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
                case 1: {ci_prov_brAdd(); break;}
                case 2: {ci_prov_brModify(); break;}
                case 3: {ci_prov_brDelete(); break;}
                case 4: {ci_prov_brInquiry(); break;}
                case 5: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     
    }

    public void ci_prov_brAdd()
    {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_prov_br").getMetaData();
    int columnCount = rsmd.getColumnCount();

    // Prompt the user to input data for each column
    String[] inputData = new String[columnCount];
    for (int i = 1; i <= columnCount; i++) {
        inputData[i - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(i) + ":");
    }

    // Construct the SQL INSERT statement
    StringBuilder insertStatement = new StringBuilder("INSERT INTO ci_prov_br (");
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

    JOptionPane.showMessageDialog(null, "Data added successfully to ci_prov_br");
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_prov_brModify()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_prov_br").getMetaData();
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
    String updateStatement = "UPDATE ci_prov_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

    // Execute the UPDATE statement
    int rowsAffected = stmt.executeUpdate(updateStatement);
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Row updated successfully in ci_prov_br");
    } else {
        JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_prov_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_prov_brDelete()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()) {
       // Retrieve column names
       ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_prov_br").getMetaData();
       int columnCount = rsmd.getColumnCount();
   
       // Prompt the user to input the primary key value for deletion
       String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");
   
       // Construct the SQL DELETE statement
       String deleteStatement = "DELETE FROM ci_prov_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";
   
       // Execute the DELETE statement
       int rowsAffected = stmt.executeUpdate(deleteStatement);
       if (rowsAffected > 0) {
           JOptionPane.showMessageDialog(null, "Row deleted successfully from ci_prov_br");
       } else {
           JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_prov_br");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    public void ci_prov_brInquiry()
    {
        int userOption;

        while(!exitTime)
        {
            input = JOptionPane.showInputDialog(null,
                    "1. Province Code. \n"
                +   "2. Province Name. \n"
                +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            switch (userOption)
            {
                case 1: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch ProvCd column values from ci_prov_br table
                    ResultSet rs = stmt.executeQuery("SELECT ProvCd FROM ci_prov_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("ProvCd")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "ProvCd values from ci_prov_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No ProvCd values found in ci_prov_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    }
                case 2: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch ProvCd column values from ci_prov_br table
                    ResultSet rs = stmt.executeQuery("SELECT CountName FROM ci_prov_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("ProvName")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CountName values from ci_prov_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CountName values found in ci_prov_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                
                case 3: {exitTime = true; break;}
            }
            }//end of switch case
        }//end of while loop 

//***********************************************************************End Of Province/States ************************************************************************************/

//***********************************************************************Start Of Streets and Roads*********************************************************************************/
    public void ci_strrd_br()
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
                case 1: {ci_strrd_brAdd(); break;}
                case 2: {ci_strrd_brModify(); break;}
                case 3: {ci_strrd_brDelete(); break;}
                case 4: {ci_strrd_brInquiry(); break;}
                case 5: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     
    }

    public void ci_strrd_brAdd()
    {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_strrd_br").getMetaData();
    int columnCount = rsmd.getColumnCount();

    // Prompt the user to input data for each column
    String[] inputData = new String[columnCount];
    for (int d = 1; d <= columnCount; d++) {
        inputData[d - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(d) + ":");
    }

    // Construct the SQL INSERT statement
    StringBuilder insertStatement = new StringBuilder("INSERT INTO ci_strrd_br (");
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

    JOptionPane.showMessageDialog(null, "Data added successfully to ci_strrd_br");
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_strrd_brModify()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_strrd_br").getMetaData();
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
    String updateStatement = "UPDATE ci_strrd_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

    // Execute the UPDATE statement
    int rowsAffected = stmt.executeUpdate(updateStatement);
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Row updated successfully in ci_strrd_br");
    } else {
        JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_strrd_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_strrd_brDelete()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()) {
       // Retrieve column names
       ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_strrd_br").getMetaData();
       int columnCount = rsmd.getColumnCount();
   
       // Prompt the user to input the primary key value for deletion
       String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");
   
       // Construct the SQL DELETE statement
       String deleteStatement = "DELETE FROM ci_strrd_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";
   
       // Execute the DELETE statement
       int rowsAffected = stmt.executeUpdate(deleteStatement);
       if (rowsAffected > 0) {
           JOptionPane.showMessageDialog(null, "Row deleted successfully from ci_strrd_br");
       } else {
           JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_strrd_br");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    public void ci_strrd_brInquiry()
    {
        int userOption;

        while(!exitTime)
        {
            input = JOptionPane.showInputDialog(null,
                    "1. Street Code. \n"
                +   "2. Street Name. \n"
                +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            switch (userOption)
            {
                case 1: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch StrRdCd column values from ci_strrd_br table
                    ResultSet rs = stmt.executeQuery("SELECT StrRdCd FROM ci_strrd_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("StrRdCd")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "StrRdCd values from ci_strrd_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No StrRdCd values found in ci_strrd_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    }
                case 2: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch StrRdCd column values from ci_strrd_br table
                    ResultSet rs = stmt.executeQuery("SELECT StrRdName FROM ci_strrd_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("StrRdName")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CountName values from ci_strrd_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CountName values found in ci_strrd_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                
                case 3: {exitTime = true; break;}
            }
            }//end of switch case
        }//end of while loop 


//***********************************************************************End Of Streets and Roads*********************************************************************************/

//***********************************************************************Start Of Transportation Custodian************************************************************************/
    public void ci_transcust_br()
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
                case 1: {ci_transcust_brAdd(); break;}
                case 2: {ci_transcust_brModify(); break;}
                case 3: {ci_transcust_brDelete(); break;}
                case 4: {ci_transcust_brInquiry(); break;}
                case 5: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     
    }

    public void ci_transcust_brAdd()
    {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_transcust_br").getMetaData();
    int columnCount = rsmd.getColumnCount();

    // Prompt the user to input data for each column
    String[] inputData = new String[columnCount];
    for (int d = 1; d <= columnCount; d++) {
        inputData[d - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(d) + ":");
    }

    // Construct the SQL INSERT statement
    StringBuilder insertStatement = new StringBuilder("INSERT INTO ci_transcust_br (");
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

    JOptionPane.showMessageDialog(null, "Data added successfully to ci_transcust_br");
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_transcust_brModify()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_transcust_br").getMetaData();
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
    String updateStatement = "UPDATE ci_transcust_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

    // Execute the UPDATE statement
    int rowsAffected = stmt.executeUpdate(updateStatement);
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Row updated successfully in ci_transcust_br");
    } else {
        JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_transcust_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_transcust_brDelete()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()) {
       // Retrieve column names
       ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_transcust_br").getMetaData();
       int columnCount = rsmd.getColumnCount();
   
       // Prompt the user to input the primary key value for deletion
       String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");
   
       // Construct the SQL DELETE statement
       String deleteStatement = "DELETE FROM ci_transcust_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";
   
       // Execute the DELETE statement
       int rowsAffected = stmt.executeUpdate(deleteStatement);
       if (rowsAffected > 0) {
           JOptionPane.showMessageDialog(null, "Row deleted successfully from ci_transcust_br");
       } else {
           JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_transcust_br");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    public void ci_transcust_brInquiry()
    {
        int userOption;

        while(!exitTime)
        {
            input = JOptionPane.showInputDialog(null,
                    "1. Custodian Code. \n"
                +   "2. Custodian Name. \n"
                +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            switch (userOption)
            {
                case 1: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch CustCd column values from ci_transcust_br table
                    ResultSet rs = stmt.executeQuery("SELECT CustCd FROM ci_transcust_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("CustCd")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CustCd values from ci_transcust_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CustCd values found in ci_transcust_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    }
                case 2: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch CustFName column values from ci_transcust_br table
                    ResultSet rs = stmt.executeQuery("SELECT CustFName FROM ci_transcust_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("CustFName")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "CustFName values from ci_transcust_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CustFName values found in ci_transcust_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                case 3: {exitTime = true; break;}
            }
            }//end of switch case
        }//end of while loop 

//***********************************************************************End Of Transportation Custodian************************************************************************/

//***********************************************************************Start Of Transportation Vehicle************************************************************************/
    public void ci_transveh_br()
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
                case 1: {ci_transveh_brAdd(); break;}
                case 2: {ci_transveh_brModify(); break;}
                case 3: {ci_transveh_brDelete(); break;}
                case 4: {ci_transveh_brInquiry(); break;}
                case 5: {exitTime = true; break;}
            }//end of switch case
        }//end of while loop     
    }

    public void ci_transveh_brAdd()
    {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_transveh_br").getMetaData();
    int columnCount = rsmd.getColumnCount();

    // Prompt the user to input data for each column
    String[] inputData = new String[columnCount];
    for (int d = 1; d <= columnCount; d++) {
        inputData[d - 1] = JOptionPane.showInputDialog(null, "Enter value for " + rsmd.getColumnName(d) + ":");
    }

    // Construct the SQL INSERT statement
    StringBuilder insertStatement = new StringBuilder("INSERT INTO ci_transveh_br (");
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

    JOptionPane.showMessageDialog(null, "Data added successfully to ci_transveh_br");
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_transveh_brModify()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
     Statement stmt = conn.createStatement()) {
    // Retrieve column names
    ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_transveh_br").getMetaData();
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
    String updateStatement = "UPDATE ci_transveh_br SET " + newValues + " WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";

    // Execute the UPDATE statement
    int rowsAffected = stmt.executeUpdate(updateStatement);
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(null, "Row updated successfully in ci_transveh_br");
    } else {
        JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_transveh_br");
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }

    public void ci_transveh_brDelete()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()) {
       // Retrieve column names
       ResultSetMetaData rsmd = stmt.executeQuery("SELECT * FROM ci_transveh_br").getMetaData();
       int columnCount = rsmd.getColumnCount();
   
       // Prompt the user to input the primary key value for deletion
       String primaryKey = JOptionPane.showInputDialog(null, "Enter the code to delete:");
   
       // Construct the SQL DELETE statement
       String deleteStatement = "DELETE FROM ci_transveh_br WHERE " + rsmd.getColumnName(1) + " = '" + primaryKey + "'";
   
       // Execute the DELETE statement
       int rowsAffected = stmt.executeUpdate(deleteStatement);
       if (rowsAffected > 0) {
           JOptionPane.showMessageDialog(null, "Row deleted successfully from ci_transveh_br");
       } else {
           JOptionPane.showMessageDialog(null, "No row with primary key '" + primaryKey + "' found in ci_transveh_br");
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    public void ci_transveh_brInquiry()
    {
        int userOption;

        while(!exitTime)
        {
            input = JOptionPane.showInputDialog(null,
                    "1. Vehicle Code. \n"
                +   "2. Vehicle Descriptive Name. \n"
                +   "3. Quit", Inquiry, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            switch (userOption)
            {
                case 1: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch VehCd column values from ci_transveh_br table
                    ResultSet rs = stmt.executeQuery("SELECT VehCd FROM ci_transveh_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("VehCd")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "VehCd values from ci_transveh_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No CustCd values found in ci_transveh_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                    }
                case 2: {
                    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            Statement stmt = conn.createStatement()) {
                    // Fetch VehDescNameName column values from ci_transveh_br table
                    ResultSet rs = stmt.executeQuery("SELECT VehDescName FROM ci_transveh_br");
                    StringBuilder countValues = new StringBuilder();
                    while (rs.next()) {
                        countValues.append(rs.getString("VehDescName")).append("\n");
                    }

                    if (countValues.length() > 0) {
                        JOptionPane.showMessageDialog(null, "VehDescName values from ci_transveh_br:\n" + countValues.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No VehDescName values found in ci_transveh_br");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    }
                case 3: {exitTime = true; break;}
            }
            }//end of switch case
        }//end of while loop 

//***********************************************************************End Of Transportation Vehicle***************************************************************************/
}