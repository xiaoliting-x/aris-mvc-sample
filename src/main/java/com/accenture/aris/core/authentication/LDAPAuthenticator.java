package com.accenture.aris.core.authentication;

import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.InvalidAttributeValueException;

import com.accenture.aris.core.GeneralFailureException;

public class LDAPAuthenticator implements Authenticator {

    private static final String DIR_CONTEXT_LDAP_VERSION_PROP_NAME = "java.naming.ldap.version";

    private String ldapContext;
    private String ldapHost;
    private String searchBase;
    private String securityAuthentication;
    private String ldapVersion;

    public void setLdapContext(String ldapContext) {
        this.ldapContext = ldapContext;
    }

    public void setLdapHost(String ldapHost) {
        this.ldapHost = ldapHost;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    public void setSecurityAuthentication(String securityAuthentication) {
        this.securityAuthentication = securityAuthentication;
    }

    public void setLdapVersion(String ldapVersion) {
        this.ldapVersion = ldapVersion;
    }

    @Deprecated
    public void initialize() {
    }

    @Override
    public AuthenticatorData authenticate(String username, String password) {
        try {
            ldapAuth(username, password);
        } catch (AuthenticationException e) {
            return null;
        } catch (Exception e) {
            throw new GeneralFailureException(e);
        }
        return getAuthnticatorData(username);
    }
    
    /**
     * プロジェクトにおいて、拡張して使用してください。<p>
     * @param username
     * @return AuthenticatorDataインスタンス
     */
    protected AuthenticatorData getAuthnticatorData(String username) {
        // デフォルト実装では、認証結果のオブジェクトには、何も保持されません
        return new AuthenticatorData();
    }

    private void ldapAuth(String userId, String password)
            throws AuthenticationException, CommunicationException,
            InvalidAttributeValueException, OperationNotSupportedException,
            NamingException {
        String userDn = userId + "@" + searchBase;
        Properties environment = new Properties();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, ldapContext);
        environment.put(Context.PROVIDER_URL, ldapHost);
        environment.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
        environment.put(DIR_CONTEXT_LDAP_VERSION_PROP_NAME, ldapVersion);
        environment.put(Context.SECURITY_PRINCIPAL, userDn);
        environment.put(Context.SECURITY_CREDENTIALS, password);
        Context context = new InitialDirContext(environment);
        context.close();
    }
}
