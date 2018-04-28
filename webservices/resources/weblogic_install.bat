D:

@REM set JAVA_HOME=C:\PROGRA~2\Java\JDK17~1.0_1
set JAVA_HOME=D:\PROGRA~1\Java\JDK17~1.0_1
set MW_HOME=D:\weblogic\wls
set DOMAIN_DIR=D:\weblogic\domain
set JAVA_VENDOR=Sun

cd %MW_HOME%
call configure.cmd
mkdir %DOMAIN_DIR%
cd %DOMAIN_DIR%
call %MW_HOME%\wlserver\server\bin\setWLSEnv.cmd
%JAVA_HOME%\bin\java.exe %JAVA_OPTIONS% -Xmx1024m -XX:MaxPermSize=128m weblogic.Server
