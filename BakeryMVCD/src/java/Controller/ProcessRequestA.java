package Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ProcessRequestA {

    public abstract void processTheRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    protected abstract void sendResponseToServer(HttpServletResponse response, String jsonString);

    protected abstract void getResponseFromServer() throws MalformedURLException, ProtocolException, IOException;

    protected abstract void sendResponseToController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}