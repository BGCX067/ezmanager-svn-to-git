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

@Name("customerAddressHome")
public class CustomerAddressHome extends EntityHome<CustomerAddress> {

	@In(create = true)
	AddressHome addressHome;
	@In(create = true)
	CustomerHome customerHome;

	public void setCustomerAddressId(CustomerAddressId id) {
		setId(id);
	}

	public CustomerAddressId getCustomerAddressId() {
		return (CustomerAddressId) getId();
	}

	public CustomerAddressHome() {
		setCustomerAddressId(new CustomerAddressId());
	}

	@Override
	public boolean isIdDefined() {
		if (getCustomerAddressId().getAddressId() == 0)
			return false;
		if (getCustomerAddressId().getCustomerId() == 0)
			return false;
		return true;
	}

	@Override
	protected CustomerAddress createInstance() {
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setId(new CustomerAddressId());
		return customerAddress;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Address address = addressHome.getDefinedInstance();
		if (address != null) {
			getInstance().setAddress(address);
		}
		Customer customer = customerHome.getDefinedInstance();
		if (customer != null) {
			getInstance().setCustomer(customer);
		}
	}

	public boolean isWired() {
		if (getInstance().getAddress() == null)
			return false;
		if (getInstance().getCustomer() == null)
			return false;
		return true;
	}

	public CustomerAddress getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
