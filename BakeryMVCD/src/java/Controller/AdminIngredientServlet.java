package Controller;

import com.bakerysystem.Model.Cart;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.Model.Product;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.client.AccountsClient;
import com.bakerysystem.client.IngredientsClient;
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

@WebServlet("/adminingredient")
public class AdminIngredientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ingupdbut = (String) request.getParameter("ingupdbut");
        switch (ingupdbut) {
            case "Update":
                String ingidu = (String) request.getParameter("ingupdate");
                Ingredient ingredientu = new IngredientsClient().getIngredient(Integer.parseInt(ingidu));

                session.setAttribute("selectedIng", ingredientu);
                request.getRequestDispatcher("adminupdate_ingredient.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buttonaction = (String) request.getParameter("ingbut");
        switch (buttonaction) {
            case "Add Ingredient":
                Ingredient ingredientc = new Ingredient((String) request.getParameter("ingname"), (int) Integer.parseInt(request.getParameter("ingquan")),(String) request.getParameter("uom"));
                String add = new IngredientsClient().addIngredient(ingredientc);

                session.setAttribute("addingresult", add);
                request.getRequestDispatcher("admin_ingredientsrm.jsp").forward(request, response);
                break;
            case "Update":
                Ingredient ingredientu = (Ingredient) session.getAttribute("selectedIng");
                ingredientu.setIngredientName(request.getParameter("ingnameupd"));
                ingredientu.setQuantity((int)Integer.parseInt((String)request.getParameter("ingquanupd")));
                ingredientu.setUnitOfMeasurement((String) request.getParameter("uom"));
                
                session.setAttribute("selectedIng", ingredientu);
                String upd = new IngredientsClient().updateDetails(ingredientu);
                session.setAttribute("updingresult", upd);
                request.getRequestDispatcher("admin_ingredientsrm.jsp").forward(request, response);
                break;
            case "Remove":
                String idel = (String) request.getParameter("ingdelete");
                String ingdelresult = new IngredientsClient().remove(Integer.parseInt(idel));

                session.setAttribute("ingdelresult", ingdelresult);
                request.getRequestDispatcher("admin_ingredientsrm.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
