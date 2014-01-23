import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

import com.google.code.gson.*

public class Test extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
	throws ServletException, IOException
    {
	Obj t = new Obj();
	t.setName("Nick");
	t.setAge(12);
       	//resp.getWriter().print("Obj: " + t);
	resp.setHeader("Content-Type", "application/json");
	Gson jsonWriter = new Gson();
	resp.getWriter().print(  jsonWriter.toJson(t) );
    }

    public Test() {
	System.out.println("INIT: Test");
    }

}

