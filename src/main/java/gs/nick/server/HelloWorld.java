package gs.nick.server;

/**
 * Simple Java Server example
 * For deployment on HEROKU
 *
 * Nick F - 2014-01-21
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class HelloWorld extends HttpServlet {
   
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
        resp.getWriter().print("Hello from Java!\n");
    }

    public HelloWorld() {
	System.out.println("Init: HelloWorld");
    }

}
