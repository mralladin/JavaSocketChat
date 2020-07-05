package ChatApp;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {

	
	private ConnectionThread connThread = new ConnectionThread();
	private Consumer<Serializable> onReceive;
	protected abstract boolean isServer();
	protected abstract String getIP();
	protected abstract int getPort();
	
	
	
	
	public NetworkConnection(Consumer<Serializable> onReceive) {
		// TODO Auto-generated constructor stub
		this.onReceive= onReceive;
		connThread.setDaemon(true);
		
	}
	
	
	
	public void  startConnection() throws Exception{
		connThread.start();
	}
	
	public void send(Serializable data) throws Exception{
		connThread.out.writeObject(data);
	}
	
	public void closeConnection() throws Exception{
		connThread.socket.close();
	}
	
	
	private class ConnectionThread extends Thread{
		private Socket socket;
		private ObjectOutputStream out;
		
		
		public void run(){
			try(ServerSocket server = isServer()? new ServerSocket(getPort()):null;
					Socket socket = isServer() ? server.accept(): new Socket(getIP(), getPort());
					ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in= new ObjectInputStream(socket.getInputStream())){
						
						this.socket= socket;
						this.out= out;
						socket.setTcpNoDelay(true);
						
						while(true){
							Serializable data = (Serializable) in.readObject();
							onReceive.accept(data);
							
							
							
						}
				
				
				
					}
					catch(Exception e){
						onReceive.accept("Verbindung abgebrochen");
					}
				
				
			
			
			
			
		 }
		
		
	}
	
	
	
}
