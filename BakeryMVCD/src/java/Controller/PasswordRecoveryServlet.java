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

@WebServlet("/recovery")
public class PasswordRecoveryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        HttpSession session = request.getSession();
        String em = request.getParameter("ue");

        String retstring = new AccountsClient().recoverAccount(em);
        session.setAttribute("pwrret", retstring);
        NotificationSystem ns = new NotificationSystem();
        char[] otp = ns.generateOTP(9);
        ns.sendGmail(em, "Password Recovery", "Your One Time Pin is : " + otp + ". Use it to log in now and reset your password.");
        request.setAttribute("otp", otp);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
