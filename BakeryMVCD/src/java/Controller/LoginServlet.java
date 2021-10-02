package Controller;

import com.bakerysystem.Model.Admin;
import com.bakerysystem.Model.Cart;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.Model.User;
import com.bakerysystem.client.AccountsClient;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String em = (String) request.getParameter("em");
        String pw = (String) request.getParameter("pw");

        Customer logcust = (Customer) new AccountsClient().login(em, pw);
        Cart cart = new Cart(logcust.getCustomerId(), new ArrayList<ProductLineItem>());

        int custid = (int) logcust.getCustomerId();
        if (logcust != null) {
            if (custid == 1) {
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", new Admin());
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else if (custid > 1) {
                HttpSession session = request.getSession(true);
                session.setAttribute("logcust", logcust);
                session.setAttribute("cart", cart);
                request.getRequestDispatcher("shop4.jsp").forward(request, response);
            }
        } else if (logcust == null) {
            request.getRequestDispatcher("failedlogin.jsp").forward(request, response);
        }
//        char[] otp = (char[]) request.getAttribute("otp");
//        if (otp.equals(pw)) {
//            if (custid == 1) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("admin", new Admin());
//                request.getRequestDispatcher("admin.jsp").forward(request, response);
//            } else if (custid > 1) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("logcust", logcust);
//                session.setAttribute("cart", cart);
//                request.getRequestDispatcher("shop4.jsp").forward(request, response);
//            }
//        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

//            if (logcust != null) {
//    User user = new User();
//user.getUserType ();
//            if (user.getUserType().equals(a)) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("admin", new Admin());
//                request.getRequestDispatcher("admin.jsp").forward(request, response);
//            } else if (user.getUserType().equals(c)) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("logcust", logcust);
//                session.setAttribute("cart", cart);
//                request.getRequestDispatcher("shop4.jsp").forward(request, response);
//            }
//        }
//        if (logcust != null) {
//            User user = new User();
//            if (user.getUserType().equals("ADMIN")) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("admin", new Admin());
//                request.getRequestDispatcher("admin.jsp").forward(request, response);
//
//            } else if (user.getUserType().equals("CUSTOMER")) {
//                HttpSession session = request.getSession(true);
//                session.setAttribute("logcust", logcust);
//                session.setAttribute("cart", cart);
//                request.getRequestDispatcher("shop4.jsp").forward(request, response);
//            }
//        }
