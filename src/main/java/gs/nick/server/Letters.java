package gs.nick.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class Letters extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
	String letters = req.getParameter("letters");
	String output = "No Input :-( <br> /letters?letters=hello or anything else";
	if( letters != null ) {
	    letters = letters.toLowerCase();
	    output = "";
	    char c;
	    int i;
	    for( i=0; i<letters.length(); i++ ) {
		c = letters.charAt(i);
		output += " :letter" + c;
	    }
	}
	resp.setHeader("Content-Type","text/html");
	resp.getWriter().print(output);
	System.out.println("letters: wrote " + letters);
    }

    public Letters() {
	System.out.println("init: LETTERS()");
    }
}


	    
