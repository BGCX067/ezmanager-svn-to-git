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
 
import ezmanager.BaseStatus;
import ezmanager.Project;
import ezmanager.Task;
import groovy.util.GroovyTestCase;

/**
* TaskDomainTest is testing the domain class Task.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class TaskDomainTest extends GroovyTestCase {
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
	
		Task task = new Task(taskId:taskIdRef, name:nameTaskRef, position:positionRef, startDate:startDateTaskRef, endDate:endDateTaskRef, project:projectRef, status:statusRef)

		assertNotNull task
		assertEquals taskIdRef, task.taskId
		assertEquals nameTaskRef, task.name
		assertEquals positionRef, task.position
		assertEquals startDateTaskRef, task.startDate
		assertEquals endDateTaskRef, task.endDate

		assertNotNull task.project
		assertEquals projectIdRef, task.project.projectId
		assertEquals nameProjectRef, task.project.name
		assertEquals startDateProjectRef, task.project.startDate
		assertEquals endDateProjectRef, task.project.endDate
		
		assertNotNull task.status
		assertEquals statusIdRef, task.status.statusId
		assertEquals nameStatusRef, task.status.name


	}
}
