package Controller;

import com.bakerysystem.Model.Cart;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Product;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.client.AccountsClient;
import com.bakerysystem.client.ProductsClient;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String custdetupdbut = (String) request.getParameter("custdetupdbut");
        switch (custdetupdbut) {
            case "Update":
                String custidu = (String) request.getParameter("custID");
                Customer customeru = new AccountsClient().getAccount(Integer.parseInt(custidu));

                session.setAttribute("selectedCust", customeru);
                request.getRequestDispatcher("account_editinfo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String updcustbut = (String) request.getParameter("updcustbut");
        switch (updcustbut) {
            case "Update":
                Customer customer = (Customer) session.getAttribute("selectedCust");

                customer.setFirstName(request.getParameter("fna"));
                customer.setLastName(request.getParameter("lna"));
                customer.setEmail(request.getParameter("uea"));
                customer.setTelephonehome(request.getParameter("cna"));
                customer.setTelephonemobile(request.getParameter("cnaa"));
                customer.setAddressId(request.getParameter("daa"));

                String result = new AccountsClient().updateDetails(customer);

                session.setAttribute("custupdresult", result);
                session.setAttribute("logcust", customer);
                request.getRequestDispatcher("account_details.jsp").forward(request, response);
        }
    }
}
