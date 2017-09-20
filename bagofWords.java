import java.util.*;
import java.io.*;
import java.math.*;
class bagofWords{
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
		System.out.println(":::Bag of Words Matrix:::");
		System.out.println(":::::::::::::::::::::::::");
			for(int y=0;y<i;y++){
				int x=0;
				System.out.println(output(s[x],s[y])+"\t"+output(s[x],s[y])+"\t"+output(s[x],s[y]));;
				System.out.println("\n");
				x++;
			}
		}
		catch(Exception e){
			System.out.println("ExceptionCaught : "+e);
		}
	}
	public static double output(String s1,String s2){
	if(s1.equals(s2)){
		return 0;
	}
	else{
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
}