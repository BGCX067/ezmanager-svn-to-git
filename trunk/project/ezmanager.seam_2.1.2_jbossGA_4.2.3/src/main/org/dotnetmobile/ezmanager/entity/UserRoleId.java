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

package org.dotnetmobile.ezmanager.entity;

//Generated Jan 3, 2010 6:01:01 PM by Hibernate Tools 3.2.5.Beta

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserRoleId generated by hbm2java
 */
@Embeddable
public class UserRoleId implements java.io.Serializable {

	private long userIdentityUserIdentityId;
	private long roleIdentityRoleIdentityId;

	public UserRoleId() {
	}

	public UserRoleId(long userIdentityUserIdentityId,
			long roleIdentityRoleIdentityId) {
		this.userIdentityUserIdentityId = userIdentityUserIdentityId;
		this.roleIdentityRoleIdentityId = roleIdentityRoleIdentityId;
	}

	@Column(name = "USER_IDENTITY_USER_IDENTITY_ID", nullable = false, precision = 10, scale = 0)
	public long getUserIdentityUserIdentityId() {
		return this.userIdentityUserIdentityId;
	}

	public void setUserIdentityUserIdentityId(long userIdentityUserIdentityId) {
		this.userIdentityUserIdentityId = userIdentityUserIdentityId;
	}

	@Column(name = "ROLE_IDENTITY_ROLE_IDENTITY_ID", nullable = false, precision = 10, scale = 0)
	public long getRoleIdentityRoleIdentityId() {
		return this.roleIdentityRoleIdentityId;
	}

	public void setRoleIdentityRoleIdentityId(long roleIdentityRoleIdentityId) {
		this.roleIdentityRoleIdentityId = roleIdentityRoleIdentityId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoleId))
			return false;
		UserRoleId castOther = (UserRoleId) other;

		return (this.getUserIdentityUserIdentityId() == castOther
				.getUserIdentityUserIdentityId())
				&& (this.getRoleIdentityRoleIdentityId() == castOther
						.getRoleIdentityRoleIdentityId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getUserIdentityUserIdentityId();
		result = 37 * result + (int) this.getRoleIdentityRoleIdentityId();
		return result;
	}

}
