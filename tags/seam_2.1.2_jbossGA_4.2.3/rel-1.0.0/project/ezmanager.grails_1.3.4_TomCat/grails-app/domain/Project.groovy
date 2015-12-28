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

//package org.dotnetmobile.ezmanager.domain
/**
 * The Project entity.
 *
 * @author    
 *
 *
 */
class Project {
    static mapping = {
         table 'PROJECT'
         // version is set to false, because this isn't available by default for legacy databases
         version false
         customerList column:'PROJECT_ID',joinTable:'PROJECT_CUSTOMER'
         id name:"projectId", generator:'sequence', columns:'PROJECT_ID', params:[sequence:'SEQ_PROJECT']
         statusIdBaseStatus column:'STATUS_ID'
    }
    Long projectId
    String name
    Date startDate
    Date endDate
    // Relation
    BaseStatus statusIdBaseStatus

    static hasMany = [ customerList : Customer ]

    static belongsTo = [  Customer ]

    static constraints = {
        projectId(nullable: true, size: 0..10)
        name(size: 1..100, blank: false)
        startDate(nullable: true)
        endDate(nullable: true)
        statusIdBaseStatus()
        customerList()
    }
    String toString() {
        return "${projectId}" 
    }
}
