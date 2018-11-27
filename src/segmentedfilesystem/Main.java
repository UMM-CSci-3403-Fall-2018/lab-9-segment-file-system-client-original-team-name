package segmentedfilesystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

public class Main {
    ArrayList<OurPacket> ourPackets = new ArrayList<OurPacket>();

    public static void main(String[] args) throws Exception{

        //Create client Socket
        DatagramSocket clientSocket = new DatagramSocket(6014);

        //Package request data
        byte[] receiveData = new byte[8];
        String sendString = "polo";
        byte[] sendData = sendString.getBytes("UTF-8");
        DatagramPacket recievePacket = new DatagramPacket(receiveData,receiveData.length);

        //Collect packets
        while(true){
            clientSocket.receive(recievePacket);

            OurPacket ourPacket = new OurPacket(recievePacket);
        }
    }

    public void splitFilesApart(){
        //Split ourPackets based on file
    }

}
