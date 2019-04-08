### EJBs
Why did EJB come about?  Prior to the era of cloud computing, deployment of enterprise software was plagued with issues of interoperability, portability and scalability.

Making remote computer systems available over a network often required deploying duplicated code on both client and server systems.

This raised all kinds of deployment issues.

Clients either had to be running the same hardware as the server it was connecting to or special client-specific code needed to be written to be written that could communicate with the server.  As hardware and sofware changed on both the client and the server, keeping software compatibile was a huge challenge.   To upgrade the server or the software it runs, often required changes in the client.  Changes in the client oftten broke the client software it was running.

As a result, remote systems were often only accessible to a small amount of client processes.

With The advent of the JVM came the hope of simpler distibuted computing, thanks to Java's portability and its "write once, run everywhere" mantra.  Even with the JVM, writing enterprise software was slowed by lower level concerns of transactionality, security and scalability.

The EJB spec describes how to interface with client processes and other existing (even non-Java) systems, including compatibility with CORBA.  It defines specialized components in the enterprise system and was the first major and successful attempt to leverage Java as a way to create robust enterprise systems with a more defined separation of application concerns.

### Couldn't you just write lighter weight apps that could run in Tomcat?  What does EJB do that Servlets/JSPs can't?

The short answers are yes and nothing.

The longer answers are more complicated and of course depends on what your application(s) needs were and how scalable you needed your services to be.

EJB containers were intended to take on lower level tasks like concurrency, transactionality, security and scalability, allowing developers to focus on core business logic.

As long as developers had to manage these concerns and were willing to do so in their own code (and there have always been Java frameworks to do so), a servlet engine like Tomcat would work fine.

For example, if your application just requires some business logic and needs to talk to a database, then a servlet or 2 and some JDBC might work fine (with added coding around the JDBC connection pooling and transactions).  Using an EJB in such a scenario would likely be overkill despite the fact that the EJB container could handle db connection pools and transactions for you.

As an application becomes more complex with more specialized components (e.g. dbs, message queues, concurrency, disctibuted tranaction maangement etc), the usefuleness of EJB quickly becomes apparent.

A great and highly recommended article on this topic can be found here: https://www.javaworld.com/article/2077826/tomcat-in-the-enterprise.html.

### What has replaced EJBs

One of the big drawbacks of EJB was its reliance on heavyweight containers like Websphere and Weblogic.  Writing EJBs had a direct dependency on the libraries provided by Java EE servers.

Although EJB 3 is still used today and is a significant simplification of EJB 2, evolving technology like cloud computing and ever simpler network protocols, RESTful communication conventions and even specific Java frameworks such as Spring and Hibernate, give/gave us more and simpler options.

In the Java world we have been able to evolve from EJB and JDBC on Websphere and Weblogic to lighter weight alternatives like Spring and Hibernate on Tomcat.

Today cloud computing in AWS, Azure and GCP give us options way beyond not only EJB but the JVM itself.

### EJB Components
JavaBeans house business logic and can run in EJB container.  An EJB container is run on an EJB-compliant server (like Websphere, Weblogic or Glassfish).

### EJB Container
The EJB container was meant to manage the lower level plumbing of enterprise deployments.  Specially it was intended to manage transactionality, database connections and pooling, scalability, security and general resource management.

As a simple example, when an error occurs saving to a database, the container is responsible for all of the steps of the rollback and then informs the EJB component.  The component then only needs to worry about business logic around an error condition and not all the concerns about how to undo a partially complete transaction.

### EJB Object
The EJB object implements the remote interface that defines the signatures of the business logic methods available to the client.  It is a proxy to the EJB server on behalf of the EJB client process.

Through the EJB object the client makes calls on the remote EJB component (the enterprise bean) on the EJB server.  (It can be helpful to remember that is it the EJB container that is the actual interface to the EJB component - though to the client this is irrelevant).

Both the EJB object (on the client) and EJB component (the bean on the server)  implement the same interface.

The EJB object is autogenerated by the EJB container and related tools and sent to the client using serialization.

The client makes calls to the EJB object which then makes remote calls to the EJB container (using RMI) which invokes the appropriate method on the EJB component.

### Session Beans/Entity Beans

A Session bean is analogous to the web application session concept.  It is created and belongs to a single client.  Although its main use is NOT of persistence, it can be used to save state during a client session.  Session beans typically do not survive server crashes and are destroyed at the end of a client session (whatever the implementer decides that should be).

An Entity Bean is intended to represent data stored in a database.  A user, an account, user preferences, etc.

### What advantage does this architecture offer?

For starters, the EJB client can build applications around EJB objects as if they were local to the system.  This removes the need to worry about networking and other related concerns.

### Basic Lifecycle

The client contacts the EJB container, requesting a specific EJB object to perform its tasks.

### EJB Home/Remote

Possibly the most confusingly named convention ever in Java.  I don't know about you, but `home` and `remote` for me do not conjure up the image of what actual EJB home and remote entities do.

From the perspecitive of the client, the `home` object is basically an EJB object factory.  The `home` object is used by the cient to create a reference to the EJB object know as the `remote`.

So, repeat after me: "The home interface is an EJB factory.  The remote interface is the EJB."

Every EJB component implements `javax.ejb.EJBHome`, which customizes the various lifecycle operations performed by the EJB container on the component.

The home interface is RMI compatible as RMI is used in EJB operations.

The client uses `JNDI` to locate the home interface of the bean it needs for its work.  Then client can call `create()` on the home object to get the needed EJB object (which implements the remote interface).
