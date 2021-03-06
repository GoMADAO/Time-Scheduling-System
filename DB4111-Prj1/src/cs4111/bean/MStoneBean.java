package cs4111.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cs4111.model.MStone;
import cs4111.model.MStoneList;
import cs4111.util.DBConn;

public class MStoneBean {
	public DBConn conn;
	
	public MStoneBean(){
		conn = new DBConn();
	}
	public DBConn getCon(){
		return this.conn;
	}
	public void closeDBconn(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MStoneList getMStoneList(String planid){
		ResultSet rs = null;
		String sql = new String("SELECT * FROM mstone WHERE plan_id ="+planid+" ORDER BY milestone_id");
		MStoneList msl = new MStoneList();
		ArrayList<MStone> msarr = new ArrayList<MStone>();
		conn.getConn();
		rs= conn.doSelect(sql);
		try {
			while(rs.next()){
				MStone ms = new MStone();
				ms.setMstoneid(Integer.parseInt(rs.getString("milestone_id")));
				ms.setStatus(rs.getString("status"));
				ms.setDesc(rs.getString("milestone_desc"));
				ms.setTimesmodif(Integer.parseInt(rs.getString("times_of_modif")));
				ms.setWeight(Integer.parseInt(rs.getString("weight")));
				ms.setDeadline(rs.getDate("deadline"));
				msarr.add(ms);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msl.setMStoneList(msarr);
		return msl;
	}
	public void upMstone(String mstoneid, String weight, String desc, String modif){
		System.out.printf(modif);
		String sql = "update mstone set weight = "+weight+", MILESTONE_DESC='"+desc
				+"', TIMES_OF_MODIF="+ modif+" where MILESTONE_ID = "+mstoneid;
		System.out.println(sql);
		conn.getConn();
		conn.doUpdate(sql);
	}
	
	public MStoneList newMstone(String planid, String deadline, String weight, String desc){
		String[] ddl = deadline.split("\\,");
		String dayMon = ddl[0]; String spcYear = ddl[1];
		String year = spcYear.split("\\ ")[1];
		String[] dm = dayMon.split("\\ ");
		String day = dm[0]; String mon = dm[1];
		if (day.length()==1){
			day = "0"+day;
		}
		weight = weight.split("\\%")[0];
		String sql = new String("INSERT INTO mstone(plan_id,times_of_modif,milestone_desc,weight,status,deadline) "
				+ "VALUES ("+planid+",0,'"+desc+"',"+weight+",62,to_date('"+mon+"-"+day+"-"+year+"','MM-DD-YY'))");
		//String sql = new String("INSERT INTO mstone(plan_id,times_of_modif,milestone_desc,weight,status,deadline) "
		//		+ "VALUES(3,0,'test',20,62,to_date('02-November-2014','DD-MONTH-YYYY'))");
		System.out.print(sql);
		conn.getConn();
		conn.doInsert(sql);
		sql = null;
		//sql = "{? = call getmstoneseq()}";
		//String msid=conn.doFunction(sql);
		ResultSet rs = null;
		
		sql = "SELECT * FROM mstone WHERE plan_id ="+planid+" ORDER BY milestone_id";
		System.out.print(sql);
		MStoneList msl = new MStoneList();
		ArrayList<MStone> msarr = new ArrayList<MStone>();
		rs= conn.doSelect(sql);
		try {
			while(rs.next()){
				MStone ms = new MStone();
				ms.setMstoneid(Integer.parseInt(rs.getString("milestone_id")));
				ms.setStatus(rs.getString("status"));
				ms.setDesc(rs.getString("milestone_desc"));
				ms.setTimesmodif(Integer.parseInt(rs.getString("times_of_modif")));
				ms.setWeight(Integer.parseInt(rs.getString("weight")));
				ms.setDeadline(rs.getDate("deadline"));
				msarr.add(ms);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msl.setMStoneList(msarr);
		return msl;
	}
	

	
//	public static void main(String[] args){
//		MStoneBean msb =new MStoneBean();
//		String mstoneid="69";
//		msb.upMstone( mstoneid,  "25",  "reading");
//	}
}
