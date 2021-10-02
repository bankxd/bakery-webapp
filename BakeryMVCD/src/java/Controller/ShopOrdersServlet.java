package Controller;

import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Order;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.Notifier.NotificationSystem;
import com.bakerysystem.client.OrdersClient;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkout")
public class ShopOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String ingupdbut = (String) request.getParameter("ingupdbut");
//        switch (ingupdbut) {
//            case "Update":
//                String ingidu = (String) request.getParameter("ingupdate");
//                Ingredient ingredientu = new IngredientsClient().getIngredient(Integer.parseInt(ingidu));
//
//                session.setAttribute("selectedIng", ingredientu);
//                request.getRequestDispatcher("adminupdate_ingredient.jsp").forward(request, response);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("logcust");
        String shopordbut = (String) request.getParameter("shopordbut");
        switch (shopordbut) {
            case "Confirm Order":
                request.getRequestDispatcher("purchase_confirmdetails.jsp").forward(request, response);
                break;
            case "Make Payment":
//                int randomNum = (int) ((Math.random() * 4) + 1);
//                if (randomNum < 3) {
                Order order = (Order) session.getAttribute("order");
                Order orderc = new Order((double) order.getTotalPrice(), (int) customer.getCustomerId(),
                        customer.getAddressId(), "Preparing", "Paid");
                orderc.setOrderLineArr((ArrayList<ProductLineItem>) session.getAttribute("CartArr"));
                String add = new OrdersClient().addOrder(orderc);
                if (add.equalsIgnoreCase("SUCCESSFUL!")) {
                    session.setAttribute("latestOrder", orderc);
                    session.setAttribute("addordresult", add);
                    Customer cust = (Customer) session.getAttribute("logcust");
                    //new NotificationSystem().sendGmail(cust.getEmail(), "DoughKnot Online Bakery store Order Placed", "Hey " + cust.getFirstName() + " you have successfully placed an order for R" + orderc.getTotalPrice());
                    request.getRequestDispatcher("purchase_successful.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("purchase_failed.jsp").forward(request, response);
                }
                break;
        }
    }
}
