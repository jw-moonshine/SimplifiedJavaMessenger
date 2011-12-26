package shared;

import java.io.*;
import java.net.Socket;


public class Receiver implements Runnable{

	ObjectInputStream in;
	DataHandler returnTo;
	
	public Receiver(DataHandler returnTo) throws IOException{
		this.in = new ObjectInputStream(returnTo.socket.getInputStream());
		this.returnTo = returnTo;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				this.returnTo.objectReceived(this.in.readObject());
			} catch (IOException e) {
				this.returnTo.disconnect();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e){
				this.returnTo.disconnect();
				e.printStackTrace();
			}
		}
		
	}

	
}