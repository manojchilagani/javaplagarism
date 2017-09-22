import java.util.*;
import java.lang.*;
import java.io.*;
class Bag_of_words
{
	static int wcount,wcount1,wcount2;
	public static void main(String args[]) throws IOException
	{
	
		//to get the file names
	
		File flist[]=file.fnames(args[0]);
        ArrayList<String> text1=new ArrayList<String>();
        ArrayList<String> text2=new ArrayList<String>();
        float a,b,dproduct,percentage;
        float matrix[][]=new float[flist.length][flist.length];
        int i,j,k,line=8;
        
        //to know the order of the matrix and for printing the matrix
        
        for(i=0;i<flist.length;i++)
        {
        	line+=8;
        	for(j=i;j<flist.length;j++)
        	{

        		if(i==j)
        		{
        			matrix[i][j]=(float)100.0;
        			
        		}
        		else
        		{
        			text1=file.read_file(flist[i]);
	        		wcount1=wcount;
	        		text2=file.read_file(flist[j]);
	        		wcount2=wcount;     	
	        		HashMap<String,Integer> commonword1=new HashMap<String,Integer>();
	        		HashMap<String,Integer> commonword2=new HashMap<String,Integer>();
	        		commonword1=x.get_wcount(text1,wcount1);
	        		commonword2=x.get_wcount(text2,wcount2);
	        		a=x.euclidian(commonword1);
	        		b=x.euclidian(commonword2);
	        		dproduct=x.dproduct(commonword1,commonword2);
	        		percentage=dproduct/(a*b);
	        		percentage=percentage*100;
	        		matrix[i][j]=percentage;
	        		matrix[j][i]=percentage;
        		}
        		
        	}        	
        }
        plagarismtable.print(flist,matrix,flist.length,line);
	}
}
class file
{
	//to read the directory
	public static File[] fnames(String path) throws IOException
	{
		String target_dir = path;
    	File dir = new File(target_dir);
    	File[] files = dir.listFiles();
    	return files;
	}
	//arranging the string present in the file in the form of an array
	public static ArrayList<String> read_file(File fp) throws IOException
	{
		FileReader fileread=new FileReader(fp);
		int i,j=0,k=0;
		String word="";
		ArrayList<String> Bag=new ArrayList<String>();

		//capitalising the small letters into capital letters and removing symbols
		
		while((i=fileread.read())!=-1)
		{
			if(i>64 && i<91)
			{
				word+=(char)(i+32);
				j+=1;
			}
			else if(i>96 && i<123 || i==95 || i>47 && i<58)
			{
				word+=(char)i;
				j+=1;
			}
			else
			{

			}
			if(i==32)
			{
				Bag.add(k,word);
				word="";
				k+=1;
			}
			
		}
		Bag.add(k,word);
		Bag_of_words.wcount=k+1;
		return Bag;
	}
}
class x
{

	public static HashMap<String,Integer> get_wcount(ArrayList<String> str,int len)
	{
		int i,j,k;

		HashMap<String,Integer> a=new HashMap<String,Integer>();
		
		//removing duplicates present in same file

		for(i=0;i<len;i++)
		{
			if(a.containsKey(str.get(i)))
			{
				a.put(str.get(i),a.get(str.get(i))+1);
			}
			else
			{
				a.put(str.get(i),1);
			}
		}
		return a;
	}

	public static float euclidian(HashMap<String,Integer> hash)
	{
		Integer x,y=0;
		Set<String> s=hash.keySet();
		for(String m:s)
		{
			x=hash.get(m);
			x*=x;
			y=y+x;
		}
		return (float)Math.sqrt(y);
	}

	//to get the dot product of two files that are compared

	public static float dproduct(HashMap<String,Integer> hash_1,HashMap<String,Integer> hash_2)
	{
		Set<String> s1=hash_1.keySet();
		Set<String> s2=hash_2.keySet();
		Integer x,y,mul,s=0;
		for(String m1:s1)
		{
			for(String m2:s2)
			{

				if(m1.equals(m2))
				{
					
					x=hash_1.get(m1);
					y=hash_2.get(m2);
					mul=x*y;
					
					s+=mul;
				}
			}
		}
		return s;
	}
}

class plagarismtable
{

//to print the plagarism table in a matrix pattern form

	public static void print(File file[],float matrix[][],int len,int line_len)
	{
		int i,j,k;
		System.out.print("\n|File\t\t");
		for(File m:file)
			{
				System.out.print("|"+m.getName()+"\t");
			}
		System.out.print("|\n");
		for(i=0;i<line_len;i++)
			{
				System.out.print("==");
			}	
		System.out.print("\n");
		for(i=0;i<len;i++)
		{
			System.out.print("|"+file[i].getName()+"\t");
			for(j=0;j<len;j++)
			{
				System.out.printf("|%.2f\t\t",matrix[i][j]);
			}
			System.out.print("|\n");
		}
	}
}