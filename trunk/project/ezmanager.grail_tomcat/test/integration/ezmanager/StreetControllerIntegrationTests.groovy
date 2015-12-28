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

import ezmanager.City;
import ezmanager.Country;
import ezmanager.State;
import ezmanager.Street;
import ezmanager.StreetSearchCommand;
import ezmanager.domain.*


class StreetControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		Street.withTransaction() { street ->
			
			def cFrance = new Country(name:"France")
			def sHautRhin = new State(name:"Haut-Rhin", country:cFrance)
			def city = new City(name:"MulLLLhouse", state:sHautRhin)
			def sLiberte = new Street(name:"LiberTTTe", city:city)
			
			cFrance.save()
			sHautRhin.save()
			city.save()
			sLiberte.save()
			
			def filter = new StreetSearchCommand(name:"LiberTTTe", cityId:city.cityId)
			
			def list = controller.search(filter)
			assertEquals 1, list.size()

			street.setRollbackOnly()
		}
	}
}
