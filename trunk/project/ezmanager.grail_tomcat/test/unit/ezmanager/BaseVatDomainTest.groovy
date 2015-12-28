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
import ezmanager.BaseVat;
import groovy.util.GroovyTestCase;

/**
* BaseVatDomainTest is testing the domain class BaseVat.
*
* Test category: Unit Test
*
* @author: Michel Petrovic, dotnetmobile@gmail.com
*
*
*/
class BaseVatDomainTest extends GroovyTestCase {
	/**
	 *  Testing the constructor
	 */
	void testConstructor() {
		Long vatIdRef = 1
		Long currencyIdRef = 123
		
		String nameVatRef = "French VAT"
		String nameCurrencyRef = "EURO"
		
		java.math.BigDecimal valueVatRef = 19.6
		
		// Relation
		BaseCurrency currencyRef = new BaseCurrency(currencyId:currencyIdRef, name:nameCurrencyRef) 
		
		BaseVat bv = new BaseVat(vatId:vatIdRef, name:nameVatRef, value:valueVatRef, currency:currencyRef)
		
		assertNotNull bv
		assertEquals vatIdRef, bv.vatId
		assertEquals nameVatRef, bv.name
		assertEquals valueVatRef, bv.value
		
		assertNotNull bv.currency
		assertEquals currencyIdRef, bv.currency.currencyId
		assertEquals nameCurrencyRef, bv.currency.name
	}

}
