package segmentedfilesystem;

import static org.junit.Assert.*;

import org.junit.Test;

import java.net.DatagramPacket;


/**
 * This is just a stub test file. You should rename it to
 * something meaningful in your context and populate it with
 * useful tests.
 */
public class PacketTests{


    @Test
    public void testPacket() {
        byte[] data = new byte[7];
        data[0] =  1;
        data[1] = 15;
        data[2] = 0;
        data[3] = 50;
        data[4] = 't';
        data[5] = 'e';
        data[6] = 's';
        data[7] = 't';
        DatagramPacket datagramPacket = new DatagramPacket(data,7);
        OurPacket ourPacket = new OurPacket(datagramPacket);

        assertEquals(ourPacket.isHeader,false);
        assertEquals(ourPacket.packetNumber,15);
        assertEquals(ourPacket.contents,"test".getBytes());
        assertEquals(ourPacket.fileID,"15");
    }
}

