package segmentedfilesystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Main {
    private static HashMap<Integer,PacketContainer> packetMap = new HashMap<Integer, PacketContainer>();


    public static void main(String[] args) throws Exception{
        //Create socket
        DatagramSocket socket = new DatagramSocket();

        //Setup request data
        byte[] buf = new byte[1024];
        InetAddress address = InetAddress.getByName("heartOfGold.morris.umn.edu");
        int port = 6014;

        //Create request packet
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

        //Request
        socket.send(packet);

        //Reset packet??
        packet = new DatagramPacket(buf, buf.length);

        while(true){
            socket.receive(packet);
            int packetID = packet.getData()[1];
            OurPacket ourPacket = new OurPacket(packet);


            if(packetMap.containsKey(packetID)) {
                packetMap.get(packetID).addPacket(ourPacket);
            }
            else {
                PacketContainer packetContainer = new PacketContainer();
                packetMap.put(packetID,packetContainer);
                packetContainer.addPacket(ourPacket);
            }

            //String received = new String(packet.getData(), 0, packet.getLength());
            //System.out.println("Quote of the Moment: " + received);
        }

    }
}
