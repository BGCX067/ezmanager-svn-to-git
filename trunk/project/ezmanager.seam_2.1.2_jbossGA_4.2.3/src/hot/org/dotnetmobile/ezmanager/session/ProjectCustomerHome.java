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

@Name("projectCustomerHome")
public class ProjectCustomerHome extends EntityHome<ProjectCustomer> {

	@In(create = true)
	CustomerHome customerHome;
	@In(create = true)
	ProjectHome projectHome;

	public void setProjectCustomerId(ProjectCustomerId id) {
		setId(id);
	}

	public ProjectCustomerId getProjectCustomerId() {
		return (ProjectCustomerId) getId();
	}

	public ProjectCustomerHome() {
		setProjectCustomerId(new ProjectCustomerId());
	}

	@Override
	public boolean isIdDefined() {
		if (getProjectCustomerId().getCustomerId() == 0)
			return false;
		if (getProjectCustomerId().getProjectId() == 0)
			return false;
		return true;
	}

	@Override
	protected ProjectCustomer createInstance() {
		ProjectCustomer projectCustomer = new ProjectCustomer();
		projectCustomer.setId(new ProjectCustomerId());
		return projectCustomer;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Customer customer = customerHome.getDefinedInstance();
		if (customer != null) {
			getInstance().setCustomer(customer);
		}
		Project project = projectHome.getDefinedInstance();
		if (project != null) {
			getInstance().setProject(project);
		}
	}

	public boolean isWired() {
		if (getInstance().getCustomer() == null)
			return false;
		if (getInstance().getProject() == null)
			return false;
		return true;
	}

	public ProjectCustomer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
