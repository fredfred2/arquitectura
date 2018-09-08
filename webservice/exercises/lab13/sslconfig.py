print 'Configuring WS-Security'

userName = 'weblogic'
passWord = 'welcome1'
url= 't3://localhost:7001'

print "Connecting to the running adminSever"

connect(userName, passWord, url)

edit()
startEdit()

#Configure SSL certs
print "Setting Keystores"
cd('/Servers/myserver')
cmo.setKeyStores('CustomIdentityAndCustomTrust')
cmo.setCustomIdentityKeyStoreFileName('D:\labs\student\keystores\server.jks')
cmo.setCustomIdentityKeyStoreType('jks')
cmo.setCustomIdentityKeyStorePassPhrase('welcome1')
cmo.setCustomTrustKeyStoreFileName('D:\labs\student\keystores\cacerts')
cmo.setCustomTrustKeyStoreType('jks')
cmo.setCustomTrustKeyStorePassPhrase('changeit')

print "Setting SSL key and enabling SSL Listen Port"
cd('/Servers/myserver/SSL/myserver')
cmo.setServerPrivateKeyAlias('server')
cmo.setServerPrivateKeyPassPhrase('welcome1')
cmo.setEnabled(true)
cd('/')


#Enable assert x509 in SecurityConfiguration
rlm = cmo.getSecurityConfiguration().getDefaultRealm()
ia = rlm.lookupAuthenticationProvider("DefaultIdentityAsserter")
activeTypesValue = list(ia.getActiveTypes())
existed = "X.509" in activeTypesValue
if existed == 1:
  print 'assert x509 is aleady enabled'
else:
  activeTypesValue.append("X.509")
ia.setActiveTypes(array(activeTypesValue,java.lang.String))
ia.setDefaultUserNameMapperAttributeType('CN');
ia.setUseDefaultUserNameMapper(Boolean('true'));


#Create default WebServcieSecurity
securityName='default_wss'
defaultWss=cmo.lookupWebserviceSecurity(securityName)
if defaultWss == None:
  print 'creating new webservice security bean for: ' + securityName
  defaultWss = cmo.createWebserviceSecurity(securityName)
else:
  print 'found exsiting bean for: ' + securityName
  

#Create credential provider for DK
cpName='default_dk_cp'
wtm=defaultWss.lookupWebserviceCredentialProvider(cpName)
if wtm == None:
        wtm = defaultWss.createWebserviceCredentialProvider(cpName)
        wtm.setClassName('weblogic.wsee.security.wssc.v200502.dk.DKCredentialProvider')
        wtm.setTokenType('dk')
        cpm = wtm.createConfigurationProperty('Label')
        cpm.setValue('WS-SecureConversationWS-SecureConversation')
        cpm = wtm.createConfigurationProperty('Length')
        cpm.setValue('16')
else:
  print 'found exsiting bean for: DK ' + cpName


#Create credential provider for x.509
cpName='default_x509_cp'
wtm=defaultWss.lookupWebserviceCredentialProvider(cpName)
if wtm == None:
        wtm = defaultWss.createWebserviceCredentialProvider(cpName)
        wtm.setClassName('weblogic.wsee.security.bst.ServerBSTCredentialProvider')
        wtm.setTokenType('x509')
else:
  print 'found exsiting bean for: x.509 ' + cpName 


#Custom keystore for xml encryption
cpName='ConfidentialityKeyStore'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
keyStoreName='D:/labs/student/keystores/server.jks'
cpm.setValue(keyStoreName)

cpName='ConfidentialityKeyStorePassword'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
cpm.setEncryptValueRequired(Boolean('true'))
KeyStorePasswd='welcome1'
cpm.setEncryptedValue(KeyStorePasswd)

cpName='ConfidentialityKeyAlias'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
keyAlias='server'
cpm.setValue(keyAlias)

cpName='ConfidentialityKeyPassword'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty('ConfidentialityKeyPassword')
cpm.setEncryptValueRequired(Boolean('true'))
keyPass='welcome1'
cpm.setEncryptedValue(keyPass)


#Custom keystore for xml digital signature
cpName='IntegrityKeyStore'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
keyStoreName='D:/labs/student/keystores/server.jks'
cpm.setValue(keyStoreName)

cpName='IntegrityKeyStorePassword'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
cpm.setEncryptValueRequired(Boolean('true'))
KeyStorePasswd='welcome1'
cpm.setEncryptedValue(KeyStorePasswd)

cpName='IntegrityKeyAlias'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
keyAlias='server'
cpm.setValue(keyAlias)

cpName='IntegrityKeyPassword'
cpm=wtm.lookupConfigurationProperty(cpName)
if cpm == None:
        cpm = wtm.createConfigurationProperty(cpName)
cpm.setEncryptValueRequired(Boolean('true'))
keyPass='welcome1'
cpm.setEncryptedValue(keyPass)

#Create token handler for x509 token
#cpName='default_x509_handler'
th=defaultWss.lookupWebserviceTokenHandler(cpName)
if th == None:
        th = defaultWss.createWebserviceTokenHandler(cpName)
        th.setClassName('weblogic.xml.crypto.wss.BinarySecurityTokenHandler')
        th.setTokenType('x509')
        cpm = th.createConfigurationProperty('UseX509ForIdentity')
        cpm.setValue('false')

save()
activate(block="true")
shutdown()
exit()