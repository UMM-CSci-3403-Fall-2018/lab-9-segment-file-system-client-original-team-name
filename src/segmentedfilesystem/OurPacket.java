package segmentedfilesystem;

import java.net.DatagramPacket;

public class OurPacket implements Comparable<OurPacket>{
    boolean isHeader;
    boolean isEnd;
    int packetNumber;
    byte[] contents;
    byte[] fileName;
    int fileID;

    public OurPacket(DatagramPacket packet){
        packet.getLength();
        byte[] data = packet.getData();

        this.isHeader = data[0]%2 == 0;
        this.isEnd = data[0]%3 == 0;
        this.fileID = data[1];

        if(isHeader){
            for(int i = 2; i < data.length; i ++){
                fileName[i-2] = data[i];
            }
        } else {
            this.packetNumber = data[2];
            for(int i = 3; i < data.length; i ++){
                contents[i-3] = data[i];
            }
        }
    }

    @Override
    public int compareTo(OurPacket other){
        return this.packetNumber - other.packetNumber;
    }
}