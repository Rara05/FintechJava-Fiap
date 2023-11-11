package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USUARIO = "RM552119";
    private static final String SENHA = "270998";

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        
        } catch (ClassNotFoundException e) {
          
        	throw new SQLException("Erro ao carregar o driver JDBC: " + e.getMessage());
        }
    }
}
