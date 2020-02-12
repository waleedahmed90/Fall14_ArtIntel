
public class Course {
	String Name;
	String Credit;
	String Pre_req_1;
	String Pre_req_2;
	boolean Fall;
	boolean Spring;
	
	
	Course(String n, String c, String p1, String p2, String f, String s){
		this.Name=n;
		this.Credit=c;
		this.Pre_req_1=p1;
		this.Pre_req_2=p2;
		
		if(f.equals("F"))
			this.Fall=true;
		else
			this.Fall=false;
		
		if(s.equals("S")){
			this.Spring=true;
		}else{
			this.Spring=false;
		}
		
	}
	
	void PrintCourse(){
		System.out.println("Course-Code: "+this.Name);
		System.out.println("Credit Hours: "+this.Credit);
		
		//the pre_requisites
		if(this.Pre_req_1.equals("NULL") && this.Pre_req_2.equals("NULL")){
			System.out.println("Pre-Requisites: None");
		}else if(this.Pre_req_1.equals("NULL")){
			System.out.println("Pre-Requisites: "+ this.Pre_req_2);
		}else if(this.Pre_req_2.equals("NULL")){
			System.out.println("Pre-Requisites: "+ this.Pre_req_1);
		}else{
			System.out.println("Pre-Requisites: "+ this.Pre_req_1+", "+this.Pre_req_2);
		}
		
		
		//session
		if(this.Fall && this.Spring){
			System.out.println("Sessions Offerred: Spring/Fall");
		}else if(this.Spring){
			System.out.println("Sessions Offerred: Spring");
		}else if(this.Fall){
			System.out.println("Sessions Offerred: Fall");
		}else{
			System.out.println("Sessions Offerred: None");
		}
	}
}
