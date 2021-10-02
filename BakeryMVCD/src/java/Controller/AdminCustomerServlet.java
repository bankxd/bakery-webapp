package Controller;

import com.bakerysystem.Model.Cart;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Ingredient;
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

@WebServlet("/admincustomer")
public class AdminCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String custupdbut = (String) request.getParameter("custupdbut");
        switch (custupdbut) {
            case "Update":
                String cidu = (String) request.getParameter("custupdate");
                Customer customeru = new AccountsClient().getAccount(Integer.parseInt(cidu));

                session.setAttribute("selectedCust", customeru);
                request.getRequestDispatcher("adminupdate_customer.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String custbuttonaction = (String) request.getParameter("custbut");
        switch (custbuttonaction) {
            case "Update":
                Customer customeru = (Customer) session.getAttribute("selectedCust");
                customeru.setFirstName((String) request.getParameter("fnupd"));
                customeru.setLastName((String) request.getParameter("lnupd"));
                customeru.setTelephonehome((String) request.getParameter("thupd"));
                customeru.setTelephonemobile((String) request.getParameter("tmupd"));
                customeru.setID((String) request.getParameter("idnupd"));
                customeru.setAddressId((String) request.getParameter("addrupd"));
                customeru.setEmail((String) request.getParameter("emupd"));
                customeru.setPassword((String) request.getParameter("pwupd"));

                request.setAttribute("selectedCust", customeru);
                String upd = new AccountsClient().updateDetails(customeru);
                session.setAttribute("updcustresult", upd);
                request.getRequestDispatcher("admin_customersrm.jsp").forward(request, response);
                break;
            case "Remove":
                String cidd = (String) request.getParameter("custdelete");
                String custdelresult = new AccountsClient().remove(Integer.parseInt(cidd));

                session.setAttribute("custdelresult", custdelresult);
                request.getRequestDispatcher("admin_customersrm.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
