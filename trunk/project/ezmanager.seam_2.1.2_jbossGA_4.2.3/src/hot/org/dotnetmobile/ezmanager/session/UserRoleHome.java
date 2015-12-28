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

import org.dotnetmobile.ezmanager.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("userRoleHome")
public class UserRoleHome extends EntityHome<UserRole> {

	@In(create = true)
	RoleIdentityHome roleIdentityHome;
	@In(create = true)
	UserIdentityHome userIdentityHome;

	public void setUserRoleId(UserRoleId id) {
		setId(id);
	}

	public UserRoleId getUserRoleId() {
		return (UserRoleId) getId();
	}

	public UserRoleHome() {
		setUserRoleId(new UserRoleId());
	}

	@Override
	public boolean isIdDefined() {
		if (getUserRoleId().getRoleIdentityRoleIdentityId() == 0)
			return false;
		if (getUserRoleId().getUserIdentityUserIdentityId() == 0)
			return false;
		return true;
	}

	@Override
	protected UserRole createInstance() {
		UserRole userRole = new UserRole();
		userRole.setId(new UserRoleId());
		return userRole;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		RoleIdentity roleIdentity = roleIdentityHome.getDefinedInstance();
		if (roleIdentity != null) {
			getInstance().setRoleIdentity(roleIdentity);
		}
		UserIdentity userIdentity = userIdentityHome.getDefinedInstance();
		if (userIdentity != null) {
			getInstance().setUserIdentity(userIdentity);
		}
	}

	public boolean isWired() {
		if (getInstance().getRoleIdentity() == null)
			return false;
		if (getInstance().getUserIdentity() == null)
			return false;
		return true;
	}

	public UserRole getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
