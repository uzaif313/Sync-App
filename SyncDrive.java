package Syncs;
import java.io.*;
import java.util.*;


public class SyncDrive 
 {
	
	 File fsrc,fdes;
	String des,src;
	Thread t;
	boolean first=true;
	public SyncDrive(){
		
	}
	
	public SyncDrive(String src,String des)
	{
		
				
		this.des=des;
		this.src=src;
		fsrc=new File(src);
		des+=File.separator+fsrc.getName();
		fdes=new File(des);
		Runnable rn=new Runnable()
		{
		
		public void run()
		{
			while(true)
			{
				try
				{
					copy(fsrc,fdes);
					Thread.sleep(5000);
					first=false;
				}
				catch(Exception ex)
				{
						System.out.println("Error:-> "+ex.getMessage());
				}
			}
	   }

			
			
		};

	
			t=new Thread(rn);
			t.setDaemon(true);
			t.start();
	} 	

	
	public void copy(File sourceLocation, File targetLocation) throws IOException
	{
			
			/* if(!fdes.exists())
			{
					fdes.mkdir();
			} */
			
						
						/*
						System.out.println("Modified Copy Work");
						 */	
						 if(first)
						 {
							 System.out.println("First Copy");
							if (sourceLocation.isDirectory()) 
							{
									copyDirectory(sourceLocation, targetLocation);
							}
							 else 
							{
									copyFile(sourceLocation, targetLocation);
							} 
						 } 
							else
						{	
					
								
								if (sourceLocation.isDirectory()) 
								{
										copyDirectory(sourceLocation, targetLocation);
								}
								 else 
								{
									if(isModified(sourceLocation))
									{
										System.out.println("Modified Copy Work");
										System.out.println("Path:"+sourceLocation.getName());										
										copyFile(sourceLocation, targetLocation);
									}								
								}
						}
						
  	}
				
	
				
				
			
	
	

private  void copyDirectory(File source, File target) throws IOException 
{
    if (!target.exists()) {
        target.mkdir();
    }
	
			for (String f : source.list()) 
		{
			copy(new File(source, f), new File(target, f));
		}
}

private  void copyFile(File source, File target) throws IOException
{        
    try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target);
		
        byte[] buf = new byte[1024];
        int length;
        while ((length = in.read(buf)) > 0) {
            out.write(buf, 0, length);
        }
		in.close();
		out.close();
    }
	catch(Exception ex)
	{
		System.out.println("Error:-> "+ex.getMessage());
	}
	
}
	
	
	public boolean isModified(File f)
	{
		Calendar fileModifiedTime=Calendar.getInstance();
		fileModifiedTime.setTime(new Date(f.lastModified()));
		Calendar last=Calendar.getInstance();
		last.setTime(new Date());
		last.set(Calendar.SECOND,last.get(Calendar.SECOND)-5);	
		System.out.println("Current Modified:"+last.getTime());
		System.out.println("last modified:"+fileModifiedTime.getTime());
		return fileModifiedTime.after(last);
	}
	


}