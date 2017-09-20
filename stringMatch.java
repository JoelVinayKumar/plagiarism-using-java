import java.util.*;
import java.io.*;
import java.math.*;

class stringMatch{
	public static void main(String[] args) {
		int i=0;
		try{
		File dir= new File("txt");
		File[] files= dir.listFiles();
		String[] s= new String[100];
		String g="";
		char[] c= new char[1000];
		for(File f:files){
			FileReader fr=new FileReader(f);
			fr.read(c);
			s[i]="";
			for(char a:c){
				s[i]+=a;
			}
			i++;
		}
		
		System.out.println(":::::::::::::::::::::::::");
		System.out.println(":::String Match Matrix:::");
		System.out.println(":::::::::::::::::::::::::");
			for(int w=0;w<i;w++){
				System.out.println(" "+output(s[0],s[w]));
			}
		}
		catch(Exception e){
			System.out.println("ExceptionCaught : "+e);
		}
	}
	public static float output(String c1,String c2){
		if(c1.equals(c2)){
			return 0;
		}
		else{
			char[] s1=c1.toCharArray();
			char[] s2=c2.toCharArray();
			int l=s1.length,m=s2.length;
			String match,answer="";
			for(int i=0;i<l;i++){
				match="";
				for(int j=0;j<m;j++){
					if(i+j<l && s1[i+j]==s2[j]){
						match+=s2[j];
					}
					else{
						if(match.length()>answer.length()){
							answer=match;
						}
					match="";
					}
				}
			}
			int LCS= (answer.length())-1;
			float output=(LCS*200)/(l+m);
			return output;
		}
	}
}