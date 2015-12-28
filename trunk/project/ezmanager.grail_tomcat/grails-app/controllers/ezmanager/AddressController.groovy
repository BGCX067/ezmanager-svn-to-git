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

class AddressController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {AddressSearchCommand cmd ->
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def addressList

		String paramStreetName
		if (cmd.streetName) {
			paramStreetName = "%" + cmd.streetName.toLowerCase() + "%"
		} else {
			paramStreetName = "%"
		}

		Integer paramStreetNumber
		if (cmd.streetNumber) {
			paramStreetNumber = cmd.streetNumber
		} else {
			paramStreetNumber = 0
		}

		String paramZip
		if (cmd.zip) {
			paramZip = "%" + cmd.zip.toLowerCase() + "%"
		} else {
			paramZip = "%"
		}

		String paramCityName
		if (cmd.cityName) {
			paramCityName = "%" + cmd.cityName.toLowerCase() + "%"
		} else {
			paramCityName = "%"
		}

		String query = "from Address as a "
		query = query + "where a.street.name like :streetName "
		query = query + "and a.streetNumber >= :streetNumber "
		query = query + "and a.zip like :zip "
		query = query + "and lower(a.street.city.name) like :cityName "

		addressList = Address.findAll(query,[streetName: paramStreetName, streetNumber: paramStreetNumber, zip: paramZip, cityName: paramCityName])

		return addressList
	}

	def list = { AddressSearchCommand cmd ->

		def addressList = search(cmd)

		[streetNumber: cmd.streetNumber, streetName: cmd.streetName, zip: cmd.zip, cityName: cmd.cityName, addressInstanceList: addressList, addressInstanceTotal: addressList.count()]
	}

	def create = {
		def addressInstance = new Address()
		addressInstance.properties = params
		return [addressInstance: addressInstance]
	}

	def save = {
		def addressInstance = new Address(params)
		if (addressInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'address.label', default: 'Address'), addressInstance.addressId])}"
			redirect(action: "show", id: addressInstance.addressId)
		}
		else {
			render(view: "create", model: [addressInstance: addressInstance])
		}
	}

	def show = {
		def addressInstance = Address.get(params.id)
		if (!addressInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'address.label', default: 'Address'), params.id])}"
			redirect(action: "list")
		}
		else {
			[addressInstance: addressInstance]
		}
	}

	def edit = {
		def addressInstance = Address.get(params.id)
		if (!addressInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'address.label', default: 'Address'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [addressInstance: addressInstance]
		}
	}

	def update = {
		def addressInstance = Address.get(params.id)
		if (addressInstance) {
			addressInstance.properties = params
			if (!addressInstance.hasErrors() && addressInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'address.label', default: 'Address'), addressInstance.addressId])}"
				redirect(action: "show", id: addressInstance.addressId)
			}
			else {
				render(view: "edit", model: [addressInstance: addressInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'address.label', default: 'Address'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def addressInstance = Address.get(params.id)
		if (addressInstance) {
			try {
				addressInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'address.label', default: 'Address'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'address.label', default: 'Address'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'address.label', default: 'Address'), params.id])}"
			redirect(action: "list")
		}
	}
}

class AddressSearchCommand {
	Integer streetNumber
	String streetName
	String zip
	String cityName
}