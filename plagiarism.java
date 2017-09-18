import java.util.*;
import java.io.*;
class plagiarism{
	public static void main(String[] args) {
		String s1="to be or not to be ";
		String s2="doubt truth to be a liar";
		bagofWords bag= new bagofWords();
		System.out.println(bag.output(s1,s2));
	}
}
class bagofWords{
	public static double output(String s1,String s2){
		String[] L1= s1.split(" ");
		String[] L2= s2.split(" ");
		String[] L3= (s1+s2).split(" ");
		String[] UW=new String [100];
		int[] count= new int [100];
		int[] c1= new int [100];
		int[] c2= new int [100];
		int len=-1,euc1=0,euc2=0,dot=0;
		//Extracting unique words
		for(int i=0;i<L3.length;i++){
			count[i]=0;
			for(int j=i+1;j<L3.length;j++){
				if(L3[i].equals(L3[j])){
					count[i]++;
				}
			}
		}
		//Arranging Unique words into array
		for(int k=0;k<L3.length;k++){
			if(count[k]==0){
				UW[++len]=L3[k];
			}
		}
		//Frequency Vector 1 & 2
		for(int z=0;z<=len;z++){
			c1[z]=0;
			c2[z]=0;
			for(int w=0;w<L2.length;w++){
				if(UW[z].equals(L2[w]))
					c2[z]++;
			}
			euc2+=(c2[z]*c2[z]);
			for(int y=0;y<L1.length;y++){
				if(UW[z].equals(L1[y]))
					c1[z]++;
			}
			euc1+=(c1[z]*c1[z]);
			dot+=(c1[z]*c2[z]);
		}
		double bag=0;
		bag=dot*100/Math.sqrt(euc1*euc2);
		return bag;
	}
}
class stringMatch{
	public static float output(char[] s1,char[] s2){
		if(s1.equals(s2)){
			return 0;
		}
		else{
			int l=s1.length,m=s2.length;
			String match,answer="";
			float output=0;
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
			output=(LCS*200)/(l+m);
			return output;
		}
	}
}