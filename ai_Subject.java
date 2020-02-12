public class Subject {
	String name;
	boolean pass;

	Subject(String n, boolean p) {
		this.name = n;
		this.pass = p;
	}

	void printSubject() {
		
		System.out.print("Subject-Code: " + this.name);
		
		
		if(this.pass){
			System.out.println("(Pass)");
		}else{
			System.out.println("(Fail)");
		}
		
	}
}
