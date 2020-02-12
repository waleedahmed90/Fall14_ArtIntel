
public class courses {
	String name;
	String Pre_req_1;
	String Pre_req_2;
	boolean pass;
	
	
	courses(String n, String p1, String p2, boolean p){
		this.name=n;
		this.Pre_req_1=p1;
		this.Pre_req_2=p2;
		this.pass=p;
	}
	
	void printCourses(){
		
		if(this.pass){
			System.out.println(this.name+" (pass)");
		}else{
			System.out.println(this.name+" (fail)");
		}
		
	}
}
