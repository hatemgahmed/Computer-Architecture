import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
public class Fetch {
	
	int[] instruction = new int[16];

	String instructionString;

	public Fetch(String s) throws IOException {
		instructionString=s;
		
		String n = "";
		if(s.length()==16 && (s.charAt(0)=='0'||s.charAt(0)=='1')){
		for (int p = 0; p < 16; p++) {
			n = s.charAt(p) + "";
			instruction[p] = Integer.parseInt(n);
		}
		}
		else {
			n=constructBinInst(s);
//			System.out.println(s);
//			System.out.println(n);
//			System.out.println(n);
			for (int p = 0; p < 16; p++) {
//				n = s.charAt(p) + "";
				instruction[p] = Integer.parseInt(""+n.charAt(p));
			}
		}
		System.out.println(instructionString +" Instruction Fetched");
	}

	String opCodeOf(String s) {
		switch (s) {
		case "add":
			return "0000";
		case "addi":
			return "0001";
		case "sub":
			return "0010";
		case "lw":
			return "0011";
		case "sw":
			return "0100";
		case "and":
			return "0101";
		case "xor":
			return "0110";
		case "j":
			return "0111";
		case "jal":
			return "1000";
		case "jr":
			return "1001";
		case "nlj":
			return "1010"; // non-leaf jump
		case "rj":
			return "1011"; // return jump
		case "lins":
			return "1100"; // load increment store
		case "nor":
			return "1101";
		case "slt":
			return "1110";
		case "beq":
			return "1111";
		default:
			System.out.println("no such instruction");
			return null;
		}
	}

	String registerOf(String s) {
		switch (s) {
		case "$zero":
			return "0000";
		case "$v0":
			return "0001";
		case "$pc":
			return "0010";
		case "$sp":
			return "0011";
		case "$ra":
			return "0100";
		case "$t0":
			return "0101";
		case "$t1":
			return "0110";
		case "$t2":
			return "0111";
		case "$t3":
			return "1000";
		case "$a0":
			return "1001";
		case "$a1":
			return "1010";
		case "$a2":
			return "1011";
		case "$s0":
			return "1100";
		case "$s1":
			return "1101";
		case "$s2":
			return "1110";
		case "$s3":
			return "1111";
		default:
			System.out.println("no such instruction");
			return null;
		}
	}

	String[] splitIns(String s) {
		String engInstruction[] = s.split(" ");

		return engInstruction;
	}

	String constructBinInst(String s) {
		String Instruction = "";
		String[] engInstruction = splitIns(s);
		Instruction += opCodeOf(engInstruction[0]);
		if (engInstruction.length == 4) {
			Instruction += registerOf(engInstruction[1]) + registerOf(engInstruction[2])
					+ registerOf(engInstruction[3]);
		} else {
			if (engInstruction.length == 3 && engInstruction[0].equalsIgnoreCase("lins")) {
//				added regOf [2]
				Instruction += registerOf(engInstruction[1]) + registerOf(engInstruction[2]) + "0000";
			} else if (engInstruction.length == 3 && engInstruction[0].equalsIgnoreCase("addi")) {
				Instruction += registerOf(engInstruction[1]);
				String temp = Integer.toBinaryString(Integer.parseInt(engInstruction[2]));
//				System.out.println("temp: "+temp);
				if (Integer.parseInt(engInstruction[2]) >= 0) {
					if (temp.length() < 8) {
						for (int i = 0; i < 8; i++)
							if (temp.length() != 8)
								temp = "0" + temp;
						Instruction += temp;
					} else if (temp.length() > 8) {
						String value = temp.substring(temp.length() - 8, temp.length());
						Instruction += value;
					} else
						Instruction += temp;

				} 
//				 it is negative
				else {

//					if (temp.length() < 8) {
//						for (int i = 0; i < 8; i++)
//							if (temp.length() != 8 && i != 7)
//								temp = "0" + temp;
//							else {
//								temp = "1" + temp;
//							}
//						Instruction += temp;
//					} else 
						if (temp.length() > 8) {
						String value = temp.substring(temp.length() - 8, temp.length() );
//						System.out.println("value: "+value);
						Instruction += value;
					} else
						Instruction += temp;
				}
			} else {
				if (engInstruction.length == 2) {
					String temp = Integer.toBinaryString(Integer.parseInt(engInstruction[1]));
					if (temp.length() < 12) {
						for (int i = 0; i < 12; i++)
							if (temp.length() != 12)
								temp = "0" + temp;
						Instruction += temp;
					} else if (temp.length() > 12) {
//						changed from 9 to 13
						String value = temp.substring(temp.length() - 12, temp.length() );
						Instruction += value;
					} else {
						Instruction += temp;
					}
				}
			}
		}
		return Instruction;

	}

}
