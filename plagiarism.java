import java.util.*;
import java.io.*;
import java.math.*;
class plagiarism{
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
		
		bagofWords bag= new bagofWords();
		stringMatch st= new stringMatch();
		fingerPrinting finger = new fingerPrinting();
		System.out.println(":::::::::::::::::::::::::");
		System.out.println(":::Bag of Words Matrix:::");
		System.out.println(":::::::::::::::::::::::::");
			for(int y=0;y<i;y++){
				int x=0;
				System.out.println(bag.output(s[x],s[y])+"\t"+bag.output(s[x],s[y])+"\t"+bag.output(s[x],s[y]));;
				System.out.println("\n");
				x++;
			}
		System.out.println(":::::::::::::::::::::::::");
		System.out.println(":::String Match Matrix:::");
		System.out.println(":::::::::::::::::::::::::");
			for(int w=0;w<i;w++){
				int z=0;
				System.out.println(st.output(s[z].toCharArray(),s[w].toCharArray())+"\t"+st.output(s[z].toCharArray(),s[w].toCharArray())+"\t"+st.output(s[z].toCharArray(),s[w].toCharArray()));
				System.out.println("\n");
				z++;
			}
		System.out.println("::::::::::::::::::::::::::::");
		System.out.println(":::Finger Printing Matrix:::");
		System.out.println("::::::::::::::::::::::::::::");
			for(int n=0;n<i;n++){
				int m=0;
				System.out.println(finger.output(s[m],s[n])+"\t"+finger.output(s[m],s[n])+"\t"+finger.output(s[m],s[n]));;
				System.out.println();
				m++;
			}
		}
		catch(Exception e){
			System.out.println("ExceptionCaught : "+e);
		}
	}
}
class bagofWords{
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
class fingerPrinting{
	public static double output(String s1,String s2){
		if(s1.equals(s2)){
			return 0;
		}
		else{
		String[] L1= s1.split(" ");
		String[] L2= s2.split(" ");
		String ast="";
		String bst="";
		for(String a:L1){
			ast+=a;
		}
		for(String b:L2){
			bst+=b;
		}
		char[] astr= ast.toCharArray();
		char[] bstr= bst.toCharArray();
		/*Extracting hash values
		k-gram is 4*/
		int kgram=4,tmp,la,lb,lc,ld,index;
		long[] hash_a=new long[100],hash_b=new long[100];
		/*Hash keys for first string*/
	    for(la=0;la<astr.length-3;la++){
	    	index=4;
	    	for(lb=la;lb<la+kgram;lb++){
	    		hash_a[la]+=((int)astr[lb]*indpow(10,index--));
	    	}
	    	hash_a[la]=hash_a[la]%10007;
	    }
	    /*Hash keys for second string*/
	    for(lc=0;lc<bstr.length-3;lc++){
	    	index=4;
	    	for(ld=lc;ld<lc+kgram;ld++){
	    		hash_b[lc]+=((int)bstr[ld]*indpow(10,index--));
	    	}
	    	hash_b[lc]=hash_b[lc]%10007;
	    }
	    /*Comparing hash arrays*/
	    int count=0;
	    for(int x=0;x<la;x++){
	    	for(int y=0;y<lc;y++){
	    		if(hash_a[x]==hash_b[y]){
	    			count++;
	    		}
	    	}
	    }
	    double plag= (2*count)*100/(la+lc);
	    return plag;
		}
	}
	public static int indpow(int a,int b){
		int res=1;
		for(int temp=1;temp<=b;temp++){
			res*=a;
		}
		return res;
	}
}