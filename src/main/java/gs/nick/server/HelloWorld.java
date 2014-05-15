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

/**
 * This handles the root resources on the server
 */
public class HelloWorld extends HttpServlet {
   
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	String msg = 
	    "\n HelloWorld  /*" +
	    "\n Name  /name" +
	    "\n Letters  /letters" +
	    "\n Test  /test" +
	    "\n SystemList  /systems";
	String output = "<html><body><h1>Hello welcome to <code>server</code></h1><pre>" + msg + "</pre></body></html";
	resp.getWriter().print(output);
	System.out.println("served /");
    }

    public HelloWorld() {
	System.out.println("Init: HelloWorld");
    }

}
