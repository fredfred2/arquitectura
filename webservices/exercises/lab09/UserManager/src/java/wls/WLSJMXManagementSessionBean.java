package wls;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import javax.management.AttributeNotFoundException;
import javax.management.Descriptor;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.JMX;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeOperationsException;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import weblogic.management.mbeanservers.MBeanTypeService;
import weblogic.management.security.authentication.GroupEditorMBean;
import weblogic.management.security.authentication.GroupUserListerMBean;
import weblogic.management.security.authentication.MemberGroupListerMBean;
import weblogic.management.security.authentication.UserEditorMBean;

/**
 * Needs a server library (BEA Utils)
 * weblogic/wls/modules/com.bea.core.utils_2.0.0.0.jar
 * http://docs.oracle.com/cd/E24329_01/web.1211/e24415/toc.htm
 * http://docs.oracle.com/cd/E24329_01/apirefs.1211/e24395/index.html
 *
 * @author mheimer
 */
@RunAs("admin")
@Stateless
public class WLSJMXManagementSessionBean {

    private static final Logger logger = Logger.getLogger(WLSJMXManagementSessionBean.class.getPackage().getName());
    private static final ObjectName service;
    private MBeanServerConnection mBeanServer;

    static {
        try {
            service = new ObjectName("com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean");
        } catch (MalformedObjectNameException e) {
            throw new AssertionError(e.getMessage());
        }
    }

    @PostConstruct
    private void init() {
        try {
//            mBeanServer = getRemoteMBeanServer("localhost", "7001", "weblogic", "welcome1");
            mBeanServer = getLocalMBeanServer();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to create JMX connection", e);
        }
    }

    private MBeanServerConnection getRemoteMBeanServer(String hostname, String portString,
            String username, String password) throws IOException,
            MalformedURLException {
        String protocol = "t3";
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.domainruntime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname,
                port, jndiroot + mserver);
        Map<String, String> map = new HashMap<>();
        map.put(Context.SECURITY_PRINCIPAL, username);
        map.put(Context.SECURITY_CREDENTIALS, password);
        map.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, "weblogic.management.remote");
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL, map);
        return connector.getMBeanServerConnection();

    }

    private MBeanServer getLocalMBeanServer() throws NamingException {
        Context ctx = new InitialContext();
        return (MBeanServer) ctx.lookup("java:comp/env/jmx/domainRuntime");
    }

    private UserEditorMBean getUserEditor() {
        try {
            for (ObjectName authProviderName : getAuthenticationProviders()) {
                //get classname for auth provider (from default realm)
                ModelMBeanInfo info = (ModelMBeanInfo) mBeanServer.getMBeanInfo(authProviderName);
                Descriptor desc = info.getMBeanDescriptor();
                String className = (String) desc.getFieldValue("interfaceClassName");

                //get all mbeans that are UserEditorMBean instances
                ObjectName MBTserviceName = new ObjectName("com.bea:Name=MBeanTypeService,Type=weblogic.management.mbeanservers.MBeanTypeService");
                MBeanTypeService mbtService = JMX.newMBeanProxy(mBeanServer, MBTserviceName, MBeanTypeService.class);
                String[] mba = mbtService.getSubtypes("weblogic.management.security.authentication.UserEditorMBean");
                for (int i = 0; i < mba.length; i++) {
                    //hopefully only one UserEditorMBean in the default realm
                    if (mba[i].equals(className)) {
                        return JMX.newMBeanProxy(mBeanServer, authProviderName, UserEditorMBean.class);
                    }
                }
            }
        } catch (InstanceNotFoundException | IntrospectionException | ReflectionException | IOException | MBeanException | RuntimeOperationsException | MalformedObjectNameException ex) {
            logger.log(Level.SEVERE, "Could not get UserEditorMBean", ex);
        }
        return null;
    }

    private GroupEditorMBean getGroupEditor() {
        try {
            for (ObjectName authProviderName : getAuthenticationProviders()) {
                ModelMBeanInfo info = (ModelMBeanInfo) mBeanServer.getMBeanInfo(authProviderName);
                Descriptor desc = info.getMBeanDescriptor();
                String className = (String) desc.getFieldValue("interfaceClassName");

                ObjectName MBTserviceName = new ObjectName("com.bea:Name=MBeanTypeService,Type=weblogic.management.mbeanservers.MBeanTypeService");
                MBeanTypeService mbtService = JMX.newMBeanProxy(mBeanServer, MBTserviceName, MBeanTypeService.class);
                String[] mba = mbtService.getSubtypes("weblogic.management.security.authentication.GroupEditorMBean");
                for (int i = 0; i < mba.length; i++) {
                    if (mba[i].equals(className)) {
                        return JMX.newMBeanProxy(mBeanServer, authProviderName, GroupEditorMBean.class);
                    }
                }
            }
        } catch (InstanceNotFoundException | IntrospectionException | ReflectionException | IOException | MBeanException | RuntimeOperationsException | MalformedObjectNameException ex) {
            logger.log(Level.SEVERE, "Could not get GroupEditorMBean", ex);
        }
        return null;
    }

    private GroupUserListerMBean getGroupUserLister() {
        try {
            for (ObjectName authProviderName : getAuthenticationProviders()) {
                ModelMBeanInfo info = (ModelMBeanInfo) mBeanServer.getMBeanInfo(authProviderName);
                Descriptor desc = info.getMBeanDescriptor();
                String className = (String) desc.getFieldValue("interfaceClassName");

                ObjectName MBTserviceName = new ObjectName("com.bea:Name=MBeanTypeService,Type=weblogic.management.mbeanservers.MBeanTypeService");
                MBeanTypeService mbtService = JMX.newMBeanProxy(mBeanServer, MBTserviceName, MBeanTypeService.class);
                String[] mba = mbtService.getSubtypes("weblogic.management.security.authentication.GroupUserListerMBean");
                for (int i = 0; i < mba.length; i++) {
                    if (mba[i].equals(className)) {
                        return JMX.newMBeanProxy(mBeanServer, authProviderName, GroupUserListerMBean.class);
                    }
                }
            }
        } catch (InstanceNotFoundException | IntrospectionException | ReflectionException | IOException | MBeanException | RuntimeOperationsException | MalformedObjectNameException ex) {
            logger.log(Level.SEVERE, "Could not get GroupUserListerMBean", ex);
        }
        return null;
    }

    private MemberGroupListerMBean getMemberGroupLister() {
        try {
            for (ObjectName authProviderName : getAuthenticationProviders()) {
                ModelMBeanInfo info = (ModelMBeanInfo) mBeanServer.getMBeanInfo(authProviderName);
                Descriptor desc = info.getMBeanDescriptor();
                String className = (String) desc.getFieldValue("interfaceClassName");

                ObjectName MBTserviceName = new ObjectName("com.bea:Name=MBeanTypeService,Type=weblogic.management.mbeanservers.MBeanTypeService");
                MBeanTypeService mbtService = JMX.newMBeanProxy(mBeanServer, MBTserviceName, MBeanTypeService.class);
                String[] mba = mbtService.getSubtypes("weblogic.management.security.authentication.MemberGroupListerMBean");
                for (int i = 0; i < mba.length; i++) {
                    if (mba[i].equals(className)) {
                        return JMX.newMBeanProxy(mBeanServer, authProviderName, MemberGroupListerMBean.class);
                    }
                }
            }
        } catch (InstanceNotFoundException | IntrospectionException | ReflectionException | IOException | MBeanException | RuntimeOperationsException | MalformedObjectNameException ex) {
            logger.log(Level.SEVERE, "Could not get MemberGroupListerMBean", ex);
        }
        return null;
    }

    private ObjectName[] getAuthenticationProviders() {
        try {
            ObjectName domainMBean =
                    (ObjectName) mBeanServer.getAttribute(service, "DomainConfiguration");
            ObjectName securityConfiguration =
                    (ObjectName) mBeanServer.getAttribute(domainMBean, "SecurityConfiguration");
            ObjectName defaultRealm =
                    (ObjectName) mBeanServer.getAttribute(securityConfiguration, "DefaultRealm");
            return (ObjectName[]) mBeanServer.getAttribute(defaultRealm, "AuthenticationProviders");
        } catch (IOException | MBeanException | AttributeNotFoundException | InstanceNotFoundException | ReflectionException ex) {
            logger.log(Level.SEVERE, "Failed to find authentication providers in the default realm", ex);
            return null;
        }

    }

    public void createUser(String userName, String password, String description) throws Exception {
        UserEditorMBean userEditor = getUserEditor();
        userEditor.createUser(userName, password, description);
    }

    public String getUserDescription(String userName) throws Exception {
        UserEditorMBean userEditor = getUserEditor();
        return userEditor.getUserDescription(userName);
    }

    public void setUserDescription(String userName, String description) throws Exception {
        switch (userName) {
            case "OracleSystemUser":
            case "weblogic":
                throw new Exception("You cannot change system accounts");
        }
        UserEditorMBean userEditor = getUserEditor();
        userEditor.setUserDescription(userName, description);
    }

    public void removeUser(String userName) throws Exception {
        switch (userName) {
            case "OracleSystemUser":
            case "weblogic":
                throw new Exception("You cannot change system accounts");
        }
        UserEditorMBean userEditor = getUserEditor();
        userEditor.removeUser(userName);
    }

    public void resetUserPassword(String userName, String password) throws Exception {
        switch (userName) {
            case "OracleSystemUser":
            case "weblogic":
                throw new Exception("You cannot change system accounts");
        }
        UserEditorMBean userEditor = getUserEditor();
        userEditor.resetUserPassword(userName, password);
    }

    public boolean doesUserExist(String userName) throws Exception {
        UserEditorMBean userEditor = getUserEditor();
        return userEditor.userExists(userName);
    }

    public List<String> getUserList() throws Exception {
        List<String> userNames = new ArrayList<>();
        UserEditorMBean userEditor = getUserEditor();
        String cursor = userEditor.listUsers("*", 0);
        while (userEditor.haveCurrent(cursor)) {
            String userName = userEditor.getCurrentName(cursor);
            userNames.add(userName);
            userEditor.advance(cursor);
        }
        userEditor.close(cursor);
        return userNames;
    }

    public void addUserToGroup(String userName, String groupName) throws Exception {
        switch (userName) {
            case "OracleSystemUser":
            case "weblogic":
                throw new Exception("You cannot change system accounts");
        }
        switch (groupName) {
            case "AdminChannelUsers":
            case "Administrators":
            case "AppTesters":
            case "CrossDomainConnectors":
            case "Deployers":
            case "Monitors":
            case "Operators":
            case "OracleSystemGroup":
                throw new Exception("You cannot change system groups");
        }
        GroupEditorMBean groupEditor = getGroupEditor();
        groupEditor.addMemberToGroup(groupName, userName);
    }

    public void removeUserFromGroup(String userName, String groupName) throws Exception {
        switch (userName) {
            case "OracleSystemUser":
            case "weblogic":
                throw new Exception("You cannot change system accounts");
        }
        switch (groupName) {
            case "AdminChannelUsers":
            case "Administrators":
            case "AppTesters":
            case "CrossDomainConnectors":
            case "Deployers":
            case "Monitors":
            case "Operators":
            case "OracleSystemGroup":
                throw new Exception("You cannot change system groups");
        }
        GroupEditorMBean groupEditor = getGroupEditor();
        groupEditor.removeMemberFromGroup(groupName, userName);
    }

    public boolean isUserInGroup(String userName, String groupName) throws Exception {
        GroupEditorMBean groupEditor = getGroupEditor();
        return groupEditor.isMember(groupName, userName, true);
    }

    public void createGroup(String groupName, String description) throws Exception {
        GroupEditorMBean groupEditor = getGroupEditor();
        groupEditor.createGroup(groupName, description);
    }

    public String getGroupDescription(String groupName) throws Exception {
        GroupEditorMBean groupEditor = getGroupEditor();
        return groupEditor.getGroupDescription(groupName);
    }

    public void setGroupDescription(String groupName, String description) throws Exception {
        switch (groupName) {
            case "AdminChannelUsers":
            case "Administrators":
            case "AppTesters":
            case "CrossDomainConnectors":
            case "Deployers":
            case "Monitors":
            case "Operators":
            case "OracleSystemGroup":
                throw new Exception("You cannot change the system groups");
        }
        GroupEditorMBean groupEditor = getGroupEditor();
        groupEditor.setGroupDescription(groupName, description);
    }

    public void removeGroup(String groupName) throws Exception {
        switch (groupName) {
            case "AdminChannelUsers":
            case "Administrators":
            case "AppTesters":
            case "CrossDomainConnectors":
            case "Deployers":
            case "Monitors":
            case "Operators":
            case "OracleSystemGroup":
                throw new Exception("You cannot change the system groups");
        }
        GroupEditorMBean groupEditor = getGroupEditor();
        groupEditor.removeGroup(groupName);
    }

    public boolean doesGroupExist(String groupName) throws Exception {
        GroupEditorMBean groupEditor = getGroupEditor();
        return groupEditor.groupExists(groupName);
    }

    public List<String> getGroupsByPattern(String groupPattern) throws Exception {
        List<String> groupNames = new ArrayList<>();
        GroupEditorMBean groupEditor = getGroupEditor();
        String cursor = groupEditor.listGroups(groupPattern, 0);
        while (groupEditor.haveCurrent(cursor)) {
            String groupName = groupEditor.getCurrentName(cursor);
            groupNames.add(groupName);
            groupEditor.advance(cursor);
        }
        groupEditor.close(cursor);
        return groupNames;
    }

    public List<String> getGroupList() throws Exception {
        return getGroupsByPattern("*");
    }

    public List<String> getGroupMembers(String groupName) throws Exception {
        GroupUserListerMBean groupUserLister = getGroupUserLister();
        List<String> userNames = new ArrayList<>();
        String[] cursors = groupUserLister.listAllUsersInGroup(groupName, "*", 0);
        userNames.addAll(Arrays.asList(cursors));
        return userNames;
    }

    public List<String> getMemberGroups(String userName) throws Exception {
        MemberGroupListerMBean groupUserLister = getMemberGroupLister();
        List<String> groupNames = new ArrayList<>();
        String cursor = groupUserLister.listMemberGroups(userName);
        while (groupUserLister.haveCurrent(cursor)) {
            String groupName = groupUserLister.getCurrentName(cursor);
            groupNames.add(groupName);
            groupUserLister.advance(cursor);
        }
        groupUserLister.close(cursor);
        return groupNames;
    }
}
