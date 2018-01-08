import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TCPsender {
	
	public static String sendTCP(String ip, int port, int timeout, String content) {
		 String ipaddress = ip;
	     int portnumber = port;
	     String modifiedSentence;
	     Socket clientSocket;
	     try
	     {
	         clientSocket = new Socket(ipaddress, portnumber);
	         DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	         BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	         outToServer.writeBytes(content + '\n');
	         clientSocket.setSoTimeout(timeout);
	         modifiedSentence = inFromServer.readLine();
	         clientSocket.close();
	             outToServer.close();
	         inFromServer.close();
	     }
	     catch (Exception exc)
	     {
	          modifiedSentence = "";
	     }
	          return modifiedSentence;
	}

	public static void main(String[] args) {
		JFrame guiFrame = new JFrame();

		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Example GUI");
		guiFrame.setSize(600, 250);
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setLayout(null);

		JLabel ipLabel = new JLabel("IP");
		ipLabel.setBounds(10,10,80,20);
		JTextField ipCampo = new JTextField();
		ipCampo.setBounds(100,10,100,20);
		
		JLabel puertoLabel = new JLabel("Puerto");
		puertoLabel.setBounds(10,40,80,20);
		JTextField puertoCampo = new JTextField();
		puertoCampo.setBounds(100, 40, 100, 20);
		
		JLabel mensajeLabel = new JLabel("Mensaje");
		mensajeLabel.setBounds(10,70,80,20);
		JTextField mensajeCampo = new JTextField();
		mensajeCampo.setBounds(100, 70, 400, 20);

		JButton boton = new JButton("Enviar");
		boton.setBounds(10, 110, 100, 50);
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*JOptionPane.showMessageDialog(null, "IP = " + ipCampo.getText() + ", puerto = " + puertoCampo.getText()
						+ ", mensaje = " + mensajeCampo.getText());*/
				String res = sendTCP(ipCampo.getText(), Integer.parseInt(puertoCampo.getText()), 5000, mensajeCampo.getText());
				JOptionPane.showMessageDialog(null, res);
			}
		});
		
		guiFrame.add(ipLabel);
		guiFrame.add(ipCampo);
		
		guiFrame.add(puertoLabel);
		guiFrame.add(puertoCampo);
		
		guiFrame.add(mensajeLabel);
		guiFrame.add(mensajeCampo);
		guiFrame.add(boton);
		
		guiFrame.setVisible(true);

	}

}
