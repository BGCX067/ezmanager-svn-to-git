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

package org.dotnetmobile.ezmanager.session;

import org.dotnetmobile.ezmanager.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("invoiceCustomerHome")
public class InvoiceCustomerHome extends EntityHome<InvoiceCustomer> {

	@In(create = true)
	CustomerHome customerHome;
	@In(create = true)
	InvoiceHome invoiceHome;

	public void setInvoiceCustomerId(InvoiceCustomerId id) {
		setId(id);
	}

	public InvoiceCustomerId getInvoiceCustomerId() {
		return (InvoiceCustomerId) getId();
	}

	public InvoiceCustomerHome() {
		setInvoiceCustomerId(new InvoiceCustomerId());
	}

	@Override
	public boolean isIdDefined() {
		if (getInvoiceCustomerId().getCustomerId() == 0)
			return false;
		if (getInvoiceCustomerId().getInvoiceId() == 0)
			return false;
		return true;
	}

	@Override
	protected InvoiceCustomer createInstance() {
		InvoiceCustomer invoiceCustomer = new InvoiceCustomer();
		invoiceCustomer.setId(new InvoiceCustomerId());
		return invoiceCustomer;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Customer customer = customerHome.getDefinedInstance();
		if (customer != null) {
			getInstance().setCustomer(customer);
		}
		Invoice invoice = invoiceHome.getDefinedInstance();
		if (invoice != null) {
			getInstance().setInvoice(invoice);
		}
	}

	public boolean isWired() {
		if (getInstance().getCustomer() == null)
			return false;
		if (getInstance().getInvoice() == null)
			return false;
		return true;
	}

	public InvoiceCustomer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
