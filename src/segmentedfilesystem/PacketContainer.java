package segmentedfilesystem;

import java.util.ArrayList;
import java.util.Collections;

public class PacketContainer {
    ArrayList<OurPacket> packetList = new ArrayList<OurPacket>();
    String filename;
    boolean foundHeader = false;
    boolean correctNumber = false;
    int desiredPacketNumber = Integer.MAX_VALUE;

    public void addPacket(OurPacket packet){
        if(packet.isHeader){
            filename = new String(packet.fileName);
            foundHeader = true;
        }

        if(packet.isEnd){
            desiredPacketNumber = packet.packetNumber + 1;
        }

        if(packetList.size() == desiredPacketNumber){
            correctNumber = true;
        }

        packetList.add(packet);
    }

    public boolean isComplete(){
        return foundHeader && correctNumber;
    }

    //TODO
    public void generateFile(){
        Collections.sort(packetList);
        //Generates the file based on data stored in the packetList
    }


}
