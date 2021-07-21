public class Memory {
	
	static short [] memory= new short[100];
	Execute e;
	Memory(Execute e){
		this.e=e;	
//		memory[9]=18;
		if(e.read&&!e.write) {
			load();
			e.read=false;
		}
		else if(e.write&&!e.read) {
			store();
			e.write=false;
		}
		else if(e.write&&e.read) {
			linsLoad();
		}
		System.out.println(e.d.f.instructionString+" Memory phase done");
	} 
	 void linsLoad() {
		//little adder after memory
//		 System.out.println("targetCell: "+e.targetCell);
		if(e.targetCell>=memory.length) 
			System.out.println("not available word");
		else {
			e.d.rd=(short) (memory[e.targetCell]+1);
		}
	}
	void linsStore() {
		memory[e.targetCell]=e.d.rd;
	}
	private void store() {
		if(e.targetCell>=memory.length) 
			System.out.println("not available word");
		else {
			if(e.d.opcode.equals("1010"))
				//non leaf jump
				memory[e.targetCell]=e.d.$ra;
			else {
				System.out.println("the value: "+e.d.getRegister(e.d.destination));
				memory[e.targetCell]=e.d.getRegister(e.d.destination) ;
			}
		}
	}
	private void load() {
		if(e.targetCell>=memory.length) 
			System.out.println("not available word");
		else {
			e.d.rd=memory[e.targetCell];
		}
	}
	
}