package Banco;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/CadastroServlet")
public class Cadastro extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    	String nome = request.getParameter("nome");
    	String senha = request.getParameter("senha");
        String foto = request.getParameter("foto");        
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        
        

        try {
            // Carrega o driver JDBC do Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Obtém a conexão usando a classe Conexao
            Connection conexao = Conexao.obterConexao();

            // Insira os dados na tabela de usuários
            String sql = "INSERT INTO TbUsuario (nome, senha, foto, email, telefone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                pstmt.setString(1, nome);
                pstmt.setString(2, senha);
                pstmt.setString(3, foto);
                pstmt.setString(4, email);
                pstmt.setString(5, telefone);
                pstmt.executeUpdate();
            }

            // Feche a conexão
            conexao.close();

            // Exiba uma mensagem de sucesso no navegador
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Cadastro realizado com sucesso:</h2>");
            out.println("<p>Nome: " + nome + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("</body></html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar o cadastro");
        }
    }
}
