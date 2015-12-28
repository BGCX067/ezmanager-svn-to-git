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
 
import ezmanager.BaseCurrency;
import groovy.util.GroovyTestCase;

/**
* BaseCurrencyDomainTest is testing the domain class BaseCurrency.
*
* Test category: Unit Test
* 
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class BaseCurrencyDomainTest extends GroovyTestCase {
	/**
	*  Testing the constructor
	*/
	void testConstructor() {
		String nameRef = "EURO"
		Long idRef = 1
		
		BaseCurrency bc = new BaseCurrency(currencyId:idRef,name:nameRef)
		
		assertNotNull bc
		assertEquals idRef, bc.currencyId
		assertEquals nameRef, bc.name
	}

}
