package segmentedfilesystem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PacketContainer {
    private ArrayList<OurPacket> packetList = new ArrayList<OurPacket>();
    public String filename;
    public int id = 500;
    private boolean foundHeader = false;
    private boolean correctNumber = false;
    private int desiredPacketNumber = Integer.MAX_VALUE;

    public void addPacket(OurPacket packet){
        if(this.id == 500){
            this.id = packet.fileID;
        }

        if(packet.isHeader){
            System.out.println("Header file found. "  + id);
            filename = new String(packet.fileName);
            foundHeader = true;
        }

        if (packet.isEnd){
            desiredPacketNumber = packet.packetNumber;
            System.out.println("Desired packet number callibrated. " + desiredPacketNumber + " " + id);
        }

        if(packetList.size() == desiredPacketNumber){
            correctNumber = true;
            System.out.println("Desired packet number reached. "  + id);
        }

        if(!packet.isHeader){
            packetList.add(packet);
        }
    }

    public boolean isComplete(){
        return foundHeader && correctNumber;
    }

    public void generateFile(){
        System.out.println("Attempting to generate: " + filename);
        Collections.sort(packetList);
        try{
            FileOutputStream out = new FileOutputStream(filename);
            for(OurPacket ourPacket : packetList){
                ///out.write("<<<".getBytes());
                out.write(ourPacket.contents);
                ///out.write(">>>".getBytes());
            }
            out.close();
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
