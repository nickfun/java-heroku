import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class Name extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
	String name = req.getParameter("name");
	if( name == null ) {
	    name = "--NO NAME--";
	}
	resp.getWriter().print("Hello, " + name + "!");
	System.out.println("Name: wrote " + name);
    }

    public Name() {
	System.out.println("init: NAME()");
    }
}
