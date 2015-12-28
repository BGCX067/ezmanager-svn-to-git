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
 
import java.util.Date;

import ezmanager.BaseStatus;
import ezmanager.ExecutedTask;
import ezmanager.Project;
import ezmanager.Task;
import ezmanager.UserIdentity;
import groovy.util.GroovyTestCase;

/**
* ExecutedTaskDomainTest is testing the domain class ExecutedTask.
* 
* Test category: Unit Test
*
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class ExecutedTaskDomainTest extends GroovyTestCase {
	/**
	 *  Testing the constructor
	 */
	void testConstructor() {
		// Status
		Long statusIdRef = 1
		String nameStatusRef = "opened"
	
		BaseStatus statusRef = new BaseStatus(statusId:statusIdRef,name:nameStatusRef)

		// Project
		Long projectIdRef = 1
		String nameProjectRef = "test project"
		Date startDateProjectRef = new Date()
		Date endDateProjectRef = new Date()
	
		Project projectRef = new Project(projectId:projectIdRef,name:nameProjectRef,startDate:startDateProjectRef,endDate:endDateProjectRef,status:statusRef)

		
		// Task
		Long taskIdRef = 1
		String nameTaskRef = "test task"
		Long positionRef = 1
		Date startDateTaskRef = new Date()
		Date endDateTaskRef = new Date()
	
		Task taskRef = new Task(taskId:taskIdRef, name:nameTaskRef, position:positionRef, startDate:startDateTaskRef, endDate:endDateTaskRef, project:projectRef, status:statusRef)

		// User Identity
		Long userIdentityIdRef = 1
		String nameUserRef = "user"
		String passwordRef = "password"
		String isActiveUserRef = 1
		UserIdentity userRef = new UserIdentity(userIdentityId:userIdentityIdRef, name:nameUserRef, password:passwordRef, isActive:isActiveUserRef)


		// Executed Task
		Long executedTaskIdRef = 1
		java.math.BigDecimal totalHoursRef = 250
		Double amountRef = 2000
		Date startDateExecutedTaskRef = new Date()
		Date endDateExecutedTaskRef = new Date()
	
		ExecutedTask et = new ExecutedTask(executedTaskId:executedTaskIdRef, totalHours:totalHoursRef, amount:amountRef, startDate:startDateExecutedTaskRef, endDate:endDateExecutedTaskRef, task:taskRef, executedByUser:userRef)
		
		assertNotNull et
		assertEquals executedTaskIdRef, et.executedTaskId
		assertEquals totalHoursRef, et.totalHours
		assertEquals amountRef, et.amount
		assertEquals startDateExecutedTaskRef, et.startDate
		assertEquals endDateExecutedTaskRef, et.endDate
		
		assertNotNull et.task
		assertEquals taskIdRef, et.task.taskId
		assertEquals nameTaskRef, et.task.name
		assertEquals positionRef, et.task.position
		assertEquals startDateTaskRef, et.task.startDate
		assertEquals endDateTaskRef, et.task.endDate

		assertNotNull et.task.project
		assertEquals projectIdRef, et.task.project.projectId
		assertEquals nameProjectRef, et.task.project.name
		assertEquals startDateProjectRef, et.task.project.startDate
		assertEquals endDateProjectRef, et.task.project.endDate
		
		assertNotNull et.task.project.status
		assertEquals statusIdRef, et.task.project.status.statusId
		assertEquals nameStatusRef, et.task.project.status.name

		assertNotNull et.task.status
		assertEquals statusIdRef, et.task.status.statusId
		assertEquals nameStatusRef, et.task.status.name

		assertNotNull et.executedByUser
		assertEquals userIdentityIdRef, et.executedByUser.userIdentityId
		assertEquals nameUserRef, et.executedByUser.name
		assertEquals passwordRef, et.executedByUser.password
		assertEquals isActiveUserRef, et.executedByUser.isActive

	}

}
