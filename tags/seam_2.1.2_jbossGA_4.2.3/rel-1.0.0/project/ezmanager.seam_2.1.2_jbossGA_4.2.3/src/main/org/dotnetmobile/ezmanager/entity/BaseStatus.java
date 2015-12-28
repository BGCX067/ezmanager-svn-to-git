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

// Generated Jan 3, 2010 6:01:01 PM by Hibernate Tools 3.2.5.Beta

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;



/**
 * BaseStatus generated by hbm2java
 */
@Entity
@Table(name = "BASE_STATUS", schema = "EZMANAGER")
public class BaseStatus implements java.io.Serializable {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8110903501703597541L;
	
	private long statusId;
	private String name;
	private Set<Project> projects = new HashSet<Project>(0);
	private Set<Task> tasks = new HashSet<Task>(0);

	public BaseStatus() {
	}

	public BaseStatus(long statusId, String name) {
		this.statusId = statusId;
		this.name = name;
	}

	public BaseStatus(long statusId, String name, Set<Project> projects,
			Set<Task> tasks) {
		this.statusId = statusId;
		this.name = name;
		this.projects = projects;
		this.tasks = tasks;
	}

	@Id
	@GeneratedValue(generator = "seqBaseActivity")
	@SequenceGenerator(name="seqBaseActivity", sequenceName = "SEQ_BASE_ACTIVITY")
	@Column(name = "STATUS_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	@Column(name = "NAME", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "baseStatus")
	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "baseStatus")
	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

}
