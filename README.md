### EJB Sandbox
This is a sample EJB 2 project.

### Why on earth did you waste your time writing something in EJB???

I think it paricularly helpful to have some understanding of how and why technology evolves.

It not only gives us an appreciation of modern cloud computing but more importantly gives us clearer insights as engineers on what makes sound architecture.

### Background
To work with EJBs you'll need a compliant container.  As awesome as Tomcat is, it doesn't fit the bill.

You'll need something like Glassfish.

Back in the heady days of EJB1/2 (late 90s to mid 2000s) you typically used an insanely heavy EE compliant container such as Websphere or Weblogic (don't miss those days of setting up those containers for local development).

In this example project, I wanted to use a container as close as possible to a typical EE environment of the time.  This would correspond to EJB2.1 (late 2003) Java 4(1.4)/5.  However, the first release of Glassfish was 2005 and that is no longer available (at least not from Oracle).

Glassfish 3 was the most promising but will not work on more modern Macs.

So I ended up using Glassfish 4 for this sample project.

### Installing
1. Download GF 4: http://download.oracle.com/glassfish/4.1.1/release/glassfish-4.1.1.zip
2. Unzip the GF bundle in a directory of your choice.
3. Startup GF => $GF_INSTALL_DIR/bin/asadmin start-domain.

GF Server admin console should be accessible at http://localhost:4848/


### Build/Deploy the EJB Jar
1. Copy or reference the GF library ($GF_INSTALL_DIR/glassfish/modules) in to your project/IDE classpath.
2. Build the prject either on the command line using javac or (preferable your through your IDE)
3. Package the EJB Jar

```
cd $BUILD_OUTPUT
jar cvf ejb2-sandbox.jar META-INF ejb
```

4. Deploy the EJB Jar in GF Server

```
http://localhost:4848/ => applications => deploy => upload ejb2-sandbox.jar
```

### Run the EJBClient
A good exercise might be to package the client code in its own jar file and run it separately from the overall project.

But to get started quickly you can simply execute the main method in `EJBClient` (make sure your project is using the GF library in its classspath).


