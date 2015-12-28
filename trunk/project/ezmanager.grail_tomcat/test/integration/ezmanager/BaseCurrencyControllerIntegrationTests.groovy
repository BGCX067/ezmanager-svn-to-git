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
import ezmanager.BaseCurrency;
import ezmanager.domain.*

class BaseCurrencyControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		BaseCurrency.withTransaction() { currency ->
			def cEuro = new BaseCurrency(name:"TTeuro")
			def cYen = new BaseCurrency(name:"TTyen")
			def cUS = new BaseCurrency(name:"TTUS\$")
			def cCHF = new BaseCurrency(name:"CHF")

			cEuro.save()
			cYen.save()
			cUS.save()
			cCHF.save()

			controller.params.searchFilter = new String("TT")

			def list = controller.search()
			assertEquals 3, list.size()

			currency.setRollbackOnly()
		}
	}
}
