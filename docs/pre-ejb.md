### EJB 1/2
Prior to the first EJB spec, application architectures were primitive with either simple 2 tier client/server systems or extremely primitive with single tier local processes and business logic running on the a single machine.

### 2 Tier Systems

Some of the earliest 2 tier systems were client/server systems where business logic would run on the client and the server was nothing more than some type of database server that stored information in an RDBMS.

As architecture became more mature, application servers started to replace database servers.  Business logic was pushed from the client to the application server, creating a more centralized architecture - a more enterprise-y approach.

### 3 Tier/N Tier Systems

As enterprise architectures evolved further, 3 tier systems enhanced the 2 tier system by further separating server side concerns of business logic and persistence.  Clients made calls to application servers which house business logic - application servers made calls to database servers where state could be saved.

N-tier systems would add more isolated layers of services utilized by tiers above or below them.

### CORBA (Common Object Request Broker Architecture)

As the power of centralized application systems became apparent, the need for interoperability among hardware and software inscreased.

CORBA allowed objects (even those written in different languages) to run interoperably on different machines.   Years-old legacy objects could be packaged as CORBA services and run in more modern systems.

==> Enter EJBs(./ejb-background.md) - interoperable Java components that were compatible with CORBA.
