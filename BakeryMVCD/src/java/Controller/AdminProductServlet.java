package Controller;

import com.bakerysystem.Model.Ingredient;
import com.bakerysystem.Model.Product;
import com.bakerysystem.Services.ProductsClientService;
import com.bakerysystem.client.IngredientsClient;
import com.bakerysystem.client.ProductsClient;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/adminproduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AdminProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String produpdbut = (String) request.getParameter("produpdbut");
        switch (produpdbut) {
            case "Update":
                String prodidu = (String) request.getParameter("produpdateID");
                Product productu = new ProductsClient().recieveProduct(Integer.parseInt(prodidu));

                session.setAttribute("selectedProd", productu);
                request.getRequestDispatcher("adminupdate_product.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String prodbutaction = (String) request.getParameter("prodbut");
        switch (prodbutaction) {
            case "Add Product": {
                String UPLOAD_DIRECTORY = "C:\\Users\\daves\\Documents\\NetBeansProjects\\BakeryTues\\BakeryMVCD\\web\\Images\\";
                Part fileToUpload = request.getPart("prodphoto");
                String filename = Paths.get(fileToUpload.getName()).getFileName().toString();
                filename = (String) request.getParameter("prodname");

                System.out.println(filename);
                File file = new File(UPLOAD_DIRECTORY + filename + ".jpg");
                if (!file.exists()) {
                    file.createNewFile();
                }
                try (InputStream input = fileToUpload.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                int catid = (int) (Integer.parseInt((String) request.getParameter("catradio")));

                Product productc = new Product((String) request.getParameter("prodname"),
                        ((String) request.getParameter("prodphotoName") + ".jpg"),
                        (String) request.getParameter("proddesc"), (String) request.getParameter("prodwar"),
                        (double) (Double.parseDouble((String) request.getParameter("prodprice"))),
                        (int) (Integer.parseInt((String) request.getParameter("proddisc"))),
                        catid);

                productc.setRecipeArr(new ArrayList<>());
                String[] ings = (String[]) request.getParameterValues("ingaddID");
                String[] ingquant = (String[]) request.getParameterValues("quanaddname");
                ArrayList<String> temp = new ArrayList<>();
                for (String val : ingquant) {
                    if (!val.equals("")) {
                        temp.add(val);
                    }
                }
                
                for (int i = 0; i < ings.length; i++) {
                    Ingredient addIng = (Ingredient) new IngredientsClient().getIngredient(Integer.parseInt(ings[i]));
                    addIng.setQuantity(Integer.parseInt(temp.get(i)));
                    productc.getRecipeArr().add(addIng);
                }

                String add = new ProductsClientService().addProduct(productc, file);
                //String add = new ProductsClient().addProduct(productc);
                session.setAttribute("addprodresult", add);
                request.getRequestDispatcher("admin_productsrm.jsp").forward(request, response);
                break;
            }
            case "Update":
                Product productu = (Product) session.getAttribute("selectedProd");
                productu.setProductName(request.getParameter("prodnameupd"));
                productu.setProductDescription((String) request.getParameter("proddescupd"));
                productu.setProductWarnings((String) request.getParameter("pwupd"));
                productu.setPrice((double) Double.parseDouble((String) request.getParameter("prodpriceupd")));
                productu.setDiscount((int) Integer.parseInt((String) request.getParameter("proddiscupd")));
                productu.setCategoryID((int) (Integer.parseInt((String) request.getParameter("cat"))));

                productu.setRecipeArr(new ArrayList<>());
                String[] ingss = (String[]) request.getParameterValues("prodingupd");
                String[] ingquantt = (String[]) request.getParameterValues("prodingquan");
                for (int i = 0; i < ingss.length; i++) {
                    Ingredient addIng = (Ingredient) new IngredientsClient().getIngredient(Integer.parseInt(ingss[i]));
                    addIng.setQuantity(Integer.parseInt(ingquantt[i]));
                    productu.getRecipeArr().add(addIng);
                }

                session.setAttribute("selectedProd", productu);
                String upd = new ProductsClient().updateProduct(productu);
                session.setAttribute("updprodresult", upd);
                request.getRequestDispatcher("admin_productsrm.jsp").forward(request, response);
                break;
            case "Remove":
                String pidd = (String) request.getParameter("proddelete");
                String proddelresult = new ProductsClient().removeProduct(Integer.parseInt(pidd));

                session.setAttribute("prodremresult", proddelresult);
                request.getRequestDispatcher("admin_productsrm.jsp").forward(request, response);
        }
    }
}

//		        if(ServletFileUpload.isMultipartContent(request)){
//		            try {
//		                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//		                for(FileItem item : multiparts){
//		                    if(!item.isFormField()){
//		                        String name = new File(item.getName()).getName();
//		                        item.write( new File("c:/guru/upload" + File.separator + name));
//		                    }
//		                }
//@MultipartConfig
//@WebServlet("/uploadServlet")
//public class UploadServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Part file = request.getPart("file");
//        String filename = getFilename(file);
//        InputStream filecontent = file.getInputStream();
//        // ... Do your file saving job here.
//
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write("File " + filename + " successfully uploaded");
//    }
//
//    private static String getFilename(Part part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
//            }
//        }
//        return null;
//    }
//}
//                case "Add Product":
//                Boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//                if (!isMultipart) {
//                    out.println("File not Uploaded.");
//                    request.getRequestDispatcher("admin_productsrm.jsp").forward(request, response);
//                } else {
//                    DiskFileItemFactory factory = new DiskFileItemFactory();
//
//                    ServletContext servletContext = this.getServletConfig().getServletContext();
//                    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//                    factory.setRepository(repository);
//
//                    ServletFileUpload upload = new ServletFileUpload(factory);
//                    List<FileItem> items = upload.parseRequest((RequestContext) request);
//                    Iterator<FileItem> iter = items.iterator();
//                    while (iter.hasNext()) {
//                        FileItem item = iter.next();
//                        if (!item.isFormField()) {
//                            if (!item.isFormField()) {
//                                String fieldName = item.getFieldName();
//                                String fileName = item.getName();
//                                String contentType = item.getContentType();
//                                boolean isInMemory = item.isInMemory();
//                                long sizeInBytes = item.getSize();
//
//                                Part filePart = request.getPart("picture");
//                                System.out.println(filePart.getContentType());
//
//                            }
//                        }
//                    }
//                }
