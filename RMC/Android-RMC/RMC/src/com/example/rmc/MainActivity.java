/*Application Name: RMC
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo/RMC
E-mail: swapnil.udapure5@gmail.com*/

package com.example.rmc;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
 
@SuppressLint("NewApi")
public class MainActivity extends Activity implements SensorEventListener{
 
int track;
String enter="";
int befor;
String cmd;
String space;
///int space=0;
int previous;
private float mLastX, mLastY, mLastZ;
private boolean mInitialized;
private SensorManager mSensorManager;
private Sensor mAccelerometer;
private final float NOISE = (float) 2.0;
int flag;
static String FileName;
protected static Socket clientsock;
protected static InputStream inputStream;
//ImageButton upArrow;
 //private Button ;
 ImageButton button,button2,button3,button9,button8,button5,button4,button6,button7,button11;//,button10;
 private TextView text;
 EditText edtxt;
 String snd;
 static AlertDialog alertDialog;
 Socket sock;
 //InputStream inputStream;
 PrintWriter toclient;
 byte[] mybytearray = new byte[1024];    //create byte array
 int bytesRead;
 @SuppressLint("NewApi")
@Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  //text.setTextColor(00);
  button2=(ImageButton)findViewById(R.id.imageButton1);
  button = (ImageButton)findViewById(R.id.imageButton3);   //reference to the send button
  button3 = (ImageButton)findViewById(R.id.imageButton2);
  button4 = (ImageButton)findViewById(R.id.imageButton7);
  button5 = (ImageButton)findViewById(R.id.imageButton6);
  button6 = (ImageButton)findViewById(R.id.imageButton8);
  button7 = (ImageButton)findViewById(R.id.imageButton9);
  button8 = (ImageButton)findViewById(R.id.imageButton5);
  button9 = (ImageButton)findViewById(R.id.imageButton4);
  button11 = (ImageButton)findViewById(R.id.imageButton11);
  ActionBar bar = getActionBar();
//for color
bar.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
//for image
//bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.settings_icon));
  //button11.setVisibility(View.GONE);
  mInitialized = false;
  mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
  mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
  mSensorManager.registerListener(this, mAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
  text = (TextView) findViewById(R.id.textView2);   //reference to the text view
  text.setTextColor(Color.WHITE);
  edtxt = (EditText) findViewById(R.id.amount1);
  alertDialog = new AlertDialog.Builder(this).create();
	 alertDialog.setTitle("RMC...");
	 try {
		 sock = new Socket("192.168.43.55",6789);
	} catch (UnknownHostException e1) {
		alertDialog.setMessage("The Remote Server might not be started...");
	    alertDialog.show();
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
  //Button press event listener
  button.setOnClickListener(new View.OnClickListener() {
 
   public void onClick(View v) {
	   try {
		   String msg = "C";
		   
	     	send(msg.toString());
	     	edtxt.requestFocus();
	     	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	     	imm.showSoftInput(edtxt, InputMethodManager.SHOW_IMPLICIT);
			
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}  
   }
  });
  
  edtxt.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "A";
			   flag=1;
		     	send(msg.toString());
		     	
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  
  button2.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "H";
		     	send(msg.toString());
		     	  //button11.setVisibility(View.VISIBLE);
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  
  button11.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "W";
		     	send(msg.toString());
		     	 BackgroundTask task = new BackgroundTask(MainActivity.this);
		    	 task.execute();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  
  button3.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "U";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  
  button4.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "O";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  
  button5.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "L";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  
  button6.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "R";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}  
	   }
	  });
  button7.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "D";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//text.setText("Exception :"+e.toString());
				//alertDialog.setMessage("Exception :"+e.toString());
			    ///alertDialog.show();
			}  
	   }
	  });
  
  button8.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "B";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//text.setText("Exception :"+e.toString());
				//alertDialog.setMessage("Exception :"+e.toString());
			    //alertDialog.show();
			}  
	   }
	  });
  
  button9.setOnClickListener(new View.OnClickListener() {
	  
	   public void onClick(View v) {
		   try {
			   String msg = "X";
		     	send(msg.toString());
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//text.setText("Exception :"+e.toString());
				//alertDialog.setMessage("Exception :"+e.toString());
			    //alertDialog.show();
			}  
	   }
	  });
   
  edtxt.setOnKeyListener(new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {

			// if keydown and "enter" is pressed
			if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
				// display a floating message
		        send(edtxt.getText().toString().substring(edtxt.getText().length()-1));	        
		        send("$");
				enter = "en";
				//toclient.flush();
				//toclient.close();
				edtxt.setText("");
				return true;
			} 
			return false;
		}
	});

  
  edtxt.addTextChangedListener(new TextWatcher() {          
	@Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {                                   
            	
		    try {		  	
		    		if(edtxt.getText().length()>=0){
		    			
		    			if(previous!=(edtxt.getText().length())-1)
		  	    		 {
		      			   send("B");
		  	    		 }
		      		  else {	 
		      			snd = s.toString().substring(edtxt.getText().length()-1);
		      			send(snd);
		        }		    
		    	previous = edtxt.getText().length();
		    	}
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(!e.toString().contains("StringIndexOutOfBoundsException"))
				{
				text.setText("Exception :"+e.toString());
				alertDialog.setMessage("Exception :"+e.toString());
			    alertDialog.show();
				}
			}
    	  //track=count;
      }                       
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count,
              int after) {
    	  
          // TODO Auto-generated method stub                          
      }                       
      @Override
      public void afterTextChanged(Editable s) {
    	  // TODO Auto-generated method stub    
    	 
      }
  });
  
 }
 
 protected void onResume() {
     super.onResume();
     mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
 }

 protected void onPause() {
     super.onPause();
     mSensorManager.unregisterListener(this);
 }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// can be safely ignored for this demo
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			mInitialized = true;
		} else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE) deltaX = (float)0.0;
			if (deltaY < NOISE) deltaY = (float)0.0;
			if (deltaZ < NOISE) deltaZ = (float)0.0;
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			if(deltaX>=6.4 && deltaY>=6.4 && deltaZ>=6.4){
				 try {
					   String msg = "W";
				     	send(msg.toString());
				     	 BackgroundTask task = new BackgroundTask(MainActivity.this);
				    	 task.execute();
				    } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//text.setText("Exception :"+e.toString());
						//alertDialog.setMessage("Exception :"+e.toString());
					    //alertDialog.show();
					} 
				 }

			if (deltaX > deltaY) {
			//	iv.setImageResource(R.drawable.horizontal);
			} else if (deltaY > deltaX) {
			//	iv.setImageResource(R.drawable.vertical);
			} else {
				//iv.setVisibility(View.INVISIBLE);
			}
		}
	}
 
 @Override
	public void onBackPressed() {
		//Display alert message when back button has been pressed
	 try {
		 if(flag==1){
		   String msg = "A";
		   flag=0;
	     	send(msg.toString());
	     	edtxt.setText("");
		 }
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//text.setText("Exception :"+e.toString());
			//alertDialog.setMessage("Exception :"+e.toString());
		    //alertDialog.show();
		}  
		return;
	}
 
 public void showDialog(View v)
 {

 	final CharSequence[] items={"Shutdown","Reboot","Suspend"};
 	AlertDialog.Builder builder=new AlertDialog.Builder(this);
 	builder.setTitle("RMC System");
 	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					  send(cmd.toString());
				    } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//text.setText("Exception :"+e.toString());
						//alertDialog.setMessage("Exception :"+e.toString());
					    //alertDialog.show();
					}
			}
		});
 	
  	builder.setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				if("Shutdown".equals(items[which]))
				{
					 cmd="sh";
				}
				else if("Reboot".equals(items[which]))
				{
					cmd="rb";
					}
				else if("Suspend".equals(items[which]))
				{
					cmd="ss";
				}
				
			}
		});
 	builder.show();
 
 }

  static void Recfile() throws Exception
 {
	  try {
   		MainActivity.clientsock = new Socket("192.168.43.55",6788);
   		
   	} catch (UnknownHostException e) {
   		alertDialog.setMessage("Problem in Network Connection,Try Again.");
   		alertDialog.show();
   		//textview.setText("Exception1 :"+e.toString());		
   	} catch (IOException e) {
   		alertDialog.setMessage("Problem in Network Connection,Try Again.");
   		alertDialog.show();
   		//textview.setText("Exception2 :"+e.toString());		
   	}
   	String arg[] = MainActivity.FileName.toString().split("::");
   	FileOutputStream fileOutputStream;
   	 BufferedOutputStream bufferedOutputStream;
       //text.setText(String.valueOf(FileName.length()));
   	 long filesize = Long.parseLong(arg[1].trim());
   	 int bytesRead;
   	 int current = 0;
   		//text.setText("Recieving File....");		
   	String outputPath=Environment.getExternalStorageDirectory().getAbsolutePath();

   	byte[] mybytearray = new byte[(int) (filesize+1)];    //create byte array to buffer the file
   		
   	inputStream = clientsock.getInputStream();
		
        // create a File object for the parent directory
           File wallpaperDirectory = new File(outputPath+"/RMCData/");
           // have the object build the directory structure, if needed.
           wallpaperDirectory.mkdirs();
           // create a File object for the output file
          fileOutputStream = new FileOutputStream(outputPath+"/RMCData/"+arg[0].trim());
           bufferedOutputStream = new BufferedOutputStream(fileOutputStream);  
           bytesRead = MainActivity.inputStream.read(mybytearray, 0, mybytearray.length);
           current = bytesRead;
    
           do {
               bytesRead = MainActivity.inputStream.read(mybytearray, current, (mybytearray.length - current));
               if (bytesRead >= 0) {
                   current += bytesRead;
               }
           } while (bytesRead > -1);
    
           bufferedOutputStream.write(mybytearray, 0, current);
           bufferedOutputStream.flush();
           bufferedOutputStream.close();
           clientsock.close();
           inputStream.close();
           
         //sock.close();
 }
 
void send(String msg)
{
	 try {
		 	sock = new Socket("192.168.43.55",6789);
			toclient = new PrintWriter(sock.getOutputStream(),true);
			toclient.print(msg);
			toclient.flush();
			BufferedReader frm = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			FileName =  frm.readLine();
			//alertDialog.setMessage("FileName :"+FileName);
	        //alertDialog.show();
			toclient.close();
			sock.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//text.setText("Exception :"+e1.toString());
			alertDialog.setMessage("The Remote Server might not be started...");
		    alertDialog.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			//text.setText("Exception :"+e1.toString());
			alertDialog.setMessage("Exception :"+e1.toString());
		    alertDialog.show();
		}	 
	 }
}

class BackgroundTask extends AsyncTask <Void, Void, Void> {
    private ProgressDialog dialog;
    private AlertDialog alertDialog;

    public BackgroundTask(MainActivity mainActivity) {
        dialog = new ProgressDialog(mainActivity);
        alertDialog = new AlertDialog.Builder(mainActivity).create();
   	 	alertDialog.setTitle("RMC...");
	}

	@Override
    protected void onPreExecute() {
        dialog.setMessage("Recieving File, please wait...");
        dialog.show();
        //String arg[] = MainActivity.FileName.toString().split("::");
        //alertDialog.setMessage(arg[0]+"  "+arg[1]);
        // alertDialog.show();
    }
     
    @Override
    protected void onPostExecute(Void result) {
        if (dialog.isShowing()) {
            dialog.dismiss();            
            alertDialog.setMessage("File Recieved Successfully...");
            alertDialog.show();
        }
    }
     
 
    @Override
    protected Void doInBackground(Void... params) {
    	try {
			MainActivity.Recfile();
			 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    			return null;
 
        //return null;
    }
     
}
