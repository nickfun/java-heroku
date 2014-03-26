
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

public class MyServer extends HttpServlet {
   
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
	context.addServlet(new ServletHolder(new SystemList()), "/systems");
        server.start();
        server.join();
    }
}
