package Controller;

import com.bakerysystem.Model.Cart;
import com.bakerysystem.Model.Category;
import com.bakerysystem.Model.Customer;
import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.Model.Product;
import com.bakerysystem.Model.ProductLineItem;
import com.bakerysystem.client.AccountsClient;
import com.bakerysystem.client.CategoriesClient;
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

@WebServlet("/admincategory")
public class AdminCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String catupdbut = (String) request.getParameter("catupdbut");
        switch (catupdbut) {
            case "Update":
                String cidu = (String) request.getParameter("catupdate");
                Category categoryu = new CategoriesClient().getACategory(Integer.parseInt(cidu));

                session.setAttribute("selectedCat", categoryu);
                request.getRequestDispatcher("adminupdate_category.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String buttonaction = (String) request.getParameter("catbut");
        switch (buttonaction) {
            case "Add Category":
                Category categoryc = new Category((String) request.getParameter("catname"));
                String add = new CategoriesClient().addCategory(categoryc);

                session.setAttribute("addcatresult", add);
                request.getRequestDispatcher("admin_categoriesrm.jsp").forward(request, response);
                break;
            case "Update":
                Category categoryu = (Category) session.getAttribute("selectedCat");
                categoryu.setCategoryName(request.getParameter("catnameupd"));

                session.setAttribute("selectedCat", categoryu);
                String upd = new CategoriesClient().updateCategory(categoryu);
                request.setAttribute("updcatresult", upd);
                request.getRequestDispatcher("admin_categoriesrm.jsp").forward(request, response);
                break;
            case "Remove":
                String cdel = (String) request.getParameter("catdelete");
                String catdelresult = new CategoriesClient().deleteCategory(Integer.parseInt(cdel));

                session.setAttribute("catdelresult", catdelresult);
                request.getRequestDispatcher("admin_categoriesrm.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
