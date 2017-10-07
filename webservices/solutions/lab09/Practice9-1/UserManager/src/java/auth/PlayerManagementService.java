package auth;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import wls.WLSJMXManagementSessionBean;

@WebService(serviceName = "user-management-service", portName = "user-management-port", endpointInterface = "auth.UserManagementType", targetNamespace = "urn:auth", wsdlLocation = "WEB-INF/wsdl/PlayerManagementService/UserManagement.wsdl")
public class PlayerManagementService {

    private static final Logger logger = Logger.getLogger(PlayerManagementService.class.getPackage().getName());
    @EJB
    private WLSJMXManagementSessionBean bean;

    public void createGroup(java.lang.String groupName, java.lang.String description) {
        try {
            bean.createGroup(groupName, description);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to create group", ex);
        }
    }

    public void createUser(java.lang.String userName, java.lang.String password, java.lang.String description) {
        try {
            bean.createUser(userName, password, description);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to create user", ex);
        }
    }

    public void deleteGroup(java.lang.String groupName) {
        try {
            bean.removeGroup(groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to delete group", ex);
        }
    }

    public void deleteUser(java.lang.String userName) {
        try {
            bean.removeUser(userName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to delete user", ex);
        }
    }

    public java.lang.String getGroupDescription(java.lang.String groupName) {
        try {
            return bean.getGroupDescription(groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to get group description", ex);
            return "";
        }
    }

    public java.lang.String getUserDescription(java.lang.String userName) {
        try {
            return bean.getUserDescription(userName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to get user description", ex);
            return "";
        }
    }

    public void setPassword(java.lang.String userName, java.lang.String password) {
        try {
            bean.resetUserPassword(userName, password);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to set password", ex);
        }
    }

    public void setGroupDescription(java.lang.String groupName, java.lang.String description) {
        try {
            bean.setGroupDescription(groupName, description);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to set group description", ex);
        }
    }

    public void setUserDescription(java.lang.String userName, java.lang.String description) {
        try {
            bean.setUserDescription(userName, description);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to set user description", ex);
        }
    }

    public boolean checkGroupMembership(java.lang.String userName, java.lang.String groupName) {
        try {
            return bean.isUserInGroup(userName, groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to check group membership", ex);
            return false;
        }
    }

    public java.util.List<java.lang.String> listJoinedGroups(java.lang.String userName) {
        try {
            return bean.getMemberGroups(userName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to list joined groups", ex);
            return new ArrayList<>();
        }
    }

    public java.util.List<java.lang.String> listGroupsByPattern(String groupPattern) {
        try {
            return bean.getGroupsByPattern(groupPattern);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to list groups", ex);
            return new ArrayList<>();
        }
    }

    public java.util.List<java.lang.String> listGroups() {
        try {
            return bean.getGroupList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to list groups", ex);
            return new ArrayList<>();
        }
    }

    public boolean isUserPresent(java.lang.String userName) {
        try {
            return bean.doesUserExist(userName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to check for user", ex);
            return false;
        }
    }

    public void joinGroup(java.lang.String userName, java.lang.String groupName) {
        try {
            bean.addUserToGroup(userName, groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to join group", ex);
        }
    }

    public void leaveGroup(java.lang.String userName, java.lang.String groupName) {
        try {
            bean.removeUserFromGroup(userName, groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to leave group", ex);
        }
    }

    public boolean isGroupPresent(java.lang.String groupName) {
        try {
            return bean.doesGroupExist(groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to check for group", ex);
            return false;
        }
    }

    public java.util.List<java.lang.String> listGroupMembers(java.lang.String groupName) {
        try {
            return bean.getGroupMembers(groupName);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to list group members", ex);
            return new ArrayList<>();
        }
    }

    public java.util.List<java.lang.String> listUsers() {
        try {
            return bean.getUserList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Failed to list users", ex);
            return new ArrayList<>();
        }
    }
}