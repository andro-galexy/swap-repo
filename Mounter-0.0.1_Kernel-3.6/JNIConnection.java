/*Application Name: Mounter
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo
E-mail: swapnil.udapure5@gmail.com*/

/*     */ import java.awt.Frame;
/*     */ import java.awt.Image;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.UIManager.LookAndFeelInfo;
/*     */ import javax.swing.UnsupportedLookAndFeelException;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class JNIConnection
/*     */   implements Runnable
/*     */ {
/*     */   private native int main();
/*     */ 
/*     */   public JNIConnection()
/*     */     throws Exception
/*     */   {
/*  16 */     File localFile1 = new File(System.getenv("HOME") + "/.mounter" + "/mounter");
/*  17 */     File localFile2 = new File(System.getenv("HOME") + "/.mounter");
/*  18 */     if (!localFile2.exists()) {
/*  19 */       if (localFile2.mkdir())
/*  20 */         System.out.println("Directory created!");
/*     */       else {
/*  22 */         System.out.println("Failed to create directory!");
/*     */       }
/*     */     }
/*     */ 
/*  26 */     if (!localFile1.exists()) {
/*  27 */       if (localFile1.createNewFile())
/*  28 */         System.out.println("file created!");
/*     */       else {
/*  30 */         System.out.println("Failed to create file!");
/*     */       }
/*     */     }
/*  33 */     new Thread(this).start();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/*  40 */       String[] arrayOfString = { "notify-send", "-i", System.getenv("HOME") + "/.mounter/Lock-icon.png", "Mounter Application", "Thread Started..." };
/*  41 */       Runtime.getRuntime().exec(arrayOfString);
/*     */     }
/*     */     catch (IOException localIOException) {
/*  44 */       System.out.println("IOException");
/*  45 */       localIOException.printStackTrace();
/*     */     }
/*  47 */     int i = main();
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) throws Exception
/*     */   {
/*  52 */     new JNIConnection();
/*     */   }
/*     */ 
/*     */   public static String MD5Hash(String paramString)
/*     */     throws NoSuchAlgorithmException
/*     */   {
/*  62 */     MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/*  63 */     localMessageDigest.update(paramString.getBytes());
/*     */ 
/*  65 */     byte[] arrayOfByte = localMessageDigest.digest();
/*     */ 
/*  68 */     StringBuffer localStringBuffer = new StringBuffer();
/*  69 */     for (int i = 0; i < arrayOfByte.length; i++) {
/*  70 */       localStringBuffer.append(Integer.toString((arrayOfByte[i] & 0xFF) + 256, 16).substring(1));
/*     */     }
/*  72 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static void setIp(String paramString1, String paramString2)
/*     */   {
/*  77 */     System.out.println("dev_name: " + paramString1);
/*  78 */     System.out.println("dir_name: " + paramString2);
/*     */     try {
/*  80 */       String[] arrayOfString = { "notify-send", "-i", System.getenv("HOME") + "/.mounter/Lock-icon.png", "Mounter Application", "Partition: " + paramString1 + "\nMount Point: " + paramString2 };
/*  81 */       Runtime.getRuntime().exec(arrayOfString);
/*     */     }
/*     */     catch (IOException localIOException1) {
/*  84 */       System.out.println("IOException");
/*  85 */       localIOException1.printStackTrace();
/*  88 */     }
/*     */ String str1 = System.getenv("HOME") + "/.mounter/mounter";
/*     */     try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
/*     */     try {
/* 103 */       ImageIcon localImageIcon = new ImageIcon(System.getenv("HOME") + "/.mounter/Lock-icon.png", "a pretty but meaningless splat");
/* 104 */       Image localImage1 = localImageIcon.getImage();
/* 105 */       Image localImage2 = localImage1.getScaledInstance(65, 65, 4);
/* 106 */       localImageIcon = new ImageIcon(localImage2);
/*     */ 
/* 110 */       final FileOutputStream fos = new FileOutputStream(str1);
/* 111 */       FileInputStream localFileInputStream = null;
/* 112 */       String str2 = "";
/* 113 */       String str3 = ""; String str4 = "";
/* 114 */       File localFile = new File(System.getenv("HOME") + "/.mounter" + "/config");
/*     */ 
/* 116 */       byte[] arrayOfByte = new byte[(int)localFile.length()];
/*     */       try
/*     */       {
/* 120 */         localFileInputStream = new FileInputStream(localFile);
/* 121 */         localFileInputStream.read(arrayOfByte);
/* 122 */         localFileInputStream.close();
/*     */ 
/* 124 */         for (int k = 0; k < arrayOfByte.length; k++) {
/* 125 */           str2 = str2 + String.valueOf((char)arrayOfByte[k]);
/*     */         }
/*     */ 
/* 128 */         System.out.println(str2);
/*     */       } catch (Exception localException) {
/* 130 */         localException.printStackTrace();
/*     */       }
/*     */       try
/*     */       {
/* 134 */         JSONObject localJSONObject = new JSONObject(str2);
/* 135 */         if (localJSONObject.has(paramString1))
/*     */         {
/* 137 */           str4 = localJSONObject.getString(paramString1);
/*     */ 
/* 139 */           final JOptionPane pane = new JOptionPane(null, 1, -1, localImageIcon, null, null);
/*     */ 
/* 145 */           Timer localTimer = new Timer(20000, new ActionListener() {
/*     */             public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 147 */               System.out.println("expire");
/* 148 */               pane.setVisible(false);
/* 149 */               JOptionPane.getRootFrame().dispose();
/*     */               try {
/* 151 */                 fos.write("no".getBytes());
/* 152 */                 fos.close();
/*     */               }
/*     */               catch (IOException localIOException)
/*     */               {
/* 156 */                 System.out.println("IOException : " + localIOException);
/*     */               }
/*     */             }
/*     */           });
/* 160 */           localTimer.setRepeats(false);
/* 161 */           localTimer.start();
/* 162 */           String str5 = (String)JOptionPane.showInputDialog(pane, "Partition \"" + paramString1 + "\" Request for Mounting.\nEnter SecurityKey: ", "Mounter@swap", -1, localImageIcon, null, "Password");
/*     */ 
/* 169 */           localTimer.stop();
/*     */           try
/*     */           {
/* 172 */             str3 = MD5Hash(str5);
/*     */           } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/* 174 */             System.out.println(localNoSuchAlgorithmException.toString());
/*     */           }
/*     */         }
/*     */       } catch (JSONException localJSONException) {
/* 178 */         System.out.println(localJSONException.toString());
/*     */       }
/* 180 */       if (str4.equals(str3))
/*     */       {
/* 182 */         fos.write("ok".getBytes());
/* 183 */         fos.close();
/*     */       }
/*     */       else
/*     */       {
/* 187 */         fos.write("no".getBytes());
/* 188 */         fos.close();
/*     */       }
/*     */     }
/*     */     catch (FileNotFoundException localFileNotFoundException)
/*     */     {
/* 193 */       System.out.println("FileNotFoundException : " + localFileNotFoundException);
/*     */     }
/*     */     catch (IOException localIOException2)
/*     */     {
/* 197 */       System.out.println("IOException : " + localIOException2);
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  57 */     System.load(System.getenv("HOME") + "/.mounter/netlinkuser.so");
/*     */   }
/*     */ }

