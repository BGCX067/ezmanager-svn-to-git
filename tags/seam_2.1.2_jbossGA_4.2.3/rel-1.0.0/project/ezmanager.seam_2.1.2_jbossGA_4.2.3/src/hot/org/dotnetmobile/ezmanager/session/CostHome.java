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
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.log.Log;

@Name("costHome")
public class CostHome extends EntityHome<Cost> {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2976924659450901938L;
	
	@In(create = true)
	TaskHome taskHome;
	@In(create = true)
	BaseVatHome baseVatHome;

	public void setCostCostId(Long id) {
		setId(id);
	}

	public Long getCostCostId() {
		return (Long) getId();
	}

	@Override
	protected Cost createInstance() {
		Cost cost = new Cost();
		BaseVat vat = new BaseVat();
		Task task = new Task();

		cost.setBaseVat(vat);
		cost.setTask(task);
		
		return cost;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		
		Task task = taskHome.getDefinedInstance();
		if (task != null) {
			getInstance().setTask(task);
		}
/*		
		BaseVat baseVat = baseVatHome.getDefinedInstance();
		if (baseVat != null) {
			getInstance().setBaseVat(baseVat);
		}
*/		
	}

	public boolean isWired() {
		
		if (getInstance().getTask() == null)
			return false;
			
		if (getInstance().getBaseVat() == null)
			return false;
		return true;
	}

	public Cost getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}