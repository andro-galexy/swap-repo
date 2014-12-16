/*Application Name: RMC
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo/RMC
E-mail: swapnil.udapure5@gmail.com*/

import java.io.*;
import java.net.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class FileServer  implements Runnable{
    private static ServerSocket serverSocket;

public FileServer()
{
new Thread(this).start();
}

public void run() //throws IOException
{
try{
serverSocket = new ServerSocket(6788);  //Server socket 
        //System.out.println("Server started. Listening to the port 6788");
 	while(true){
	Socket clientSocket = serverSocket.accept();
	//System.out.println("Request accepted.");
	new Handler2(clientSocket);
	}
}
catch(IOException e){}
}

}

 class Handler2 implements Runnable{
	Socket clientSocket;
	Handler2 (Socket s){
	this.clientSocket=s;
	//this.path=FileServer.FilePath;
	//System.out.println("Path :"+FileServer.FilePath);
	new Thread(this).start();
	//System.out.println("Thread created.");
	}
	public void notify(String msg)
	{
	try{
	String[] notifyCmd = {"notify-send","-i", System.getenv("HOME")+"/.rmc/RMC.png", "RMC Application",msg};
			Runtime.getRuntime().exec(notifyCmd);
	}
	catch(IOException e){}
	}
	public void run(){
	try {
			// Construct BufferedReader from FileReader
 			File localFile2 = new File(System.getenv("HOME") + "/.rmc");
			if (!localFile2.exists()) {
			if (localFile2.mkdir()){}
			//System.out.println("Directory created!");
			else {
			//System.out.println("Failed to create directory!");
			       }
			   }
			File file1 = new File(System.getenv("HOME")+"/.rmc/RMC.txt");

			if (!file1.exists()) {
			       if (file1.createNewFile()){}
			//         System.out.println("file created!");
			       else {
			  //       System.out.println("Failed to create file!");
				       }
				     }

			String path;
			BufferedReader br = new BufferedReader(new FileReader(file1));
			//String line = null;
			path = br.readLine();
			if(path.contains("\n")){}//System.out.println("newline");}
	   		 File file = new File(path);
	        	 OutputStream outputStream = clientSocket.getOutputStream();

		    byte[] mybytearray = new byte[(int) file.length()]; //create a byte array to file
		    FileInputStream fileInputStream = new FileInputStream(file);
		    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); 		   
		    bufferedInputStream.read(mybytearray, 0, mybytearray.length); //read the file		    
		    outputStream.write(mybytearray, 0, mybytearray.length); //write file to the output stream byte by byte
		    outputStream.flush();
		    bufferedInputStream.close();
		    outputStream.close();	
		    clientSocket.close();
			String msg=path.substring(path.lastIndexOf("/")+1);;
			String[] notifyCmd = {"notify-send","-i", System.getenv("HOME")+"/.rmc/RMC.png", "RMC Application","File: "+msg+" Sent Successfully"};
			Runtime.getRuntime().exec(notifyCmd);	        
	    //System.out.println("Sent");    
		   } catch (UnknownHostException e) {
			notify(e.toString());

		   	//System.out.println("UnknownHostException");
		    //e.printStackTrace();
		   } catch (IOException e) {
			notify(e.toString());
		   	///System.out.println("IOException");
			//	e.printStackTrace();
			} 
}
}
