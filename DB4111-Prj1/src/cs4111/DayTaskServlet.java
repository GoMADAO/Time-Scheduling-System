package cs4111;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs4111.bean.CourworkBean;
import cs4111.bean.DayTaskBean;
import cs4111.bean.MStoneBean;
import cs4111.bean.PlanBean;
import cs4111.model.Courwork;
import cs4111.model.DayTask;
import cs4111.model.MStoneList;
import cs4111.model.Plan;

/**
 * Servlet implementation class DayTaskServlet
 */
public class DayTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DayTaskServlet() {
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
		
		
		response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8"); 
        String cwid = (String)request.getParameter("TCid");
        
        CourworkBean cwb = new CourworkBean();
        Courwork cw= cwb.getACourwork(cwid);
        cwb.closeDBconn();
        
        PlanBean pb = new PlanBean();
        Plan pl = new Plan();
        pl = pb.getPlan(cwid);
        pb.closeDBconn();
        
        MStoneList msl = new MStoneList();
        MStoneBean msb = new MStoneBean();
        msl = msb.getMStoneList(pl.getPlanid().toString());
        msb.closeDBconn();
		DayTaskBean dtb = new DayTaskBean(); 
        //String TCheck = request.getParameter("TCheck");

        String Tplan = request.getParameter("Tplan");
        String Ttask = request.getParameter("Ttask");
        String Tcontent = request.getParameter("Tcontent");
        String Tspentime = request.getParameter("Tspentime");
        String Tweight =  request.getParameter("Tweight");
        String Tstatus =  request.getParameter("Tstatus");
        
        DayTask uptoday = new DayTask();
        uptoday.setPlanid(Integer.parseInt(Tplan));
        uptoday.setTaskid(Integer.parseInt(Ttask));
        uptoday.setSpend(Integer.parseInt(Tspentime));
        uptoday.setContent(Tcontent);
        uptoday.setWeight(Integer.parseInt(Tweight));
        uptoday.setStatus(Tstatus);
        
        dtb.upDayTask(uptoday);
            
        DayTask today = new DayTask();
        today = dtb.getDayTask(uptoday.getPlanid(), "today");
        DayTask tomor = new DayTask();
        tomor = dtb.getDayTask(uptoday.getPlanid(), "tomorrow");
        dtb.closeDBconn(); 
        
        String forward = new String("Plan.jsp");
        request.setAttribute("plan", pl);
        request.setAttribute("courwork",cw);
        request.setAttribute("mstonelist", msl);
        request.setAttribute("cwid", cwid);
        request.setAttribute("today", today);
        request.setAttribute("tomor", tomor);
        RequestDispatcher rd=request.getRequestDispatcher(forward);
        rd.forward(request,response);
        
	}

}