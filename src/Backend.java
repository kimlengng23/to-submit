

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Backend
 */
@WebServlet("/Backend")
public class Backend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Backend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:3306/expense?verifyServerCertificate=false&useSSL=true";
		String username="root";
		String password="112233";
		PrintWriter pw = response.getWriter();
		try {
			Connection database = DriverManager.getConnection(url, username, password);
			Statement statement = database.createStatement();
			String dateOfExpense = request.getParameter("txt-date");
			String amount = request.getParameter("txt-amount");
			String reason = request.getParameter("txt-reason");
			String query = "Insert into expense(dateOfExp,amount,description) values("+dateOfExpense+","+amount+",\""+reason+"\")";
			statement.executeQuery(query);
			statement.close();
			database.close();
			pw.write("Successfully added!");
			response.sendRedirect("expense-add-page.html");
		}
		catch (SQLException e){
			while (e != null) {
                System.out.println("SQL Error:  " + e.getMessage());
                e = e.getNextException();
            }
			pw.write("Failed!");
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
