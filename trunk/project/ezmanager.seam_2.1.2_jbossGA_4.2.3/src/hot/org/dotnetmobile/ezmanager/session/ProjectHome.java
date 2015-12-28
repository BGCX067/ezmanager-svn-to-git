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

import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("projectHome")
public class ProjectHome extends EntityHome<Project> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8479151853820847619L;
	
	@In(create = true)
	BaseStatusHome baseStatusHome;

	public void setProjectProjectId(Long id) {
		setId(id);
	}

	public Long getProjectProjectId() {
		return (Long) getId();
	}

	@Override
	protected Project createInstance() {
		Project project = new Project();
		BaseStatus status = new BaseStatus();
		
		project.setBaseStatus(status);
		
		return project;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		if (getInstance().getBaseStatus() == null)
			return false;
		return true;
	}

	public Project getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Task> getTasks() {
		return getInstance() == null ? null : new ArrayList<Task>(getInstance()
				.getTasks());
	}

}
