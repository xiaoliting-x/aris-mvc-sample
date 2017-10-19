package com.accenture.aris.core.authentication;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.aris.core.GeneralFailureException;

public class DatabaseAuthenticator implements Authenticator {

    @Autowired
    private AuthenticatorDao databaseAuthenticatorDao;

    private boolean isEncrypt = false;
    
    public boolean getIsEncrypt() {
        return isEncrypt;
    }
    
    public void setIsEncrypt(boolean isEncrypt) {
        this.isEncrypt = isEncrypt;
    }

    @Override
    public AuthenticatorData authenticate(String username, String password) {
        try {
            if (isEncrypt) {
                password = encrypt(password);
            }
            AuthenticatorData data = databaseAuthenticatorDao.selectByNameAndPassword(username, password);
            if (data != null) {
                data.setPassword(null);
            }
            return data;
        } catch (GeneralFailureException e) {
            throw e;
        }
    }

    public static String encrypt(String password)
            throws GeneralFailureException {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte enc[] = md.digest(password.getBytes());

            StringBuilder md5String = new StringBuilder();
            for (int i = 0; i < enc.length; i++) {
                int n = enc[i] & 0xff;
                if (n < 16) {
                    md5String.append("0");
                }
                md5String.append(Integer.toHexString(n).toUpperCase());
            }
            return md5String.toString();
        } catch (Exception e) {
            throw new GeneralFailureException(e);
        }
    }
}
