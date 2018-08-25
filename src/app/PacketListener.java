package app;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
/**
 * 
 * @author mavti
 *
 * Implementacao da classe da biblioteca para pegar as informações do RFID 
 */
public final class PacketListener  implements SerialPortPacketListener{
	
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}
	
	public int getPacketSize() {
		return JSerialCom.PACKET_SIZE_IN_BYTES;
	}
	
	public void serialEvent(SerialPortEvent event) {
		byte[] newData = event.getReceivedData();
		String str = new String(newData);
		int byteSize = 0;
		
		try {
            byteSize = str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PacketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
		//if (byteSize == JSerialCom.PACKET_SIZE_IN_BYTES) {
        System.out.println("(Tamanho: " + byteSize + ")" + str);
        System.out.println("Informacao: " + str);
        //}
	}
	

}
