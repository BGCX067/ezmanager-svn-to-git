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
import ezmanager.Address;
import ezmanager.AddressSearchCommand;
import ezmanager.City;
import ezmanager.Country;
import ezmanager.State;
import ezmanager.Street;
import ezmanager.domain.*

class AddressControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		Address.withTransaction() { address ->
			// Country
			String nameCountry = "France"
			Country country = new Country(name:nameCountry)
			country.save()


			// State
			String nameState = "Haut Rhin"
			State state = new State(name:nameState,country:country)
			state.save()


			// City
			String nameCity = "Mulhouse"
			City city = new City(name:nameCity, state:state)
			city.save()


			// Street
			String nameStreetColmar = "avenue de colmar"
			String remarkStreet = "forte circulation"

			Street streetColmar = new Street(name:nameStreetColmar, remark:remarkStreet, city:city)
			streetColmar.save()
			
			String nameStreetStrasbourg = "faubourg de strasbourg"

			Street streetStrasbourg = new Street(name:nameStreetStrasbourg, remark:remarkStreet, city:city)
			streetStrasbourg.save()

			// Address 1
			Integer streetNumber1 = 3
			String remarkAddress1 = "remark"
			String zip1 = "68100"
			String bActive = "1"

			def address1 = new Address(streetNumber:streetNumber1, street:streetColmar, zip:zip1, remark:remarkAddress1, bActive:bActive)
			address1.save()

			// Address 2
			Integer streetNumber2 = 126
			String remarkAddress2 = "remark2"
			String zip2 = "68540"

			def address2 = new Address(streetNumber:streetNumber2, street:streetStrasbourg, zip:zip2, remark:remarkAddress2, bActive:bActive)
			address2.save()

			def cmd = new AddressSearchCommand(streetNumber:126, streetName:"stras", zip:"", cityName:"Mu")
			def list = controller.search(cmd)
			assertEquals 1, list.size()

			address.setRollbackOnly()
		}
	}
}
