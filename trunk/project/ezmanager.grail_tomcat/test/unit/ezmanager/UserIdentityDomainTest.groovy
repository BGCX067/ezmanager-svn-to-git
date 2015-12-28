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

package ezmanager;

import ezmanager.UserIdentity;
import groovy.util.GroovyTestCase;

/**
* UserIdentityDomainTest is testing the domain class UserIdentity.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/ 
class UserIdentityDomainTest extends GroovyTestCase {
	/**
	 *  Testing the constructor
	 */
	void testConstructor() {
		Long userIdentityIdRef = 1
		String nameUserRef = "user"
		String passwordRef = "password"
		String isActiveUserRef = 1

		UserIdentity ui = new UserIdentity(userIdentityId:userIdentityIdRef, name:nameUserRef, password:passwordRef, isActive:isActiveUserRef)
		
		assertNotNull ui 
		assertEquals userIdentityIdRef, ui.userIdentityId
		assertEquals nameUserRef, ui.name
		assertEquals passwordRef, ui.password
		assertEquals isActiveUserRef, ui.isActive

	}
}
