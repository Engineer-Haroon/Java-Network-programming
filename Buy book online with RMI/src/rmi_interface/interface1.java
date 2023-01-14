package rmi_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface1 extends Remote {

    public String chek(String name, String Email, String Password, String Country, int price) throws RemoteException;

}
