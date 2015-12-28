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

package org.dotnetmobile.ezmanager.entity;

// Generated Jan 3, 2010 6:01:01 PM by Hibernate Tools 3.2.5.Beta

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name = "INVOICE", schema = "EZMANAGER")
public class Invoice implements java.io.Serializable {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8369757628211521596L;
	
	private long invoiceId;
	private ExecutedTask executedTask;
	private Long position;
	private Set<Customer> customers = new HashSet<Customer>(0);

	public Invoice() {
	}

	public Invoice(long invoiceId, ExecutedTask executedTask) {
		this.invoiceId = invoiceId;
		this.executedTask = executedTask;
	}

	public Invoice(long invoiceId, ExecutedTask executedTask, Long position,
			Set<Customer> customers) {
		this.invoiceId = invoiceId;
		this.executedTask = executedTask;
		this.position = position;
		this.customers = customers;
	}

	@Id
	@GeneratedValue(generator = "seqInvoice")
	@SequenceGenerator(name="seqInvoice", sequenceName = "SEQ_INVOICE")
	@Column(name = "INVOICE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXECUTED_TASK_ID", nullable = false)
	@NotNull
	public ExecutedTask getExecutedTask() {
		return this.executedTask;
	}

	public void setExecutedTask(ExecutedTask executedTask) {
		this.executedTask = executedTask;
	}

	@Column(name = "POSITION", precision = 10, scale = 0)
	public Long getPosition() {
		return this.position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "INVOICE_CUSTOMER", schema = "EZMANAGER", joinColumns = { @JoinColumn(name = "INVOICE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CUSTOMER_ID", nullable = false, updatable = false) })
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

}
