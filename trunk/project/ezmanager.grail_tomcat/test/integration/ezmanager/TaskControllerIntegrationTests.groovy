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
import ezmanager.Project;
import ezmanager.Task;
import ezmanager.TaskSearchCommand;
import ezmanager.domain.*
import java.util.Date;

class TaskControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		Task.withTransaction() { task ->
			// Status
			BaseStatus statusRef = new BaseStatus(name:"OOopened")
			statusRef.save()

			// Project
			Date startDateProjectRef = new Date()
			Date endDateProjectRef = new Date() + 1

			Project projectRef = new Project(name:"test project",startDate:startDateProjectRef,endDate:endDateProjectRef,status:statusRef)
			projectRef.save()

			// Task
			String nameTaskRef = "test task"
			Date startDateTaskRef = new Date() + 3
			Date endDateTaskRef = new Date() + 4

			Task t = new Task(name:"test AAtask", position:1, startDate:startDateTaskRef, endDate:endDateTaskRef, project:projectRef, status:statusRef)
			t.save()

			def filter = new TaskSearchCommand(name: "AA", startDate:startDateTaskRef-1, endDate: startDateTaskRef+10, positionMin: 0, positionMax: 5)

			def list = controller.search(filter)
			assertEquals 1, list.size()


			task.setRollbackOnly()
		}
	}
}

