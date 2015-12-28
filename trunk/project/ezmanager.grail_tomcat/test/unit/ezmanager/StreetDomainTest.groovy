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
 
import ezmanager.City;
import ezmanager.Country;
import ezmanager.State;
import ezmanager.Street;
import groovy.util.GroovyTestCase;

/**
* StreetDomainTest is testing the domain class Street.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class StreetDomainTest extends GroovyTestCase {
	/**
	 *  Testing the constructor
	 */
	void testConstructor() {
		// Country
		Long countryIdRef = 1
		String nameCountryRef = "France"
		Country countryRef = new Country(countryId:countryIdRef, name:nameCountryRef)

		// State
		Long stateIdRef = 1
		String nameStateRef = "Haut Rhin"
		State stateRef = new State(stateId:stateIdRef,name:nameStateRef,country:countryRef)


		// City
		Long cityIdRef = 1
		String nameCityRef = "Mulhouse"
		City cityRef = new City(cityId:cityIdRef,name:nameCityRef, state:stateRef)


		// Street
		Long streetIdRef = 1
		String nameStreetRef = "avenue de colmar"
		String remarkStreetRef = "forte circulation"

		Street street = new Street(streetId:streetIdRef, name:nameStreetRef, remark:remarkStreetRef, city:cityRef)
		
		assertNotNull street
		assertEquals streetIdRef, street.streetId
		assertEquals nameStreetRef, street.name
		assertEquals remarkStreetRef, street.remark
		
		assertNotNull street.city
		assertEquals cityIdRef, street.city.cityId
		assertEquals nameCityRef, street.city.name
		
		assertNotNull street.city.state
		assertEquals stateIdRef, street.city.state.stateId
		assertEquals nameStateRef, street.city.state.name
		
		assertNotNull street.city.state.country
		assertEquals countryIdRef, street.city.state.country.countryId
		assertEquals nameCountryRef, street.city.state.country.name


	}
}
