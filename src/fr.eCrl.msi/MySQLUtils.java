/* KHN - 17/11/2022 - RIP - MySQLUtils*/

package fr.eCrl.msi;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.StringJoiner;

public class MySQLUtils {
    /**
     * Prints a MySQl response wrapped in an RS
     * @param rs ResultSet wrapping a response
     * @throws SQLException
     */
    static void printRequest(ResultSet rs) throws SQLException {
        ResultSet rs2 = rs;
        ResultSetMetaData metaData = rs2.getMetaData();
        int columnNumber = metaData.getColumnCount();
        StringJoiner sj = new StringJoiner(" | ");
        for (int i = 1; i <= columnNumber; i++)
        {
            sj.add(metaData.getColumnName(i));
        }
        System.out.println(sj);
        StringJoiner lines = new StringJoiner("\n");
        while (rs2.next())
        {
            sj = new StringJoiner( " | ");
            for (int i = 1; i <= columnNumber; i++) {
                sj.add(rs2.getString(i));
            }
            lines.add(sj.toString());
        }
        System.out.println(lines);
    }

}
