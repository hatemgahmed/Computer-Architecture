
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.out.println("Enter you instruction: ");
		BufferedReader br = new BufferedReader(new FileReader("code.txt"));
		Fetch f = null;
		Decode d = null;
		Execute e = null;
		Memory m = null;
		Writeback wb = null;
		ArrayList<String> instructions=new ArrayList<String>();
		while (br.ready()) 
			instructions.add(br.readLine());
//		changed from plus i to minus i in all
		for(short i = 0; Decode.$pc-i<instructions.size();i=i==4?4:++i) {
			System.out.println("PC: "+Decode.$pc);
			if (i >= 4&&Decode.$pc-4<instructions.size())
				wb = new Writeback(m);
			if (i >= 3&&Decode.$pc-3<instructions.size())
				m = new Memory(e);
			if (i >= 2&&Decode.$pc-2<instructions.size()) {
				e = new Execute(d);
				if (e.jump) {
//					changed from 1 to 0
					i = -1;
					if(m!=null)
						wb=new Writeback(m);
					continue;
				}
			}
			if (i >= 1&&Decode.$pc-1<instructions.size())
				d = new Decode(f);
			if (i >= 0&&Decode.$pc<instructions.size())
				f = new Fetch(instructions.get(Decode.$pc));
			Decode.$pc++;
//			System.out.println(Decode.$s0);
//			if(d!=null)
//				System.out.println(d.imm);
//			 String s=Integer.toBinaryString((int)(d.rd));
			// System.out.println( s.length()<=16?s: s.substring(16, 32));
		}
		System.out.println("t0: " + Decode.$t0);
		System.out.println("t1: " + Decode.$t1);
		System.out.println("t2: " + Decode.$t2);
		System.out.println("s0: " + Decode.$s0);
		System.out.println("s1: " + Decode.$s1);
		System.out.println("ra: " + Decode.$ra);
		System.out.println("Memory: "+Arrays.toString(Memory.memory));
	}
}
/*
 * 0000110001100101 1100000101010000
 * 
 */