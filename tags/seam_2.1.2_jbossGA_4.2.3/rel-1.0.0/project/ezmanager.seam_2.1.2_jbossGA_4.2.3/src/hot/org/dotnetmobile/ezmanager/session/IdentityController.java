package org.dotnetmobile.ezmanager.session;

//import org.dotnetmobile.ezmanager.authentication.PasswordManager;
import org.dotnetmobile.ezmanager.entity.UserIdentity;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.international.StatusMessages;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Name("identityController")
public class IdentityController
{
    @Logger private Log log;

    @In private EntityManager entityManager;
    @In private Identity identity;
    @In StatusMessages statusMessages;

    @Out public PasswordManager passwordManager;
    
    public void identityController()
    {
        // implement your business logic here
        log.info("identityController.identityController() action called");
        statusMessages.add("identityController");
    }

    // add additional action methods

    @Transactional 
    public boolean checkIdentity() {
    	
    	try {
			Credentials cr = identity.getCredentials();
			
			Query qry = entityManager.createQuery("SELECT u FROM UserIdentity u WHERE u.name = :username").setParameter("username", cr.getUsername());
			
			UserIdentity user = (UserIdentity)qry.getSingleResult();
			
			boolean integrity =  ((user!=null) && (user.getIsActive()=='1'));
			
			passwordManager = (PasswordManager)Component.getInstance(PasswordManager.class);
			passwordManager.setDigestAlgorithm("SHA-1");
			passwordManager.setCharset("UTF-8");

			if (integrity) {
				integrity &= validatePassword(cr.getPassword(), user);
			}
			
			return integrity;
		} catch (NoResultException e) {
			return false;
		}
    }
    
	public boolean validatePassword(String password, UserIdentity user) {
		return passwordManager.hash(password).equals(user.getPassword());
	}

}
