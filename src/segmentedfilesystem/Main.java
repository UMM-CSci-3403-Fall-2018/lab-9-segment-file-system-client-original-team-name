package segmentedfilesystem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class Main {
    private static HashMap<Integer,PacketContainer> packetContainerMap = new HashMap<Integer, PacketContainer>();

    private static boolean filesAreComplete(){
        for(PacketContainer packetContainer : packetContainerMap.values()){
            if(!packetContainer.isComplete()){
                //System.out.println("Failure: " + packetContainer.id);
                return false;
            }
        }
        return packetContainerMap.size() != 0;
    }

    public static void main(String[] args) throws Exception{
        System.out.println("Starting client..");
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

        //Reset packet
        packet = new DatagramPacket(buf, buf.length);

        System.out.println("Setup complete.");
        System.out.println("Start receiving packets...");

        int tracker = 0;
        while(!filesAreComplete()){
            if(tracker%100==0){
                System.out.println("Processed: " + tracker);
            }
            tracker++;
            socket.receive(packet);
            int packetID = packet.getData()[1];
            OurPacket ourPacket = new OurPacket(packet);

            if(packetContainerMap.containsKey(packetID)) {
                packetContainerMap.get(packetID).addPacket(ourPacket);
            }
            else {
                PacketContainer packetContainer = new PacketContainer();
                packetContainerMap.put(packetID,packetContainer);
                packetContainer.addPacket(ourPacket);
                System.out.println(" - - - new file found.");
            }
        }

        System.out.println("Starting to generate files...");
        //Generate files
        for(PacketContainer packetContainer : packetContainerMap.values()){
            System.out.println("Handling: " + packetContainer.filename);

            packetContainer.generateFile();
        }

        System.out.println("Packets were received.");
    }
}
