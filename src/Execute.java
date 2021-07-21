import java.util.ConcurrentModificationException;

public class Execute {
	Decode d;
	short targetCell;
	boolean read=false;
	boolean write=false;
	boolean jump=false;
	
	public Execute( Decode d)
	{
		this.d=d;
		System.out.println(d.opcode);
		switch(d.opcode)
		{
		case "0000": add();break;
		case "0001": addi();break;
		case "0010": sub();break;
		case "0011": lw(); break;
		case "0100": sw();break;
		case "0101": and();break;
		case "0110": xor();break;
		case "0111": j();break;
		case "1000": jal();break;
		case "1001": jr();break;
		case "1010": nlj();break; //non-leaf jump
		case "1011": rj();break;  //return jump
		case "1100": lins();break; //load increment store
		case "1101": nor();break;
		case "1110": slt();break;
		case "1111": beq();break;
		default: ;System.out.println("Instruction not found");
		
		}
		System.out.println(d.f.instructionString+" Instruction Executed");
	}
	void add() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		d.rd=(short)(r1+r2);
	}
	void addi() {
		short r1=d.getRegister(d.destImm);
		short immediate=(short) Integer.parseInt(d.imm,2);
		System.out.println("register: "+r1+" immediate "+immediate);
		d.rd=(short) (r1+immediate);
		System.out.println(d.rd);
	}
	void xor() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		d.rd=(short)(r1^r2);
	}
	void sub() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		d.rd=(short)(r1-r2);
	}
	void and() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		d.rd=(short) (r1&r2);
	}
	void j() {
		short jumpImmediate=(short) Integer.parseInt(d.regj,2);
		System.out.println("Execute pcCopy: "+d.$pcCopy);
		d.$pc=(short) (d.$pcCopy+jumpImmediate);
		jump=true;
	}
	void jal() {
		d.$ra=d.$pcCopy;
		j();
	}
	void jr() {
		short reg=d.getRegister(d.destination);
		d.$pc=reg;
		jump=true;
	}
	void nor() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		d.rd=(short) (~(r1|r2));
	}
	void slt() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		if(r1<r2) 
			d.rd=1;
	}
	void beq() {
		short r1=d.getRegister(d.reg1);
		short r2=d.getRegister(d.reg2);
		short dest=d.getRegister(d.destination);
		if(r1==r2) {
			d.$pc=dest;
			jump=true;
		}
	}
	void rj() {
		d.$pc=d.$ra;
		jump=true;
	}
	void nlj() {
		d.rd=(short) (d.$sp+1);
		targetCell=d.rd;
		write=true;
		read=false;
		jal();
	}
	void lw() {
		short offset = d.getRegister(d.reg1);
		short base = d.getRegister(d.reg2); 
		targetCell = (short)(offset+base);
		write=false;
		read=true ;
	}
	void sw() {
		short offset = d.getRegister(d.reg1);
		short base = d.getRegister(d.reg2);
		targetCell = (short) (offset+base);
		System.out.println("cell: "+targetCell);
		write = true ;
		read=false;
	}
	void lins() {
		read=true;
		write=true;
		targetCell=d.getRegister(d.reg1);
	}
}
