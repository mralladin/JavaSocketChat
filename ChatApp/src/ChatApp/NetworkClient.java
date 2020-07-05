package ChatApp;
import java.io.Serializable;
import java.util.function.Consumer;

public class NetworkClient extends NetworkConnection {

	private String ipaddress;
	private int port;
	
	public NetworkClient(String ipaddress,int port, Consumer<Serializable> onReceive) {
		super(onReceive);
		this.ipaddress=ipaddress;
		this.port=port;
	}

	@Override
	protected boolean isServer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getIP() {
		// TODO Auto-generated method stub
		return this.ipaddress;
	}

	@Override
	protected int getPort() {
		// TODO Auto-generated method stub
		return this.port;
	}

}
