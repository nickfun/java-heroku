package gs.nick.server;

/**
 * Simple Java Server example For deployment on HEROKU
 *
 * Nick F - 2014-01-21
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        System.err.println(">>> This is a test of stderr <<<");
        
        // register shutdown hook
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("XXX APPLICATION IS ENDING XXX");
                System.out.println("XXX APPLICATION IS ENDING XXX");
                System.out.println("XXX APPLICATION IS ENDING XXX");
                try {
                    mainThread.join();
                } catch (InterruptedException ex) {
                    System.err.println("EXCEPTION IN ShutdownHook !!! ");
                }
            }
        });
        // done processing
        server.join();
    }
}
