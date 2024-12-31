JUNIT_JAR = ../junit5.jar
JUNIT_RUNNER = org.junit.platform.console.ConsoleLauncher

JAVAC = javac
JAVA = java
JFLAGS = -cp .:$(JUNIT_JAR)
MAIN_CLASS = Main

SOURCES = Meteorite.java BackendInterface.java Backend.java Frontend.java FrontendInterface.java MeteoriteObjectInterface.java Main.java

runMain: $(SOURCES) Main.java
	$(JAVAC) -cp .:$(JUNIT_JAR) $(SOURCES) Main.java
	$(JAVA) $(JFLAGS) $(MAIN_CLASS)

clean:
	find . -name "*.class" -type f -delete

