
public class Writeback  {
	Memory m;
	public Writeback(Memory m){
		this.m=m;
		switch(m.e.d.opcode){
		case "0111": //j();
		case "1000": //jal();
		case "1001": //jr();
		case "1011": //rj();
		case "0100": //sw();
		case "1111": //beq()
			break;
		case "1100": //lins();
			m.linsStore();
			
		case "0000": //add();
		case "0001": //addi();
		case "0010": //sub();
		case "0011": //lw();
		case "0101": //and();
		case "0110": //xor();
		case "1110": //slt()
		case "1101": //nor();
			
			m.e.d.set(m.e.d.destination, m.e.d.rd); break;
			
		case "1010": //nlj(); //sp=sp+1
			m.e.d.set("0011", m.e.d.rd);break;
		}
		System.out.println(m.e.d.f.instructionString+" WriteBack phase done");
	}

}