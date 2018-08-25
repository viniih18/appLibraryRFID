package app;

import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

/**
 * 
 * @author mavti
 *
 */
public class JSerialCom {
	
	public static String devicePortName = "Nome da porta"; //nome da porta do RFID ou arduino
	public static SerialPort arduinoPort = null; //Porta do arduino
	public static InputStream arduinoStream = null; //
	public static int PACKET_SIZE_IN_BYTES = 100;
	
	public static void main(String[] args) {
		
		int len = SerialPort.getCommPorts().length; //quantidade de portas
		SerialPort serialPorts[] = new SerialPort[len];
		serialPorts = SerialPort.getCommPorts(); //pega as portas do dispositivo
		
		for(int i = 0; i < len; i++) {
			
			String portName = serialPorts[i].getDescriptivePortName(); //pega o nome da porta
			System.out.println(serialPorts[i].getSystemPortName() + ": " + portName + ": " + i);
			
			//caso seja a porta informada no inicio do codigo ele ira abrir uma conexao com a mesma
			if(portName.contains(devicePortName)) {
				arduinoPort = serialPorts[i];
				arduinoPort.openPort();
				System.out.println("Conectado: " + portName + "[" + i + "]");
				break;
			}
		}
		
		PacketListener listener = new PacketListener();
		arduinoPort.addDataListener(listener);
		
	}

}
