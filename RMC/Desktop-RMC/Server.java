/*Application Name: RMC
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo/RMC
E-mail: swapnil.udapure5@gmail.com*/

import java.io.*;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.net.*;
class Server  implements Runnable{
public static int space_code;

public Server()
{
new Thread(this).start();
}

public void run() //throws Exception
{
int n,fact=1;
try{
ServerSocket ss = new ServerSocket(6789);
//System.out.println("server is listening at port 6789.....");
while(true){
Socket sock = ss.accept();
//System.out.println("Request accepted.");
new Handler(sock);
}
}
catch(IOException e){}
}

}

class Handler implements Runnable {
byte[] mybytearray = new byte[1024];    //create byte array
Socket sock;
Server server = new Server();
Robot r;
int down;
Runtime runtime;Process process;
int bytesRead;
Handler (Socket s){
this.sock=s;
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
try{
r = new Robot();
//r.delay(2000);
while(true)
{
InputStream inputStream = sock.getInputStream();
PrintWriter toclient = new PrintWriter(sock.getOutputStream(),true);
bytesRead = inputStream.read(mybytearray, 0, mybytearray.length);
//System.out.println("up");
String str = new String(mybytearray);

if(str.contains("C"))
{
r.keyPress(KeyEvent.VK_CONTROL);  
r.keyPress(KeyEvent.VK_ALT);
r.keyPress(KeyEvent.VK_T);  
r.keyRelease(KeyEvent.VK_CONTROL);  
r.keyRelease(KeyEvent.VK_ALT);
r.keyRelease(KeyEvent.VK_T);      
//runtime = Runtime.getRuntime();
//process = runtime.exec("gnome-terminal -x gnome-terminal");
//process.waitFor();
//toclient.println("ok");
}
else if(str.contains("sh"))
{
//System.out.println("shutdown....");
runtime = Runtime.getRuntime();
process = runtime.exec("sudo shutdown -P");
}
else if(str.contains("rb"))
{
runtime = Runtime.getRuntime();
process = runtime.exec("sudo reboot");
}
else if(str.contains("ss"))
{//System.out.println("suspending....");
runtime = Runtime.getRuntime();
process = runtime.exec("pm-suspend");
process.waitFor();
}
else if(str.contains("W"))
{
down=1;
r.keyPress(KeyEvent.VK_CONTROL);  
r.keyPress(KeyEvent.VK_C);  
r.keyRelease(KeyEvent.VK_CONTROL);  
r.keyRelease(KeyEvent.VK_C);  
String data = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
try {
File file = new File(System.getenv("HOME")+"/.rmc/RMC.txt");
// if file doesnt exists, then create it
if (!file.exists()) {
file.createNewFile();
}
FileWriter fw = new FileWriter(file.getAbsoluteFile());
BufferedWriter bw = new BufferedWriter(fw);
bw.write(data);
bw.close();
//System.out.println("Done");
} catch (IOException e) {
notify(e.toString());
e.printStackTrace();
}
String FileName=data.substring(data.lastIndexOf("/")+1);
File df = new File(data);
toclient.print(FileName+"::"+df.length());
toclient.flush();
//notify(FileName+"::"+df.length());
//System.out.println("Path :"+data+"\t"+FileName);
}

//System.out.println("Recieved: "+str);
if(str.contains("a"))
{
r.keyPress(KeyEvent.VK_A);
r.keyRelease(KeyEvent.VK_A);
server.space_code=KeyEvent.VK_A;
}
else if(str.contains(" "))
{
//if(flag=1){
r.keyPress(server.space_code);
r.keyRelease(server.space_code);
r.keyPress(KeyEvent.VK_SPACE);
r.keyRelease(KeyEvent.VK_SPACE);
server.space_code=KeyEvent.VK_SPACE;
//flag=0;
//}
}
else if(str.contains("$"))
{
r.keyPress(KeyEvent.VK_ENTER);
r.keyRelease(KeyEvent.VK_ENTER);
}
else if(str.contains("B"))
{
r.keyPress(KeyEvent.VK_BACK_SPACE);
r.keyRelease(KeyEvent.VK_BACK_SPACE);
if(server.space_code==KeyEvent.VK_SPACE)
{
//r.keyPress(server.space_code);
//r.keyRelease(server.space_code);
//r.keyPress(KeyEvent.VK_SPACE);
//r.keyRelease(KeyEvent.VK_SPACE);
}

}
else if(str.contains("A"))
{
//System.out.println(str);
r.keyPress(KeyEvent.VK_ALT);  
r.keyPress(KeyEvent.VK_F1);  
r.keyRelease(KeyEvent.VK_ALT);  
r.keyRelease(KeyEvent.VK_F1);
r.delay(100);
r.keyPress(KeyEvent.VK_ENTER);  
r.keyRelease(KeyEvent.VK_ENTER); 
}
else if(str.contains("X"))
{
//System.out.println(str);
r.keyPress(KeyEvent.VK_ALT);  
r.keyPress(KeyEvent.VK_F4);  
r.keyRelease(KeyEvent.VK_ALT);  
r.keyRelease(KeyEvent.VK_F4);
}
else if(str.contains("b"))
{
r.keyPress(KeyEvent.VK_B);
r.keyRelease(KeyEvent.VK_B);
server.space_code=KeyEvent.VK_B;
}
else if(str.contains("c"))
{
r.keyPress(KeyEvent.VK_C);
r.keyRelease(KeyEvent.VK_C);
server.space_code=KeyEvent.VK_C;
}
else if(str.contains("d"))
{
r.keyPress(KeyEvent.VK_D);
r.keyRelease(KeyEvent.VK_D);
if(down==1){
r.keyPress(KeyEvent.VK_ESCAPE);  
r.keyRelease(KeyEvent.VK_ESCAPE);
down=0;
}
server.space_code=KeyEvent.VK_D;
}
else if(str.contains("e"))
{
r.keyPress(KeyEvent.VK_E);
r.keyRelease(KeyEvent.VK_E);
server.space_code=KeyEvent.VK_E;
}
else if(str.contains("f"))
{
r.keyPress(KeyEvent.VK_F);
r.keyRelease(KeyEvent.VK_F);
server.space_code=KeyEvent.VK_F;
}
else if(str.contains("g"))
{
r.keyPress(KeyEvent.VK_G);
r.keyRelease(KeyEvent.VK_G);
server.space_code=KeyEvent.VK_G;
}
else if(str.contains("h"))
{
r.keyPress(KeyEvent.VK_H);
r.keyRelease(KeyEvent.VK_H);
server.space_code=KeyEvent.VK_H;
}
else if(str.contains("i"))
{
r.keyPress(KeyEvent.VK_I);
r.keyRelease(KeyEvent.VK_I);
server.space_code=KeyEvent.VK_I;
}
else if(str.contains("j"))
{
r.keyPress(KeyEvent.VK_J);
r.keyRelease(KeyEvent.VK_J);
server.space_code=KeyEvent.VK_J;
}
else if(str.contains("k"))
{
r.keyPress(KeyEvent.VK_K);
r.keyRelease(KeyEvent.VK_K);
server.space_code=KeyEvent.VK_K;
}
else if(str.contains("l"))
{
r.keyPress(KeyEvent.VK_L);
r.keyRelease(KeyEvent.VK_L);
server.space_code=KeyEvent.VK_L;
}
else if(str.contains("m"))
{
r.keyPress(KeyEvent.VK_M);
r.keyRelease(KeyEvent.VK_M);
server.space_code=KeyEvent.VK_M;
}
else if(str.contains("n"))
{
r.keyPress(KeyEvent.VK_N);
r.keyRelease(KeyEvent.VK_N);
server.space_code=KeyEvent.VK_N;
}
else if(str.contains("o"))
{
r.keyPress(KeyEvent.VK_O);
r.keyRelease(KeyEvent.VK_O);
server.space_code=KeyEvent.VK_O;
}
else if(str.contains("p"))
{
r.keyPress(KeyEvent.VK_P);
r.keyRelease(KeyEvent.VK_P);
server.space_code=KeyEvent.VK_P;
}
else if(str.contains("q"))
{
r.keyPress(KeyEvent.VK_Q);
r.keyRelease(KeyEvent.VK_Q);
server.space_code=KeyEvent.VK_Q;
}
else if(str.contains("r"))
{
r.keyPress(KeyEvent.VK_R);
r.keyRelease(KeyEvent.VK_R);
server.space_code=KeyEvent.VK_R;
}
else if(str.contains("s"))
{
r.keyPress(KeyEvent.VK_S);
r.keyRelease(KeyEvent.VK_S);
server.space_code=KeyEvent.VK_S;
}
else if(str.contains("t"))
{
r.keyPress(KeyEvent.VK_T);
r.keyRelease(KeyEvent.VK_T);
server.space_code=KeyEvent.VK_T;
}
else if(str.contains("u"))
{
r.keyPress(KeyEvent.VK_U);
r.keyRelease(KeyEvent.VK_U);
server.space_code=KeyEvent.VK_U;
}
else if(str.contains("v"))
{
r.keyPress(KeyEvent.VK_V);
r.keyRelease(KeyEvent.VK_V);
server.space_code=KeyEvent.VK_V;
}
else if(str.contains("w"))
{
r.keyPress(KeyEvent.VK_W);
r.keyRelease(KeyEvent.VK_W);
server.space_code=KeyEvent.VK_W;
}
else if(str.contains("x"))
{
r.keyPress(KeyEvent.VK_X);
r.keyRelease(KeyEvent.VK_X);
server.space_code=KeyEvent.VK_X;
}
else if(str.contains("y"))
{
r.keyPress(KeyEvent.VK_Y);
r.keyRelease(KeyEvent.VK_Y);
server.space_code=KeyEvent.VK_Y;
}
else if(str.contains("z"))
{
r.keyPress(KeyEvent.VK_Z);
r.keyRelease(KeyEvent.VK_Z);
server.space_code=KeyEvent.VK_Z;
}
else if(str.contains("0"))
{
r.keyPress(KeyEvent.VK_0);
r.keyRelease(KeyEvent.VK_0);
server.space_code=KeyEvent.VK_0;
}
else if(str.contains("1"))
{
r.keyPress(KeyEvent.VK_1);
r.keyRelease(KeyEvent.VK_1);
server.space_code=KeyEvent.VK_1;
}
else if(str.contains("2"))
{
r.keyPress(KeyEvent.VK_2);
r.keyRelease(KeyEvent.VK_2);
server.space_code=KeyEvent.VK_2;
}
else if(str.contains("3"))
{
r.keyPress(KeyEvent.VK_3);
r.keyRelease(KeyEvent.VK_3);
server.space_code=KeyEvent.VK_3;
}
else if(str.contains("4"))
{
r.keyPress(KeyEvent.VK_4);
r.keyRelease(KeyEvent.VK_4);
server.space_code=KeyEvent.VK_4;
}
else if(str.contains("5"))
{
r.keyPress(KeyEvent.VK_5);
r.keyRelease(KeyEvent.VK_5);
server.space_code=KeyEvent.VK_5;
}
else if(str.contains("6"))
{
r.keyPress(KeyEvent.VK_6);
r.keyRelease(KeyEvent.VK_6);
server.space_code=KeyEvent.VK_6;
}
else if(str.contains("7"))
{
r.keyPress(KeyEvent.VK_7);
r.keyRelease(KeyEvent.VK_7);
server.space_code=KeyEvent.VK_7;
}
else if(str.contains("8"))
{
r.keyPress(KeyEvent.VK_8);
r.keyRelease(KeyEvent.VK_8);
server.space_code=KeyEvent.VK_8;
}
else if(str.contains("9"))
{
r.keyPress(KeyEvent.VK_9);
r.keyRelease(KeyEvent.VK_9);
server.space_code=KeyEvent.VK_9;
}
else if(str.contains("#"))
{
r.keyPress(KeyEvent.VK_NUMBER_SIGN);
r.keyRelease(KeyEvent.VK_NUMBER_SIGN);
server.space_code=KeyEvent.VK_NUMBER_SIGN;
}
else if(str.contains("-"))
{
r.keyPress(KeyEvent.VK_MINUS);
r.keyRelease(KeyEvent.VK_MINUS);
server.space_code=KeyEvent.VK_MINUS;
}
else if(str.contains("!"))
{
r.keyPress(KeyEvent.VK_EXCLAMATION_MARK);
r.keyRelease(KeyEvent.VK_EXCLAMATION_MARK);
server.space_code=KeyEvent.VK_EXCLAMATION_MARK;
}
else if(str.contains("@"))
{
r.keyPress(KeyEvent.VK_AT);
r.keyRelease(KeyEvent.VK_AT);
server.space_code=KeyEvent.VK_AT;
}
else if(str.contains("$"))
{
r.keyPress(KeyEvent.VK_DOLLAR);
r.keyRelease(KeyEvent.VK_DOLLAR);
}
else if(str.contains("%"))
{
r.keyPress(KeyEvent.VK_DIVIDE);
r.keyRelease(KeyEvent.VK_DIVIDE);
server.space_code=KeyEvent.VK_DIVIDE;
}
else if(str.contains("+"))
{
r.keyPress(KeyEvent.VK_PLUS);
r.keyRelease(KeyEvent.VK_PLUS);
server.space_code=KeyEvent.VK_PLUS;
}
else if(str.contains("*"))
{
r.keyPress(KeyEvent.VK_ASTERISK);
r.keyRelease(KeyEvent.VK_ASTERISK);
server.space_code=KeyEvent.VK_ASTERISK;
}
else if(str.contains(":"))
{
r.keyPress(KeyEvent.VK_COLON);
r.keyRelease(KeyEvent.VK_COLON);
server.space_code=KeyEvent.VK_COLON;
}
else if(str.contains(";"))
{
r.keyPress(KeyEvent.VK_SEMICOLON);
r.keyRelease(KeyEvent.VK_SEMICOLON);
server.space_code=KeyEvent.VK_SEMICOLON;
}
else if(str.contains("("))
{
r.keyPress(KeyEvent.VK_LEFT_PARENTHESIS );
r.keyRelease(KeyEvent.VK_LEFT_PARENTHESIS );
server.space_code=KeyEvent.VK_LEFT_PARENTHESIS;
}
else if(str.contains(")"))
{
r.keyPress(KeyEvent.VK_RIGHT_PARENTHESIS );
r.keyRelease(KeyEvent.VK_RIGHT_PARENTHESIS );
server.space_code=KeyEvent.VK_RIGHT_PARENTHESIS;
}
else if(str.contains(","))
{
r.keyPress(KeyEvent.VK_COMMA);
r.keyRelease(KeyEvent.VK_COMMA);
server.space_code=KeyEvent.VK_COMMA;
}
else if(str.contains("_"))
{
r.keyPress(KeyEvent.VK_UNDERSCORE);
r.keyRelease(KeyEvent.VK_UNDERSCORE);
server.space_code=KeyEvent.VK_UNDERSCORE;
}
else if(str.contains("."))
{
r.keyPress(KeyEvent.VK_PERIOD);
r.keyRelease(KeyEvent.VK_PERIOD);
server.space_code=KeyEvent.VK_PERIOD;
}
else if(str.contains("H"))
{
runtime = Runtime.getRuntime();
process = runtime.exec("nautilus");
}
else if(str.contains("U"))
{
r.keyPress(KeyEvent.VK_UP);
r.keyRelease(KeyEvent.VK_UP);
}
else if(str.contains("D"))
{
r.keyPress(KeyEvent.VK_DOWN);
r.keyRelease(KeyEvent.VK_DOWN);
}
else if(str.contains("L"))
{
r.keyPress(KeyEvent.VK_LEFT);
r.keyRelease(KeyEvent.VK_LEFT);
}
else if(str.contains("R"))
{
r.keyPress(KeyEvent.VK_RIGHT);
r.keyRelease(KeyEvent.VK_RIGHT);
}
else if(str.contains("O"))
{
r.keyPress(KeyEvent.VK_ENTER);
r.keyRelease(KeyEvent.VK_ENTER);
}

str = null;
//if (str=="-1")
//{
sock.close();
//break;
//}
toclient.println("ok");
}
}

catch (IOException e){			}//notify(e.toString());}//e.printStackTrace();}
catch(Exception ex) {			}//notify(ex.toString()); }///ex.printStackTrace(); }
}
}
