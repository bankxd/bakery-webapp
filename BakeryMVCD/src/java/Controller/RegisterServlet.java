package Controller;

import com.bakerysystem.Model.Customer;
import com.bakerysystem.Notifier.NotificationSystem;
import com.bakerysystem.client.AccountsClient;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        HttpSession session = request.getSession();

        String fn = request.getParameter("firstName");
        String ln = request.getParameter("lastName");
        String email = request.getParameter("email");
        String contact = request.getParameter("telephonemobile");
        String pw = request.getParameter("password");

        Customer regcust = new Customer(fn, ln, email, "", contact, "", "", pw);

        session.setAttribute("regcust", regcust);

        Customer c = new AccountsClient().register(regcust);
        new NotificationSystem().sendGmail(c.getEmail(), "Successfully Registered!", 
                "Welcome " + c.getFirstName() + "! You have been successfully registered with The Dough Knot online delivery system.");
        request.getRequestDispatcher("shop4.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
