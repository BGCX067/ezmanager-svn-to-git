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

class CustomerController {
	String searchFilter

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {
		def customerList

		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		searchFilter = params.searchFilter

		def c = Customer.createCriteria()

		customerList = c.list(params) {
			if (searchFilter) {
				ilike("name", "%" + params.searchFilter + "%")
			}
		}

		return customerList
	}

	def list = {
		def customerList = search()

		[searchFilter: searchFilter, customerInstanceList: customerList, customerInstanceTotal: customerList.totalCount]
	}

	def create = {
		def customerInstance = new Customer()
		customerInstance.properties = params
		return [customerInstance: customerInstance]
	}

	def save = {
		def customerInstance = new Customer(params)
		if (customerInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'customer.label', default: 'Customer'), customerInstance.customerId])}"
			redirect(action: "show", id: customerInstance.customerId)
		}
		else {
			render(view: "create", model: [customerInstance: customerInstance])
		}
	}

	def show = {
		def customerInstance = Customer.get(params.id)
		if (!customerInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])}"
			redirect(action: "list")
		}
		else {
			[customerInstance: customerInstance]
		}
	}

	def edit = {
		def customerInstance = Customer.get(params.id)
		if (!customerInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [customerInstance: customerInstance]
		}
	}

	def update = {
		def customerInstance = Customer.get(params.id)
		if (customerInstance) {
			customerInstance.properties = params
			if (!customerInstance.hasErrors() && customerInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'customer.label', default: 'Customer'), customerInstance.customerId])}"
				redirect(action: "show", id: customerInstance.customerId)
			}
			else {
				render(view: "edit", model: [customerInstance: customerInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def customerInstance = Customer.get(params.id)
		if (customerInstance) {
			try {
				customerInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])}"
			redirect(action: "list")
		}
	}
}
