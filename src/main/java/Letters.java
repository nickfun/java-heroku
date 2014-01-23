import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class Letters extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
	String letters = req.getParameter("letters");
	String output = "No Input :-( <br> /letters?letters=hello";
	if( letters != null ) {
	    output = "";
	    for( char c in letters ) {
		output += " :letter" + c;
	    }
	}
	resp.getWriter.print(output);
    }

    public Letters() {
	System.out.println("init: LETTERS()");
    }
}


	    
