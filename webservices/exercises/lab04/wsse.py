readDomain('D:/weblogic/domain')
installDir = 'D:/weblogic/wls/wlserver'
templateLocation = installDir + \
'/common/templates/applications/wls_webservice_jaxws.jar'
addTemplate(templateLocation)
updateDomain()
closeDomain()
exit()