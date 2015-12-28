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
import ezmanager.ProjectSearchCommand;
import ezmanager.domain.*

class ProjectControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		Project.withTransaction() { project ->

			Date today = new Date()

			def sTest = new BaseStatus(name:"test status")
			sTest.save()

			def p = new Project(name:"prrroject", startDate:today+1, endDate:today+2, status:sTest)
			p.save()

			def filter = new ProjectSearchCommand(name:"prrro", startDate:today-1, endDate:today+10, statusId:sTest.statusId)

			def list = controller.search(filter)
			assertEquals 1, list.size()

			project.setRollbackOnly()
		}
	}
}
