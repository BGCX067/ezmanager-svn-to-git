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

import ezmanager.BaseCurrency;
import ezmanager.BaseStatus;
import ezmanager.BaseVat;
import ezmanager.Cost;
import ezmanager.Project;
import ezmanager.Task;

import groovy.util.GroovyTestCase;

/**
* CostDomainTest is testing the domain class Cost.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class CostDomainTest extends GroovyTestCase {
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

		// Currency
		Long currencyIdRef = 1
		String nameCurrencyRef = "Yen"
	
		BaseCurrency currencyRef = new BaseCurrency(currencyId:currencyIdRef,name:nameCurrencyRef)

		// Vat
		Long vatIdRef = 1
		String nameVatRef = "Japan Vat"
		java.math.BigDecimal valueRef = 0
	
		BaseVat vatRef = new BaseVat(vatId:vatIdRef, name:nameVatRef, value:valueRef, currency:currencyRef)

		// Cost
		Long costIdRef = 1
		String nameCostRef = "test cost"
		Double amountProposedRef = 445.23
		java.math.BigDecimal totalHoursRef = 2
		
		Cost c = new Cost(costId:costIdRef, name:nameCostRef, amountProposed:amountProposedRef, totalHours:totalHoursRef, task:taskRef, vat:vatRef)
		
		assertNotNull c
		assertEquals costIdRef, c.costId
		assertEquals nameCostRef, c.name
		assertEquals amountProposedRef, c.amountProposed
		assertEquals totalHoursRef, c.totalHours

		assertNotNull c.task
		assertEquals taskIdRef, c.task.taskId
		assertEquals nameTaskRef, c.task.name
		assertEquals positionRef, c.task.position
		assertEquals startDateTaskRef, c.task.startDate
		assertEquals endDateTaskRef, c.task.endDate

		assertNotNull c.vat
		assertEquals vatIdRef, c.vat.vatId
		assertEquals nameVatRef, c.vat.name
		assertEquals valueRef, c.vat.value
		
		assertNotNull c.task.project
		assertEquals projectIdRef, c.task.project.projectId
		assertEquals nameProjectRef, c.task.project.name
		assertEquals startDateProjectRef, c.task.project.startDate
		assertEquals endDateProjectRef, c.task.project.endDate
		
		assertNotNull c.task.project.status
		assertEquals statusIdRef, c.task.project.status.statusId
		assertEquals nameStatusRef, c.task.project.status.name

		assertNotNull c.task.status
		assertEquals statusIdRef, c.task.status.statusId
		assertEquals nameStatusRef, c.task.status.name
		
	}

}
