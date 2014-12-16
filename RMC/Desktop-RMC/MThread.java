/*Application Name: RMC
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo/RMC
E-mail: swapnil.udapure5@gmail.com*/
import java.io.*;
/*   */ class MThread
/*   */ {
/*   */   public static void main(String[] args)
/*   */     throws Exception
/*   */   {
		try{
	File file1 = new File(System.getenv("HOME")+"/.config/autostart/RMC.desktop");
		if (!file1.exists()) {
		      if (file1.createNewFile()){
	PrintWriter out = new PrintWriter(file1);
	out.print("[Desktop Entry]\nType=Application\nName=RMC\nExec="+System.getenv("HOME")+"/.rmc/RMC.jar\nX-GNOME-Autostart-enabled=true");
			out.flush();out.close();System.exit(0);}
		   else {
		       }
		}

	String[] notifyCmd = {"notify-send","-i", System.getenv("HOME")+"/.rmc/RMC.png", "RMC Application","RMC Thread Started..."};
			Runtime.getRuntime().exec(notifyCmd);
		
/* 6 */     new Server();
	    new FileServer();}
		catch(Exception e){String[]Cmd = {"notify-send","-i", System.getenv("HOME")+"/.rmc/RMC.png", "RMC Application",e.toString()};
			Runtime.getRuntime().exec(Cmd);}
		//catch(IOException e){}
/*   */   }
/*   */ }

