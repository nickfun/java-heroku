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
    public static void main(String[] args) throws Exception{
	Integer port = Integer.valueOf(System.getenv("PORT"));
	int iPort = 9999;
	System.out.println("ENV PORT:");
	System.out.println(port);

        Server server = new Server( iPort );
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()),"/*");
        server.start();
        server.join();
    }
}
