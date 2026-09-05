import java.net.InetAddress;

public class TesteIP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TesteIP ti = new TesteIP();
		ti.testarIp();
	}

	public void testarIp() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
	}

}
