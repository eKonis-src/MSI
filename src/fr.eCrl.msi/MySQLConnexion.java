/*KHN - 18/10/2022 - RIP - MySQLConnexion*/

package fr.eCrl.msi;

import java.io.IOException;
import java.sql.*;

public final class MySQLConnexion {

    private final Connection con;

    private final Statement stmt;



    private MySQLConnexion(String databaseUrl, String user, String password){
        try{
            con = connect(databaseUrl, user, password);
            con.setAutoCommit(false);
            stmt = con.createStatement();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tries to connect to the database
     * @return the connection
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private Connection connect(String databaseUrl, String user,String password) throws IOException, SQLException, ClassNotFoundException {
        try {
            Connection con = DriverManager.getConnection(
                    databaseUrl, user, password);
            return con;
        } catch (Exception e) {
            throw(e);
        }
    }

    /**
     * Executes an MySQLQuery
     * @param query The Query to execute
     * @param print Boolean to print the query
     * @return The ResultSet wrapping the query answer
     * @throws SQLException
     */
    public ResultSet execute(MySQLQuery query, boolean print) throws SQLException {
        if (query == null)
            throw new NullPointerException("Query must be non-null.");
        ResultSet rs=stmt.executeQuery(query.compose());
        if (print)
            MySQLUtils.printRequest(rs);
        con.commit();
        return rs;
    }

    /**
     * Executes an update query.
     * @param query The query to execute
     * @throws SQLException
     */
    public void executeUpdate(String query) throws SQLException{
        stmt.executeUpdate(query);
        con.commit();
    }

    /**
     * Executes an update query.
     * @param query The query to execute
     * @throws SQLException
     */
    public void executeUpdate(MySQLQuery query) throws SQLException {
        stmt.executeUpdate(query.compose());
        con.commit();
    }

    /**
     * Add a query to the stack pile
     * @param query
     * @return
     * @throws SQLException
     */
    public MySQLConnexion stack(MySQLQuery query) throws  SQLException {
        String q = query.compose();
        if (q.contains("SELECT"))
            stmt.execute(q);
        else
            stmt.executeUpdate(q);
        return this;
    }

    /**
     * Commits the stack of queries
     * @throws SQLException
     */
    public void commitStack() throws SQLException {
        con.commit();
    }
}
