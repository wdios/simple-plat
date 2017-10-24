package com.wis.tookit;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.wis.model.user.User;

public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User aUser) {
    	aUser.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                aUser.getPassword(),
                ByteSource.Util.bytes(aUser.getCredentialsSalt()),
                hashIterations).toHex();
        aUser.setPassword(newPassword);
    }
    
    public String getEncryptPassword(String username, String password, String salt) {
        return new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(username + salt),
                hashIterations).toHex();
    }
    
}
