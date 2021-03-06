package cs4111;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs4111.bean.statBean;

/**
 * Servlet implementation class statServlet
 */
public class statServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public statServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8");   
        String cwid=(String)request.getParameter("cwid");  
        String stuid=(String)request.getParameter("stuid");
        String plid=(String)request.getParameter("plid");
//        cwid = "4111";
//        stuid="yf2338";
//        plid="7";
        statBean stb = new statBean();
        String TotalTime = stb.getTotalTime(cwid, stuid);
        String Modif = stb.getModif(stuid, plid);
        String NumofReg = stb.getNumofReg(cwid);
        String NumofPlan = stb.getNumofPlan(cwid);
        String AvgProg = stb.getAvgProg(cwid);
        stb.closeDBconn();
        
        request.setAttribute("TotalTime", TotalTime);
        request.setAttribute("Modif", Modif);
        request.setAttribute("NumofReg", NumofReg);
        request.setAttribute("NumofPlan", NumofPlan);
        request.setAttribute("AvgProg", AvgProg);
        String forward = "statistics.jsp";
        RequestDispatcher rd=request.getRequestDispatcher(forward);
        rd.forward(request,response);  
	}

}
