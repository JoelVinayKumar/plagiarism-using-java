import java.util.*;
import java.lang.*;
import java.io.*;
class fingerPrinting{
	public static void main(String[] args) {
		int i=0;
		try{
		File dir= new File("txt");
		File[] files= dir.listFiles();
		String[] s= new String[100];
		
		char[] c= new char[50];
		for(File f:files){
			FileReader fr=new FileReader(f);
			fr.read(c);
			s[i]=new String(c);
			i++;
		}
		
		System.out.println("::::::::::::::::::::::::::::");
		System.out.println(":::Finger Printing Matrix:::");
		System.out.println("::::::::::::::::::::::::::::");
			for(File d:files){
				System.out.printf("\t%s",d.getName());
			}
			System.out.println("\n");
			for(int x=0;x<i;x++){
				System.out.printf("%s\t",files[x].getName());
				for(int y=0;y<i;y++){
					System.out.printf("|%.2f\t",output(s[x],s[y]));
				}
				System.out.println("\n");
			}
		}
		catch(Exception e){
			System.out.println("Exception Caught : "+e);
		}
	}
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
	    double plag= (2*count)/(la+lc);
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