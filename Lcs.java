import java.util.*;
import java.lang.*;
import java.io.*;
class Lcs
{
	static float wcount,l1,l2;

//to print the lcs table in a matrix pattern form

	public static void main(String args[]) throws IOException
	{
        int i,j,k,line=8;
		float lcsp,lcs;
		
		File flist[]=files.fname(args[0]);

		float plag[][]=new float[flist.length][flist.length];
        
        ArrayList<String> text1=new ArrayList<String>();
        ArrayList<String> text2=new ArrayList<String>();
        
        for(i=0;i<flist.length;i++)
        {
        	line+=8;
        	for(j=i;j<flist.length;j++)
        	{
        
        		if(i==j)
        		{
        			plag[i][j]=(float)100.0;
        			
        		}
        
        		else
        		{
        			text1=files.filescan(flist[i]);
	        		l1=wcount;
	        		text2=files.filescan(flist[j]);
	        		l2=wcount;     	
	        		Plagarism a=new Plagarism(text1,text2);
	        		lcs=a.get_lcs();
	        		lcsp=(lcs*2)/(l1+l2);
	        		lcsp=lcsp*100;
	        		plag[i][j]=lcsp;
	        		plag[j][i]=lcsp;
        		}
       		
        	}        	
        }
        lcsplagarism.print(flist,plag,flist.length,line);
	}
}

class files
{

	//to read the directory path of text files folders
	
	public static File[] fname(String path) throws IOException
	{
	
		String target_dir = path;
    	File dir = new File(target_dir);
    	File[] files = dir.listFiles();
    	return files;
	}
	
	//capitalising the small letters into capital letters and removing symbols

	public static ArrayList<String> filescan(File fp) throws IOException
		{
	
		int i,j=0,k=0,l=0;
	
		String word="";
		ArrayList<String> text=new ArrayList<String>();
		FileReader fileread=new FileReader(fp);
	
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
	
			if(i==32)
			{
				text.add(k,word);
				word="";
				k+=1;
			}
			
		}
		text.add(k,word);
		Lcs.wcount=j;
		return text;
	}
}

	//to know the longest common sub string betwwen to files

class Plagarism
{

	ArrayList<String> commonstring1=new ArrayList<String>();
	ArrayList<String> commonstring2=new ArrayList<String>();

	Plagarism(ArrayList<String> commonstring1,ArrayList<String> commonstring2)
	{
		this.commonstring1.addAll(commonstring1);
		this.commonstring2.addAll(commonstring2);
	}

	float get_lcs()
	{
		int i,j,k;
		float lcs=0,longestCS=0;
		for(i=0;i<this.commonstring1.size();i++)
		{
			k=i;
			lcs=0;
			for(j=0;j<this.commonstring2.size();j++)
			{
				if(this.commonstring1.get(k).equals(this.commonstring2.get(j)))
				{
					lcs+=this.commonstring1.get(k).length();
					
					if(lcs>longestCS)
					{
						longestCS=lcs;
					}
					k+=1;
				}
				else
				{		
					lcs=0;
				}
				if(k==this.commonstring1.size())
					{
						break;
					}
			}
			
		}
		return longestCS;
	}
}

//to print the lcs percent in matrix form

class lcsplagarism
{

	public static void print(File file[],float matrix[][],int len,int line_len)
	{

		int i,j,k;
		System.out.print("\n||File\t\t");
		for(File m:file)
			{
				System.out.print("||"+m.getName()+"\t");
			}
		System.out.print("\n");
		for(i=0;i<line_len-1;i++)
			{
				System.out.print("**");	
			}
		System.out.print("\n");
		for(i=0;i<len;i++)
		{
			System.out.print("|"+file[i].getName()+"\t");
			for(j=0;j<len;j++)
			{
				System.out.printf("|%.2f\t\t",matrix[i][j]);
			}
			System.out.print("\n");
		}
	}
}