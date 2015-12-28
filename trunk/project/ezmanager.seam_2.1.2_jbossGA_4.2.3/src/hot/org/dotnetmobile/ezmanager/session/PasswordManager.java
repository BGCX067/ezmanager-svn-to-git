/*  <EzManager: Web application for activities follow up. As main features, you can create projects, then associate tasks and calculate their corresponding invoices. Each project is associated to one or more customers identified by their addressee>
    Copyright (C) <2010>  <Michel Petrovic> <email: dotnetmobile@gmail.com>

    Contributor(s): _____________________.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.dotnetmobile.ezmanager.session;

import java.security.MessageDigest;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.log.Log;
import org.jboss.seam.util.Hex;
import org.jboss.seam.util.Base64;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;

//@Name("org.dotnetmobile.ezmanager.authentication.passwordManager")
@Name("org.dotnetmobile.ezmanager.session.passwordManager")
@BypassInterceptors
public class PasswordManager {
	@Logger private Log log;
	
    private String digestAlgorithm;
    private String charset;
    private String saltPhrase;
    public enum Encoding { base64, hex }
    private Encoding encoding = Encoding.hex;

    public String getDigestAlgorithm() {
            return this.digestAlgorithm;
    }

    public void setDigestAlgorithm(String algorithm) {
            this.digestAlgorithm = algorithm;
    }

    public String getCharset() {
            return this.charset;
    }

    public void setCharset(String charset) {
            this.charset = charset;
    }

    public Encoding getEncoding() {
            return this.encoding;
    }

    public void setEncoding(Encoding encoding) {
            this.encoding = encoding;
    }

    public String getSaltPhrase() {
            return this.saltPhrase;
    }

    public void setSaltPhrase(String saltPhrase) {
            this.saltPhrase = saltPhrase;
    }

    public String hash(String plainTextPassword) {
            try {
                    MessageDigest digest = MessageDigest.getInstance(digestAlgorithm);
                    if (saltPhrase != null) {
                            digest.update(saltPhrase.getBytes(charset));
                            byte[] salt = digest.digest();
                            digest.reset();
                            digest.update(plainTextPassword.getBytes(charset));
                            digest.update(salt);
                    }
                    else {
                            digest.update(plainTextPassword.getBytes(charset));
                    }
                    byte[] rawHash = digest.digest();
                    if (encoding != null && encoding.equals(Encoding.base64)) {
                            return Base64.encodeBytes(rawHash);
                    }
                    else {
                            return new String(Hex.encodeHex(rawHash));
                    }
            }
            catch (Exception e) {
                    throw new RuntimeException(e);
            }
    }

    public static PasswordManager instance() {
            return (PasswordManager) Component.getInstance(PasswordManager.class, ScopeType.EVENT);
    }
}