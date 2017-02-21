import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Mapping {

public static ArrayList<String> addresses = new ArrayList<String>();
public static ArrayList<String> deviceNames=new ArrayList<String>();

public static void main(String[] args) {
//Call the N-Map function to analyze the network
while(true)
AnaLyzeNetwork();
}

public static void AnaLyzeNetwork(){
try {
deviceNames.clear();
Runtime rt = Runtime.getRuntime();
Process pr = rt.exec("nmap -sn -oG ip.txt 192.168.1.*");
BufferedReader input = new BufferedReader(
new InputStreamReader(pr.getInputStream()));
String line = null;
while((line=input.readLine()) != null) {
if (line.contains("Nmap scan report for")){
String[] elements = line.split(" ");
int end = elements.length-1;
String ip_address = elements[end];
String line2 = input.readLine();
if (line2.contains("Host is up")){
addresses.add(ip_address);
String line3=input.readLine();
if(line3.startsWith("MAC"))
deviceNames.add(line3.substring(line3.indexOf(": ")+1, line3.indexOf(" (")).trim());
}
}
}
System.out.println("device id"+deviceNames.toString());
for(String j:addresses)
System.out.println(j);
//for(String i:deviceNames)
//System.out.println(i);

File fnew=new File("device.txt");
//fold.delete();
//File fnew=new File("device.txt");
try{
FileWriter f2= new FileWriter(fnew, false);
PrintWriter pw= new PrintWriter(f2);
pw.write(deviceNames.toString());
pw.flush();
pw.close();
//System.out.println("device id"+deviceNames.toString());
f2.close();

}
catch(IOException ex){
ex.printStackTrace();
}

int exitVal = pr.waitFor();
if(exitVal!=0){
System.out.println("Exited with error code "+exitVal);
}
else{
System.out.println("Network Analyzed Successfully.");
}
}
catch(Exception e){
e.printStackTrace();
System.exit(0);
}
}

}

