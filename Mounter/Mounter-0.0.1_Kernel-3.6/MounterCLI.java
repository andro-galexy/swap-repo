/*Application Name: Mounter
Version: 0.0.1(Beta version)
Developer&Maintainer: Swapnil J. Udapure
Repository: https://github.com/swapgit/swap-repo
E-mail: swapnil.udapure5@gmail.com*/

/*     */ import java.io.BufferedReader;
/*     */ import java.io.Console;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class MounterCLI
/*     */ {
/*     */   static Console console;
/*  10 */   static FileOutputStream fos = null;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) throws IOException, JSONException {
/*  13 */     File localFile1 = new File(System.getenv("HOME") + "/.mounter" + "/config");
/*  14 */     File localFile2 = new File(System.getenv("HOME") + "/.mounter");
/*  15 */     if ((!localFile2.exists()) && 
/*  16 */       (!localFile2.mkdir()))
/*     */     {
/*  19 */       System.out.println("Failed to create directory!");
/*     */     }
/*     */     Object localObject1;
/*     */     Object localObject2;
/*     */     Object localObject3;
/*     */     Object localObject4;
/*     */     Object localObject5;
/*     */     String str1;
/*     */     Object localObject6;
/*  23 */     if (!localFile1.exists()) {
/*  24 */       if (localFile1.createNewFile()) {
/*  25 */         localObject1 = new ArrayList();
/*  26 */         localObject2 = Runtime.getRuntime();
/*  27 */         localObject3 = new String[] { "sudo", "blkid" };
/*  28 */         localObject4 = ((Runtime)localObject2).exec((String[])localObject3);
/*  29 */         localObject5 = new BufferedReader(new InputStreamReader(((Process)localObject4).getInputStream()));
/*  30 */         str1 = "";
/*  31 */         localObject6 = new JSONObject();
/*  32 */         while ((str1 = ((BufferedReader)localObject5).readLine()) != null)
/*     */         {
/*  34 */           ((JSONObject)localObject6).put(str1.substring(0, str1.indexOf(":")), "");
/*     */         }
/*  36 */         fos = new FileOutputStream(localFile1);
/*  37 */         fos.write(((JSONObject)localObject6).toString().getBytes());
/*  38 */         fos.close();
/*     */       }
/*     */       else {
/*  41 */         System.out.println("Failed to create file!");
/*     */       }
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  47 */       if (paramArrayOfString[0].equals("--help"))
/*     */       {
/*  49 */         System.out.println("Usage : [-n] <Partition_Name> [-p] <Password> \n--help For More Information...\nDeveloped and Maintained by swapnil udapure(swapnil.udapure5@gmail.com)");
/*     */       }
/*  51 */       else if (paramArrayOfString[0].equals("-n"))
/*     */       {
/*  53 */         localObject1 = Runtime.getRuntime();
/*  54 */         localObject2 = new String[] { "sudo", "blkid", paramArrayOfString[1] };
/*  55 */         localObject3 = ((Runtime)localObject1).exec((String[])localObject2);
/*  56 */         localObject4 = new BufferedReader(new InputStreamReader(((Process)localObject3).getInputStream()));
/*  57 */         localObject5 = "";
/*  58 */         localObject5 = ((BufferedReader)localObject4).readLine();
/*     */         try {
/*  60 */           if (((String)localObject5).contains(paramArrayOfString[1]))
/*     */           {
/*  62 */             if (paramArrayOfString[2].equals("-p"))
/*     */             {
/*  64 */               if (paramArrayOfString[3].length() >= 9) {
/*     */                 try
/*     */                 {
/*  67 */                   str1 = "";
/*  68 */                   localObject6 = "";
/*     */ 
/*  70 */                   byte[] arrayOfByte = new byte[(int)localFile1.length()];
/*  71 */                   FileInputStream localFileInputStream = null;
/*  72 */                   String str2 = "";
/*  73 */                   String str3 = "";
/*  74 */                   String str4 = "";
/*     */ 
/*  76 */                   localFileInputStream = new FileInputStream(localFile1);
/*  77 */                   localFileInputStream.read(arrayOfByte);
/*  78 */                   localFileInputStream.close();
/*     */ 
/*  80 */                   for (int i = 0; i < arrayOfByte.length; i++) {
/*  81 */                     str2 = str2 + String.valueOf((char)arrayOfByte[i]);
/*     */                   }
/*  83 */                   JSONObject localJSONObject = new JSONObject(str2);
/*     */ 
/*  85 */                   if (!localJSONObject.getString(paramArrayOfString[1]).equals(""))
/*     */                   {
/*  87 */                     console = System.console();
/*  88 */                     char[] arrayOfChar = console.readPassword("Enter old Password: ", new Object[0]);
/*  89 */                     str3 = String.valueOf(arrayOfChar);
/*  90 */                     if (MD5Hash(str3).equals(localJSONObject.getString(paramArrayOfString[1])))
/*     */                     {
/*  92 */                       str3 = paramArrayOfString[3];
/*     */                     }
/*     */                     else
/*     */                     {
/*  96 */                       System.out.println("Invalid old Password!");
/*  97 */                       System.exit(0);
/*     */                     }
/*     */                   }
/*     */ 
/* 101 */                   str3 = paramArrayOfString[3];
/*     */                   try {
/* 103 */                     localJSONObject.put(paramArrayOfString[1], MD5Hash(str3));
/* 104 */                     localObject6 = localJSONObject.toString();
/*     */                   }
/*     */                   catch (JSONException localJSONException) {
/* 107 */                     System.out.println(localJSONException.toString());
/* 108 */                   }fos = new FileOutputStream(localFile1);
/* 109 */                   fos.write(((String)localObject6).getBytes());
/* 110 */                   fos.close();
/*     */ 
/* 112 */                   System.out.println("Password on \"" + paramArrayOfString[1] + "\" applied successfully...");
/*     */                 }
/*     */                 catch (Exception localException) {
/* 115 */                   System.out.println(localException.toString());
/*     */                 }
/*     */               }
/*     */               else
/*     */               {
/* 120 */                 System.out.println("Password length must be >= 9");
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (NullPointerException localNullPointerException) {
/* 126 */           System.out.println("Partition \"" + paramArrayOfString[1] + "\" doesn't exist...");
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/* 131 */       System.out.println("Usage : [-n] <Partition_Name> [-p] <Password> \n--help For More Information...");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String MD5Hash(String paramString) throws NoSuchAlgorithmException
/*     */   {
/* 137 */     MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/* 138 */     localMessageDigest.update(paramString.getBytes());
/*     */ 
/* 140 */     byte[] arrayOfByte = localMessageDigest.digest();
/*     */ 
/* 143 */     StringBuffer localStringBuffer = new StringBuffer();
/* 144 */     for (int i = 0; i < arrayOfByte.length; i++) {
/* 145 */       localStringBuffer.append(Integer.toString((arrayOfByte[i] & 0xFF) + 256, 16).substring(1));
/*     */     }
/* 147 */     return localStringBuffer.toString();
/*     */   }
/*     */ }

