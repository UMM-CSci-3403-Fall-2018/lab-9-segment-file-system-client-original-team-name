package segmentedfilesystem;

import java.util.ArrayList;

public class PacketContainer {
    ArrayList<OurPacket> packetList = new ArrayList<OurPacket>();

    public void addPacket(OurPacket packet){
        packetList.add(packet);
    }

    //TODO please fix this
    public boolean isComplete(){
        return true;
    }

    //TODO
    public void sort(){
        //packetList.sort();
    }

    //TODO
    public void generateFile(){
        //Generates the file based on data stored in the packetList
    }


}
