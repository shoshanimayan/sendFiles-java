import java.io.*;
import java.net.*;

public class Server {

  public final static int SOCKET_PORT =  9099;  // you may change this
  public static String TO_SEND = "C:\\Users\\Shoshani\\Documents\\";  // you may change this root

  public static void main (String [] args ) throws IOException {
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream outs = null;
    ServerSocket ssock = null;
    Socket sock = null;
    Console c = System.console();
    try {
      ssock = new ServerSocket(SOCKET_PORT);
      while (true) {
        System.out.println("Waiting...");
        try {
          sock = ssock.accept();
          System.out.println("Accepted connection : " + sock);
          // send file
          String file;
          file=c.readLine();
          TO_SEND= TO_SEND+file;
          File myFile = new File (TO_SEND);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          outs = sock.getOutputStream();
          System.out.println("Sending " + TO_SEND + "(" + mybytearray.length + " bytes)");
          outs.write(mybytearray,0,mybytearray.length);
          outs.flush();
          System.out.println("Done.");
        }
        finally {
          if (bis != null) {bis.close();}
          if (outs != null) {outs.close();}
          if (sock!=null) {sock.close();}
        }
        continue;
      }
    }
    finally {
      if (ssock != null){ ssock.close();}
    }
  }
}
