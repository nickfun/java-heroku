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
        int port = Integer.parseInt(System.getenv("PORT"));
	int iPort = 9999;

	System.out.println("ENV PORT:" + port );
	//System.out.println(port);

        Server server = new Server( port );
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()), "/*");
	context.addServlet(new ServletHolder(new Name()), "/name");
	context.addServlet(new ServletHolder(new Letters()), "/letters");
	context.addServlet(new ServletHolder(new Test()), "/test");
        server.start();
        server.join();
    }
}
