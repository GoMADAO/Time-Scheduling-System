package cs4111.bean;

public class Stubean {
	private String username;
	private String password;
	private String name;
	
	public void setName(String name){
		this.username = name;
	}
	public void setPwd(String pwd){
		this.password = pwd;
	}
	public void setStuName(String stuName){
		this.name = stuName;
	}
	public String getName(){
		return this.username;
	}
	public String getPwd(){
		return this.password;
	}
	public String getStuName(){
		return this.name;
	}
}
