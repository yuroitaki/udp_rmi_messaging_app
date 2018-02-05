@echo off

echo Computer Networks and Distributed Systems Coursework - Build Utility
echo (c) Daniele Sgandurra, Imperial College London, Mar 2016
echo.

if "%1"=="" goto lUsage

REM Select target to be built
if "%1"=="all" goto lBuildStart
if "%1"=="rmi" goto lBuildRMI
if "%1"=="udp" goto lBuildUDP
if "%1"=="tcp" goto lBuildTCP
if "%1"=="clean" goto lClean

REM Missing a Target
	echo Target not recongnised
	goto lEnd

REM Targets
:lBuildStart
:lBuildRMI
	echo Building RMI Classes ...
	cd common
	javac -g -classpath . *.java
	cd ..\rmi
	javac -g -classpath .;.. *.java
	cd ..
	rmic rmi.RMIServer
	echo.
	if not "%1"=="all" goto lEnd

:lBuildUDP
	echo Building UDP Client / Server...
	cd common
	javac -g -classpath . *.java
	cd ..\udp
	javac -g -classpath .. *.java
	cd ..
	echo.
	if not "%1"=="all" goto lEnd

:lBuildEnd
	goto lEnd
	
:lClean
	del common\*.class
	del rmi\*.class
	del udp\*.class
	echo.
	goto :lEnd

REM End Targets


:lUsage
echo Usage: build 'target' (rmi / udp)
goto lEnd



:lEnd
echo Done!
echo.
