package com.asi.model;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

import com.asi.util.SecurityHelper;

public class User {
	
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String passwod;
	protected String role;
    protected String store;
    private String address;
	
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String decryptPasswod() {
		 SecurityHelper securityHelper;
			try {
				securityHelper = new SecurityHelper();
				return securityHelper.decrypt(passwod);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public void setPasswod(String passwod) {
		this.passwod = passwod;
	}
	
	public String getPasswod() {
		return passwod;
	}

	public String encryptPasswod(String passwod) {
		 SecurityHelper securityHelper;
		try {
			securityHelper = new SecurityHelper();
			return securityHelper.encrypt( passwod);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
