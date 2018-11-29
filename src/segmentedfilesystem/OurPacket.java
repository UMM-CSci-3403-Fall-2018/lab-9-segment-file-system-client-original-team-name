package segmentedfilesystem;

import java.net.DatagramPacket;

public class OurPacket implements Comparable<OurPacket>{
    boolean isHeader;
    int packetNumber;
    byte[] contents;
    int fileID;

    public OurPacket(DatagramPacket packet){
        packet.getLength();
        byte[] data = packet.getData();

        this.isHeader = data[0]%2 == 0;
        this.fileID = data[1];

        if(isHeader){

        } else {
            this.packetNumber = data[2];
            for(int i = 3; i < data.length; i ++){
                contents[i-3] = data[i];
            }
        }
    }

    @Override
    public int compareTo(OurPacket packet){
        return packetNumber - packet.packetNumber;
    }
}