import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

public class Test extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
	throws ServletException, IOException
    {
	Obj t = new Obj();
	t.setName("Nick");
	t.setAge(12);
	resp.getWriter().print("Obj: " + t);
    }

    public Test() {
	System.out.println("INIT: Test");
    }

}

