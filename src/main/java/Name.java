import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class Name extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
	resp.getWriter().print("Hello, NAME!");
    }
}
