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

//package org.dotnetmobile.ezmanager.domain

/**
 * The Address entity.
 *
 * @author: Michel Petrovic, dotnetmobile@gmail.com    
 *
 *
 */
class Address {
    static mapping = {
         table 'ADDRESS'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         customerList column:'ADDRESS_ID',joinTable:'CUSTOMER_ADDRESS'
         id name:"addressId", generator:'sequence', columns:'ADDRESS_ID', params:[sequence:'SEQ_ADDRESS']
         street column:'STREET_ID'
		 columns {
			 streetNumber column:'STREET_NUMBER'
			 remark column:'REMARK'
			 zip column:'ZIP'
			 bActive column:'B_ACTIVE'
		 }
 
    }
	
    Long addressId
    Integer streetNumber
    String remark
    String zip
    String bActive
    // Relation
    Street street

    static hasMany = [ customerList : Customer]

    static constraints = {
        addressId(nullable:true, size: 0..10)
        streetNumber(nullable: false, size:0..5)
        remark(nullable:true, size: 0..500)
        zip(size: 1..20, blank: false)
        bActive(size: 1..1, blank: false)
        street()
        customerList()
    }
    String toString() {
        return "${addressId}" 
    }
}
