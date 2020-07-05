package ChatApp;
import java.io.Serializable;
import java.util.function.Consumer;

public class NetworkServer extends NetworkConnection {

	private int port;
	
	
	public NetworkServer(int port,Consumer<Serializable> onReceive) {
		super(onReceive);
		this.port=port;
	}

	@Override
	protected boolean isServer() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected String getIP() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getPort() {
		// TODO Auto-generated method stub
		return this.port;
	}

}
