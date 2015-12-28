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
import ezmanager.ExecutedTask;
import ezmanager.Invoice;
import ezmanager.Project;
import ezmanager.Task;
import ezmanager.UserIdentity;
import groovy.util.GroovyTestCase;

/**
* InvoiceDomainTest is testing the domain class Invoice.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class InvoiceDomainTest extends GroovyTestCase {
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
		Long positionTaskRef = 1
		Date startDateTaskRef = new Date()
		Date endDateTaskRef = new Date()
	
		Task taskRef = new Task(taskId:taskIdRef, name:nameTaskRef, position:positionTaskRef, startDate:startDateTaskRef, endDate:endDateTaskRef, project:projectRef, status:statusRef)

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
	
		ExecutedTask etRef = new ExecutedTask(executedTaskId:executedTaskIdRef, totalHours:totalHoursRef, amount:amountRef, startDate:startDateExecutedTaskRef, endDate:endDateExecutedTaskRef, task:taskRef, executedByUser:userRef)

		// Invoice
		Long invoiceIdRef = 1
		Long positionInvoiceRef = 1
	
		Invoice i = new Invoice(invoiceId:invoiceIdRef, position:positionInvoiceRef, executedTask:etRef)
		
		assertNotNull i
		assertEquals invoiceIdRef, i.invoiceId
		assertEquals positionInvoiceRef, i.position
		
		assertNotNull i.executedTask
		assertEquals executedTaskIdRef, i.executedTask.executedTaskId
		assertEquals totalHoursRef, i.executedTask.totalHours
		assertEquals amountRef, i.executedTask.amount
		assertEquals startDateExecutedTaskRef, i.executedTask.startDate
		assertEquals endDateExecutedTaskRef, i.executedTask.endDate
		
		assertNotNull i.executedTask.task
		assertEquals taskIdRef, i.executedTask.task.taskId
		assertEquals nameTaskRef, i.executedTask.task.name
		assertEquals positionTaskRef, i.executedTask.task.position
		assertEquals startDateTaskRef, i.executedTask.task.startDate
		assertEquals endDateTaskRef, i.executedTask.task.endDate

		assertNotNull i.executedTask.task.project
		assertEquals projectIdRef, i.executedTask.task.project.projectId
		assertEquals nameProjectRef, i.executedTask.task.project.name
		assertEquals startDateProjectRef, i.executedTask.task.project.startDate
		assertEquals endDateProjectRef, i.executedTask.task.project.endDate
		
		assertNotNull i.executedTask.task.project.status
		assertEquals statusIdRef, i.executedTask.task.project.status.statusId
		assertEquals nameStatusRef, i.executedTask.task.project.status.name

		assertNotNull i.executedTask.task.status
		assertEquals statusIdRef, i.executedTask.task.status.statusId
		assertEquals nameStatusRef, i.executedTask.task.status.name

		assertNotNull i.executedTask.executedByUser
		assertEquals userIdentityIdRef, i.executedTask.executedByUser.userIdentityId
		assertEquals nameUserRef, i.executedTask.executedByUser.name
		assertEquals passwordRef, i.executedTask.executedByUser.password
		assertEquals isActiveUserRef, i.executedTask.executedByUser.isActive

	}

}
