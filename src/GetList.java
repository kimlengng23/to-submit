

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GetList
 */
@WebServlet("/GetList")
public class GetList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetList() {
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
			
			String query = "Select * from expense";
			ResultSet result = statement.executeQuery(query);
			JsonArray jsonArray = new JsonArray();
			while(result.next())
			{
				String date = result.getString("dateOfExp");
                String amount = result.getString("amount");
                String reason = result.getString("description");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("date", date);
                jsonObject.addProperty("amount", amount);
                jsonObject.addProperty("reason", reason);
                jsonArray.add(jsonObject);
			}
			pw.write(jsonArray.toString());
			response.setStatus(200);
			result.close();
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
