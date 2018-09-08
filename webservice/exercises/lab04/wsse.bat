D:

@REM set JAVA_HOME=C:\PROGRA~2\Java\JDK17~1.0_1
set JAVA_HOME=D:\PROGRA~1\Java\JDK17~1.0_1
set MW_HOME=D:\weblogic\wls
set JAVA_VENDOR=Sun
call %MW_HOME%\wlserver\server\bin\setWLSEnv.cmd


%JAVA_HOME%\bin\java.exe weblogic.WLST D:\labs\student\exersises\lab04\wsse.py

