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
package ezmanager

import grails.test.*

import ezmanager.BaseStatus;
import ezmanager.ExecutedTask;
import ezmanager.Invoice;
import ezmanager.Project;
import ezmanager.Task;
import ezmanager.UserIdentity;
import ezmanager.domain.*

class InvoiceControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		Invoice.withTransaction() { invoice ->
			// Status
			BaseStatus statusRef = new BaseStatus(name:"OOopened")
			statusRef.save()

			// Project
			Date startDateProjectRef = new Date()
			Date endDateProjectRef = new Date() + 1

			Project projectRef = new Project(name:"TTtest project",startDate:startDateProjectRef,endDate:endDateProjectRef,status:statusRef)
			projectRef.save()

			// Task
			Date startDateTaskRef = new Date() + 2
			Date endDateTaskRef = new Date() + 3

			Task taskRef = new Task(name:"TTtest task", position:1, startDate:startDateTaskRef, endDate:endDateTaskRef, project:projectRef, status:statusRef)
			taskRef.save()

			// User Identity
			UserIdentity userRef = new UserIdentity(name:"UUuser", password:"PPpassword", isActive:1)
			userRef.save()


			// Executed Task
			Date startDateExecutedTaskRef = new Date() + 10
			Date endDateExecutedTaskRef = new Date() + 20

			ExecutedTask etRef = new ExecutedTask(totalHours:251, amount:2001, startDate:startDateExecutedTaskRef, endDate:endDateExecutedTaskRef, task:taskRef, executedByUser:userRef)
			etRef.save

			// Invoice

			Invoice i1 = new Invoice(position:1, executedTask:etRef)
			i1.save()
			Invoice i2 = new Invoice(position:2, executedTask:etRef)
			i2.save()

			// @TODO
			// FINISH IMPLEMENTATION

			invoice.setRollbackOnly()
		}
	}
}
