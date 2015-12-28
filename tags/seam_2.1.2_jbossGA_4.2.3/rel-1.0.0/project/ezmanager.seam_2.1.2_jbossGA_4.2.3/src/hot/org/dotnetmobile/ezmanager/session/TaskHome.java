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

@Name("taskHome")
public class TaskHome extends EntityHome<Task> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7691358383509061108L;
	
	@In(create = true)
	ProjectHome projectHome;
	@In(create = true)
	BaseStatusHome baseStatusHome;

	public void setTaskTaskId(Long id) {
		setId(id);
	}

	public Long getTaskTaskId() {
		return (Long) getId();
	}

	@Override
	protected Task createInstance() {
		Task task = new Task();
		BaseStatus status = new BaseStatus();
		
		task.setBaseStatus(status);
		return task;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Project project = projectHome.getDefinedInstance();
		if (project != null) {
			getInstance().setProject(project);
		}
		BaseStatus baseStatus = baseStatusHome.getDefinedInstance();
		if (baseStatus != null) {
			getInstance().setBaseStatus(baseStatus);
		}
	}

	public boolean isWired() {
		if (getInstance().getProject() == null)
			return false;
		if (getInstance().getBaseStatus() == null)
			return false;
		return true;
	}

	public Task getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Cost> getCosts() {
		return getInstance() == null ? null : new ArrayList<Cost>(getInstance()
				.getCosts());
	}

	public List<ExecutedTask> getExecutedTasks() {
		return getInstance() == null ? null : new ArrayList<ExecutedTask>(
				getInstance().getExecutedTasks());
	}

}
