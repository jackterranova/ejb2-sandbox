package ejb;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;


public interface MyStatelessEJBHome extends EJBHome {
  MyStatelessEJBRemote create() throws RemoteException;
}