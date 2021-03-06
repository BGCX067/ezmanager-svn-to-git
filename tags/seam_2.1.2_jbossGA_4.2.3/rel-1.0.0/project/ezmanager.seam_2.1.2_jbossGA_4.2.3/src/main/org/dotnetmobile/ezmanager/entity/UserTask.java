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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;

/**
 * UserTask generated by hbm2java
 */
@Entity
@Table(name = "USER_TASK")
public class UserTask implements java.io.Serializable {

	private UserTaskId id;
	private Task task;
	private UserIdentity userIdentity;

	public UserTask() {
	}

	public UserTask(UserTaskId id, Task task, UserIdentity userIdentity) {
		this.id = id;
		this.task = task;
		this.userIdentity = userIdentity;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "taskTaskId", column = @Column(name = "TASK_TASK_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "userIdentityUserIdentityId", column = @Column(name = "USER_IDENTITY_USER_IDENTITY_ID", nullable = false, precision = 10, scale = 0))})
	@NotNull
	public UserTaskId getId() {
		return this.id;
	}

	public void setId(UserTaskId id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TASK_TASK_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_IDENTITY_USER_IDENTITY_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public UserIdentity getUserIdentity() {
		return this.userIdentity;
	}

	public void setUserIdentity(UserIdentity userIdentity) {
		this.userIdentity = userIdentity;
	}

}
