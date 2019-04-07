package ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface MyStatelessEJBRemote extends EJBObject {
  void executeOnServer() throws RemoteException;
}