package app;

import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class JSerialCom {
	
	public static String devicePortName = "Nome da porta"; //nome da porta: USB
	public static SerialPort arduinoPort = null; //Porta do arduino
	public static InputStream arduinoStream = null; //
	public static int PACKET_SIZE_IN_BYTES = 8;
	
	public static void main(String[] args) {
		
		int len = SerialPort.getCommPorts().length;
		SerialPort serialPorts[] = new SerialPort[len];
		serialPorts = SerialPort.getCommPorts();
		
		for(int i = 0; i < len; i++) {
			
			String portName = serialPorts[i].getDescriptivePortName();
			System.out.println(serialPorts[i].getSystemPortName() + ": " + portName + ": " + i);
			
			if(portName.contains(devicePortName)) {
				arduinoPort = serialPorts[i];
				arduinoPort.openPort();
				System.out.println("Conectado: " + portName + "[" + i + "]");
				break;
			}
		}
		
		ClassHelp classHelp = new ClassHelp();
		arduinoPort.addDataListener(classHelp);
		
	}

}
