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
import groovy.util.GroovyTestCase;

/**
* ProjectDomainTest is testing the domain class Project.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class ProjectDomainTest extends GroovyTestCase {
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
	
		Project project = new Project(projectId:projectIdRef,name:nameProjectRef,startDate:startDateProjectRef,endDate:endDateProjectRef,status:statusRef)

		assertNotNull project
		assertEquals projectIdRef, project.projectId
		assertEquals nameProjectRef, project.name
		assertEquals startDateProjectRef, project.startDate
		assertEquals endDateProjectRef, project.endDate
		
		assertNotNull project.status
		assertEquals statusIdRef, project.status.statusId
		assertEquals nameStatusRef, project.status.name

	}
}
