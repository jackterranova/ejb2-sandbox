package ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;


public class MyStatelessSessionBean implements SessionBean {

  @Override
  public void ejbActivate() throws EJBException, RemoteException {
    System.out.println("ejb - activate");
  }

  @Override
  public void ejbPassivate() throws EJBException, RemoteException {
    System.out.println("ejb - passivate");
  }

  @Override
  public void ejbRemove() throws EJBException, RemoteException {
    System.out.println("ejb - remove");
  }

  @Override
  public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
  }

  // used for creating a reference to RemoteInterface
  public void ejbCreate() throws CreateException {
    System.out.println("ejb - create");
  }

  // Our business method which client will call
  public void executeOnServer() throws RemoteException {
    System.out.println("Executing logic on the server ...");
  }

}