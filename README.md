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
