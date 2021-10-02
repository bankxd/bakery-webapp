package Controller;

import com.bakerysystem.Model.Order;
import com.bakerysystem.client.OrdersClient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminorder")
public class AdminOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ordupdbut = (String) request.getParameter("ordupdbut");
        switch (ordupdbut) {
            case "Update":
                String ordidu = (String) request.getParameter("orderupdate");
                Order orderu = new OrdersClient().getOrder(Integer.parseInt(ordidu));

                session.setAttribute("selectedOrd", orderu);
                request.getRequestDispatcher("adminupdate_order.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buttonaction = (String) request.getParameter("ordbut");
        switch (buttonaction) {
            case "Update":
                Order ordersu = (Order) session.getAttribute("selectedOrd");
                ordersu.setOrderStatus(request.getParameter("ordstatus"));

                session.setAttribute("selectedOrd", ordersu);
                String upd = new OrdersClient().editOrder(ordersu);
                session.setAttribute("updordresult", upd);
                request.getRequestDispatcher("admin_ordersrm.jsp").forward(request, response);
                break;
            case "Remove":
                String odel = (String) request.getParameter("orderdelete");
                String orddelresult = new OrdersClient().remove(Integer.parseInt(odel));

                session.setAttribute("orddelresult", orddelresult);
                request.getRequestDispatcher("admin_ordersrm.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
