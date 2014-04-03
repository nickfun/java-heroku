
/**
 * Simple Java Server example For deployment on HEROKU
 *
 * Nick F - 2014-01-21
 */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

public class MyServer extends HttpServlet {

    public static void main(String[] args) throws Exception {
        int port;
        String suppliedPort = System.getenv("PORT");
        if( suppliedPort == null ) {
            port = 5000;
            System.out.println("XXX No value for PORT, using 5000 by default");
        } else {
            port = Integer.parseInt(suppliedPort);
        }
        
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()), "/*");
        context.addServlet(new ServletHolder(new Name()), "/name");
        context.addServlet(new ServletHolder(new Letters()), "/letters");
        context.addServlet(new ServletHolder(new Test()), "/test");
        context.addServlet(new ServletHolder(new SystemList()), "/systems");
        server.start();
        System.out.println("=== Server has started on port " + port);
        server.join();
    }
}
