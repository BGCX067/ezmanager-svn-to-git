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
import ezmanager.BaseVat;
import ezmanager.VatSearchCommand;
import ezmanager.domain.*

class BaseVatControllerIntegrationTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSearch() {
		BaseVat.withTransaction() { vat ->
			def cEuro = new BaseCurrency(name:"euro")
			def cCHF = new BaseCurrency(name:"chf")
			
			cEuro.save()
			cCHF.save()
			
			def vFrench = new BaseVat(name:"TTFrench Vat", value:19.6, currency:cEuro)
			def vGerman = new BaseVat(name:"TTGerman Vat", value:17, currency:cEuro)
			def vCHF = new BaseVat(name:"BBCHF Vat", value:7.6, currency:cCHF)
			def vSpain = new BaseVat(name:"TTSpain Vat", value:12, currency:cEuro)
			
			vFrench.save()
			vGerman.save()
			vCHF.save()
			vSpain.save()
			
			def criteria = new VatSearchCommand()
			criteria.name = "TT"
			criteria.value = 16
			
			def list = controller.search(criteria)
			assertEquals 2, list.size()
			
			vat.setRollbackOnly()
		}

	}
}
