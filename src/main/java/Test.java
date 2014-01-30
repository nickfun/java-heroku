import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.servlet.*;

import com.google.gson.*;

public class Test extends HttpServlet {

    protected void doGet( HttpServletRequest req, HttpServletResponse resp )
	throws ServletException, IOException
    {
	Obj t = new Obj();
	String name = this.getParam("name","Default Name", req);
	String age = getParam("age","-99999",req);
	t.setName(name);
	try {
	    t.setAge(Integer.parseInt(age));
	} catch( Exception e ) {
	    t.setAge(-9999);
	}
       	//resp.getWriter().print("Obj: " + t);
	resp.setHeader("Content-Type", "application/json");
	Gson jsonWriter = new Gson();
	resp.getWriter().print(  jsonWriter.toJson(t) );
	System.out.println("Writing: " + name + " / " + age);
    }

    private String getParam( String name, String fallback, HttpServletRequest req ) {
	String result = req.getParameter(name);
	if( result == null ) {
	    return fallback;
	}
	return result;
    }

    public Test() {
	System.out.println("INIT: Test");
    }

}

