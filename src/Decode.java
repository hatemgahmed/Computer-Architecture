
import java.util.Arrays;

public class Decode {
	String opcode = "";
	String reg1 = "";
	String reg2 = "";
	String destination = "";
	String regj = "";
	String reglwsw1 = "";
	String reglwsw2 = "";
	String destImm = "";
	String imm = "";
	Fetch f;
	int[] instruction;
	static short $zero=0;
	static short $pc=0;
	static short $t3;
	static short $sp;
	static short $ra;
	static short $t0;
	static short $t1;
	static short $t2;
	static short $v0;
	static short $a0;
	static short $a1;
	static short $a2;
	static short $s0;
	static short $s1;
	static short $s2;
	static short $s3;
	static short $s4;
	short rd;
	short $pcCopy;
	static void set(String destination,short value) {
		switch (destination) {
		case "0000":
			$zero=value;return;
		case "0001":
			$v0=value;return;
		case "0010":
			$pc=value; return;
		case "0011":
			$sp=value; return;
		case "0100":
			$ra=value; return;
		case "0101":
			$t0=value; return;
		case "0110":
			$t1=value; return;
		case "0111":
			$t2=value; return;
		case "1000":
			$t3=value; return;
		case "1001":
			$a0=value; return;
		case "1010":
			$a1=value; return;
		case "1011":
			$a2=value; return;
		case "1100":
			$s0=value; return;
		case "1101":
			$s1=value; return;
		case "1110":
			$s2=value; return;
		case "1111":
			$s3=value; return;
		}
		System.out.println("register not found");
	}
	
	String signExendFrom8(String binary) {
		String s=binary;
		char c=binary.charAt(0);
		for(int i=0;i<8;i++) {
			s=c+s;
		}
		return s;
	}
	String signExendFrom12(String binary) {
		String s=binary;
		char c=binary.charAt(0);
		for(int i=0;i<12;i++) {
			s=c+s;
		}
		return s;
	}
	
	public Decode(Fetch f) {
		this.f=f;
		this.instruction = f.instruction;
//		$t0=9;
//		$t1=0;
		$pcCopy=$pc;
		System.out.println(f.instructionString+" pcCopy: "+$pcCopy);
		for (int i = 0; i < 4; i++) {
			opcode += instruction[i];

		}
		for (int i = 4; i < 8; i++) {
			destination += instruction[i];
		}
		for (int i = 8; i < 12; i++) {
			reg1 += instruction[i];
		}
		for (int i = 12; i < 16; i++) {
			reg2 += instruction[i];
		}
		for (int j = 4; j < 16; j++) {
			regj += instruction[j];
		}
		regj=signExendFrom12(regj);
		// look here

		// from 10
		for (int j = 4; j < 8; j++) {
			reglwsw1 += instruction[j];
		}
		for (int j = 8; j < 12; j++) {
			reglwsw2 += instruction[j];
		}
		// rest should be zero
		for (int j = 4; j < 8; j++) {
			destImm += instruction[j];
		}
		for (int j = 8; j < 16; j++) {
			imm += instruction[j];
		}
//		System.out.println(imm);
		imm=signExendFrom8(imm);
//		System.out.println(imm.length());
		System.out.println(f.instructionString+" Instruction Decoded");
	}

	short getRegister(String x) {
		switch (x) {
		case "0000":
			return $zero;
		case "0001":
			return $v0;
		case "0010":
			return $pc;
		case "0011":
			return $sp;
		case "0100":
			return $ra;
		case "0101":
			return $t0;
		case "0110":
			return $t1;
		case "0111":
			return $t2;
		case "1000":
			return $t3;
		case "1001":
			return $a0;
		case "1010":
			return $a1;
		case "1011":
			return $a2;
		case "1100":
			return $s0;
		case "1101":
			return $s1;
		case "1110":
			return $s2;
		case "1111":
			return $s3;
		}
		System.out.println("register not found");
		return 0;
	}

}
