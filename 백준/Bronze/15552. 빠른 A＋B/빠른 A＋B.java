import java.io.*;
import java.util.*;
public class Main{
	public static void main(String[] args){
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
			int num = Integer.parseInt(in.readLine());
			for (int i = 0; i < num; i++){
				StringTokenizer st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int result = a + b;
				out.write(Integer.toString(result) + '\n');
			}
			out.flush();
		}
		catch(Exception e) {
			System.out.println("Exception occured");
		}
	}
}
