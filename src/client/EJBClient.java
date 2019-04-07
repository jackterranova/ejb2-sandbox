package client;

import ejb.MyStatelessEJBHome;
import ejb.MyStatelessEJBRemote;

import javax.naming.*;
import java.rmi.RemoteException;
import java.util.Properties;


public class EJBClient {

  public static void main(String[] args) {

    try {
      Properties props = new Properties();

      // JNDI lookup
      // We need to make a remote JNDI lookup for the EJB
      // An EJB is no different than any other resource on the Java server (like a JDBC connection or pool).
      // Just like we access a Spring-managed object through a Spring context getBean() method,
      // we access the EJB home object via JNDI.
      // And just like Spring, as a client, we typically only have an interface to which the container assigns
      // a concrete object (or in the case of EJB a stub)  - it would make no sense to instantiate the object directly.

      // Look at how tightly coupled we are to Glassfish here!
      // The JNDI lookup dependencies are highly dependent on the EE server we use - yuk!

      props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
      props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
      props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
      InitialContext ctx = new InitialContext(props);

      // In more modern JavaEE servers, the "narrow" function is most likely not needed.
      // You can usually just cast the JNDI object directly , which is what narrow does.
      // The narrow convention is a hold over from the ServiceLocator pattern used when casting via CORBA was required
      Object obj  = ctx.lookup("jndi/MyStatelessSessionBean");
      MyStatelessEJBHome beanHome =  (MyStatelessEJBHome) javax.rmi.PortableRemoteObject.narrow(obj, MyStatelessEJBHome.class);


      //EJB HOME (the EJB "factory")
      MyStatelessEJBRemote bean = beanHome.create();

      // EJB REMOTE (the EJB)
      // Although it would be silly to name your business logic method "executeOnServer()", I want to
      // drive home the point that this is being execute server side.
      bean.executeOnServer();
    }
    catch (RemoteException | NamingException e) {
      e.printStackTrace();
    }
  }
}
