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
        byte[] data = packet.getData();

        this.isHeader = data[0]%2 == 0;
        this.isEnd = data[0]%4 == 3;
        this.fileID = data[1];

        if(isHeader){
            this.fileName = new byte[packet.getLength() - 2];
            for(int i = 2; i < packet.getLength(); i ++){
                fileName[i-2] = data[i];
            }
        } else {
            int a = data[2];
            int b = data[3];
            if(a<0){
                a+= 256;
            }
            if(b<0){
                b+=256;
            }
            this.packetNumber = a*256 + b;

            this.contents = new byte[packet.getLength() - 4];
            for(int i = 4; i < packet.getLength(); i ++){
                contents[i-4] = data[i];
            }
        }
    }

    @Override
    public int compareTo(OurPacket other){
        return this.packetNumber - other.packetNumber;
    }
}