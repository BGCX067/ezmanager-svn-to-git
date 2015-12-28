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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * Address generated by hbm2java
 */
@Entity
@Table(name = "ADDRESS", schema = "EZMANAGER")
public class Address implements java.io.Serializable {

	@Logger private Log log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8966815976080350472L;
	
	private long addressId;
	private Street street;
	private Integer streetNumber;
	private String remark;
	private String zip;
	private char BActive;
	private Set<Customer> customers = new HashSet<Customer>(0);

	public Address() {
	}

	public Address(long addressId, Street street, String zip, char BActive) {
		this.addressId = addressId;
		this.street = street;
		this.zip = zip;
		this.BActive = BActive;
	}

	public Address(long addressId, Street street, Integer streetNumber,
			String remark, String zip, char BActive, Set<Customer> customers) {
		this.addressId = addressId;
		this.street = street;
		this.streetNumber = streetNumber;
		this.remark = remark;
		this.zip = zip;
		this.BActive = BActive;
		this.customers = customers;
	}

	@Id
	@GeneratedValue(generator = "seqAddress")
	@SequenceGenerator(name="seqAddress", sequenceName = "SEQ_ADDRESS")
	@Column(name = "ADDRESS_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STREET_ID", nullable = false)
	@NotNull
	public Street getStreet() {
		return this.street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	@Column(name = "STREET_NUMBER", precision = 5, scale = 0)
	public Integer getStreetNumber() {
		return this.streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Column(name = "REMARK", length = 500)
	@Length(max = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ZIP", nullable = false, length = 20)
	@NotNull
	@Length(max = 20)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "B_ACTIVE", nullable = false, length = 1)
	public char getBActive() {
		return this.BActive;
	}

	public void setBActive(char BActive) {
		this.BActive = BActive;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "addresses")
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

}
