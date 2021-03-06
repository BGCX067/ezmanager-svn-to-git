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
import ezmanager.Country;
import ezmanager.State;
import ezmanager.StateSearchCommand;
import ezmanager.domain.*

class StateControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		State.withTransaction() { state ->
			Country cFrance = new Country(name:"France")
			cFrance.save()
			
			def s1 = new State(name:"Ile de FFFrance", country:cFrance)
			def s2 = new State(name:"GironFFFde", country:cFrance)

			s1.save()
			s2.save()

			def filter = new StateSearchCommand(name:"FFF", countryId:cFrance.countryId)

			def list = controller.search(filter)
			assertEquals 2, list.size()

			state.setRollbackOnly()
		}

	}
}
