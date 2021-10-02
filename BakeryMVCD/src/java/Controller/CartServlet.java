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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    
    ProductLineItem pli;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cartaddbut = (String) request.getParameter("cartaddbut");
        switch (cartaddbut) {
            case "Add to Cart":
                String prodID = (String) request.getParameter("prodID");
                Product retprod = new ProductsClient().recieveProduct((int) Integer.parseInt(prodID));
                pli = new ProductLineItem(retprod.getProductID(), retprod.getProductName(), 1, (double) retprod.getPrice());

                Cart cartA = (Cart) session.getAttribute("cart");
                ArrayList<ProductLineItem> cartArrA = cartA.getProducts();
                cartArrA.add(pli);

                session.setAttribute("CartArr", cartArrA);
                request.getRequestDispatcher("shop4.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cartbuttonaction = (String) request.getParameter("cartbut");
        switch (cartbuttonaction) {
            case "Remove":
                String cartdeleteID = (String) request.getParameter("cartdeleteID");
                int cartdeleteIndex = (int) Integer.parseInt((String) request.getParameter("cartdeleteIndex"));
                Product retprod = new ProductsClient().recieveProduct((int) Integer.parseInt(cartdeleteID));
                pli = new ProductLineItem((int) retprod.getProductID(), (String) retprod.getProductName(), 1, (double) retprod.getPrice());
                
                Cart cartD = (Cart) session.getAttribute("cart");
                ArrayList<ProductLineItem> cartArrD = cartD.getProducts();
                cartArrD.remove(cartdeleteIndex);
                
                session.setAttribute("cart", cartD);
                request.getRequestDispatcher("account_cart.jsp").forward(request, response);
        }
    }
}
