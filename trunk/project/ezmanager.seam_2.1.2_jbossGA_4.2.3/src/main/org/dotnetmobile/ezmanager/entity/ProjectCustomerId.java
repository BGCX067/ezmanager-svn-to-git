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
 * ProjectCustomerId generated by hbm2java
 */
@Embeddable
public class ProjectCustomerId implements java.io.Serializable {

	private long customerId;
	private long projectId;

	public ProjectCustomerId() {
	}

	public ProjectCustomerId(long customerId, long projectId) {
		this.customerId = customerId;
		this.projectId = projectId;
	}

	@Column(name = "CUSTOMER_ID", nullable = false, precision = 10, scale = 0)
	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 10, scale = 0)
	public long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProjectCustomerId))
			return false;
		ProjectCustomerId castOther = (ProjectCustomerId) other;

		return (this.getCustomerId() == castOther.getCustomerId())
				&& (this.getProjectId() == castOther.getProjectId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getCustomerId();
		result = 37 * result + (int) this.getProjectId();
		return result;
	}

}
