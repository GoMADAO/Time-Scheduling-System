package cs4111;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs4111.bean.ChapterReqBean;
import cs4111.bean.CourseReqBean;
import cs4111.bean.CourworkBean;
import cs4111.bean.ProfTeachesReqBean;
import cs4111.bean.TAassistsReqBean;
import cs4111.model.ChapterList;
import cs4111.model.Course;
import cs4111.model.CourseList;
import cs4111.model.Courwork;
import cs4111.model.CourworkList;
import cs4111.model.ProfTeachesList;
import cs4111.model.TAassistsList;

/**
 * Servlet implementation class CourseServlet
 */
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8"); 
        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("stuid");
        String crsid=(String)request.getParameter("courseid");
        
        TAassistsList ta = new TAassistsList();
        TAassistsReqBean assists = new TAassistsReqBean();
        assists.assists(crsid, ta);
        assists.closeDBconn();
        
        ProfTeachesList prof = new ProfTeachesList();
        ProfTeachesReqBean teaches = new ProfTeachesReqBean();
        teaches.teaches(crsid, prof);
        teaches.closeDBconn();
        
        ChapterList chap = new ChapterList();
        ChapterReqBean chapb = new ChapterReqBean();
        chapb.getChap(crsid, chap);
        chapb.closeDBconn();
        
        CourworkList cwl=new CourworkList();
        CourworkBean cwb = new CourworkBean();
        cwb.reqCouwork(crsid, cwl);
        cwb.closeDBconn();
        
        String forward = new String("Course.jsp");
        request.setAttribute("ta",ta);
        request.setAttribute("prof",prof);
        request.setAttribute("chap",chap);
        request.setAttribute("cw",cwl);
        RequestDispatcher rd=request.getRequestDispatcher(forward);
        rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
