
JFLAGS = -d bin -sourcepath src

JUNIT_JAR = junit-platform-console-standalone-1.2.0.jar
JUNIT_RUNNER = org.junit.platform.console.ConsoleLauncher

WD_JAR = WeatherData.jar

default:
	@echo "'make clean' removes *.class *~ files"
	@echo "'make compile' compiles all Java files"
	@echo "'make demo' runs simulator demo"
	@echo "'make test' runs JUnit5 test cases"

test: $(JUNIT_JAR) $(WD_JAR)
	java -cp .:bin:$(JUNIT_JAR):$(WD_JAR) $(JUNIT_RUNNER) --scan-class-path

clean:
	rm -f bin/*.class
	rm -f src/*~
	rm -f *~

compile: $(JUNIT_JAR) $(WD_JAR)
	javac $(JFLAGS) -cp .:$(JUNIT_JAR):$(WD_JAR) src/*.java

demo: bin/Demo.class $(WD_JAR)
	java -cp bin:$(WD_JAR) Demo

