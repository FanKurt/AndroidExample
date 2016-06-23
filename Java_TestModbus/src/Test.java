
import java.net.InetAddress;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.SimpleRegister;

public class Test {
	static WriteSingleRegisterRequest Wreq;
	static WriteSingleRegisterResponse Wres;

	public static void main(String args[]) {

		int port = Modbus.DEFAULT_PORT;
		int ref = 1027;

		SimpleRegister sr = null;
		sr = new SimpleRegister(1);

		try {
			String astr = "192.168.1.1";
			InetAddress addr = InetAddress.getByName(astr);
			TCPMasterConnection con = new TCPMasterConnection(addr); // the
																		// connection
			ModbusTCPTransaction trans = null; // the transaction

			Wreq = new WriteSingleRegisterRequest(ref, sr);
			Wres = new WriteSingleRegisterResponse();
			Wreq.setUnitID(2); //set Slave Address  
			Wres.setUnitID(2); //set Slave Address
			// 2. Open the connection
			con.setPort(port);
			con.connect();
			System.out.println("Connected... " + port + " " + ref);

			trans = new ModbusTCPTransaction(con);
			trans.setRequest(Wreq);

			trans.execute();
			Wres = (WriteSingleRegisterResponse) trans.getResponse();
			System.out.println("The value is: " + Wres.getHexMessage());

			con.close();

		} catch (Exception ex) {
			System.out.print(""+ex);
			ex.printStackTrace();
		}

		System.out.println("Finished");
	}

}