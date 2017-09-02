D:

set PROG_DIR=D:\PROGRA~1
@REM set PROG_DIR=C:\PROGRA~2
set CA_HOME=%PROG_DIR%\Java\jre7\lib\security\
set JAVA_HOME=%PROG_DIR%\Java\JDK17~1.0_1
set WLS_KEY_DIR=D:\weblogic\wls\wlserver\server\lib
set KEY_DIR=D:\labs\student\keystores
mkdir %KEY_DIR%
cd %KEY_DIR%

ECHO "Generating CA"
@REM CA Keys
%JAVA_HOME%\bin\keytool.exe -genkeypair -storepass welcome1 -keypass welcome1 -keyalg RSA -keysize 2048 -dname "CN=ca,OU=OracleUniversity,O=Oracle,C=US" -keystore ca.jks -alias ca -ext bc:c
@REM Export CA Cert to pem
%JAVA_HOME%\bin\keytool -exportcert -storepass welcome1 -rfc -alias ca -keystore ca.jks -file ca.pem
copy %CA_HOME%\cacerts .
@REM Create keystore for CA signing checks (can only have public key)
%JAVA_HOME%\bin\keytool -importcert -noprompt -storepass changeit -alias ca -keystore cacerts -file ca.pem
%JAVA_HOME%\bin\keytool -importkeystore -srckeystore %WLS_KEY_DIR%\DemoTrust.jks -destkeystore cacerts -srcstorepass DemoTrustKeyStorePassPhrase -deststorepass changeit

ECHO "Generating & Signing Server Keys"
@REM Server Keys
%JAVA_HOME%\bin\keytool -genkeypair -storepass welcome1 -keypass welcome1 -keyalg RSA -keysize 2048 -dname "CN=localhost,OU=OracleUniversity,O=Oracle,C=US" -keystore server.jks -alias server
@REM Server CSR
%JAVA_HOME%\bin\keytool -certreq -storepass welcome1 -alias server -keystore server.jks -file server.csr
@REM Use CA to sign
%JAVA_HOME%\bin\keytool -gencert -storepass welcome1 -alias ca -rfc -keystore ca.jks -infile server.csr -outfile server.pem
@REM Add CA to server keystore
%JAVA_HOME%\bin\keytool -importcert -noprompt -storepass welcome1 -alias ca -keystore server.jks -file ca.pem
@REM Add signed server cert back into server keystore
%JAVA_HOME%\bin\keytool -importcert -noprompt -storepass welcome1 -alias server -keystore server.jks -file server.pem

ECHO "Generating & Signing Client Keys"
@REM Client Keys
%JAVA_HOME%\bin\keytool -genkeypair -storepass welcome1 -keypass welcome1 -keyalg RSA -keysize 2048 -dname "CN=client,OU=OracleUniversity,O=Oracle,C=US" -keystore client.jks -alias client
@REM Client CSR
%JAVA_HOME%\bin\keytool -certreq -storepass welcome1 -alias client -keystore client.jks -file client.csr
@REM Use CA to sign
%JAVA_HOME%\bin\keytool -gencert -storepass welcome1 -alias ca -rfc -keystore ca.jks -infile client.csr -outfile client.pem
@REM Add CA to server keystore
%JAVA_HOME%\bin\keytool -importcert -noprompt -storepass welcome1 -alias ca -keystore client.jks -file ca.pem
@REM Add signed client cert back into client keystore
%JAVA_HOME%\bin\keytool -importcert -noprompt -storepass welcome1 -alias client -keystore client.jks -file client.pem