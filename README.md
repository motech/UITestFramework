# UITestFramework
Selenium webdriver based testing framework for MOTECH.




# Prerequisites
sudo apt-get install Xvfb

# Running a single test
http://maven.apache.org/surefire/maven-failsafe-plugin/examples/single-test.html

mvn clean install -PFT -Dit.test=HelloWorldFT
mvn clean install -PFT -Dit.test=HelloWorldFT#testHelloWorld

# Debugging failsafe
http://maven.apache.org/surefire/maven-failsafe-plugin/examples/debugging.html

mvn clean install -PFT -Dmaven.failsafe.debug

# Debugging Tomcat
set CATALINA_OPTS="-Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000"

# Changing ports

You can change the ports being used by the tests in order to avoid conflicts with a regular Tomcat instance.
Make sure you don't have a CATALINA_HOME variable set, it will override these ports.

    mvn clean install -PFT -Dhttp.port=9090 -Dshutdown.port=8010