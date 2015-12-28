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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dotnetmobile.ezmanager.entity.Project;
import org.dotnetmobile.ezmanager.entity.Task;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityController;
import org.jboss.seam.log.Log;

@Name("projectReport")
public class ProjectReport extends EntityController {
	
	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7148623681878111403L;

	private static final String JPQL =
		"SELECT DISTINCT p FROM Project p " +
		"JOIN FETCH p.tasks " +
		"WHERE p.projectId = #{projectReport.projectId} ";


	@RequestParameter private Long projectId;
	@Out private Project project;

	// NOTE: using @Create would avoid need to use page action
	//@Create
	public void load() {
		project = (Project) createQuery(JPQL).getSingleResult();
	}
	
	public Long getProjectId() {
		return projectId;
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.addAll(project.getTasks());
		
		Collections.sort(tasks, new TaskComparatorBystartDate());
		
		return tasks;
	}
}
