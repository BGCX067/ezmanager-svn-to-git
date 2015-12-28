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

class BaseVatController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {VatSearchCommand cmd ->
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def vatList

		def c = BaseVat.createCriteria()
		vatList = c.list(params) {

			if (cmd.name) {
				ilike("name", "%" + cmd.name + "%")
			}
			if (cmd.value || cmd.currencyId) {
				and {
					if (cmd.value) {
						ge("value", cmd.value)
					}

					if (cmd.currencyId) {
						eq("currency.currencyId", cmd.currencyId)
					}
				}
			}
		}

		return vatList
	}

	def list = { VatSearchCommand cmd ->

		def vatList = search(cmd)

		[name: cmd.name, value: cmd.value, currencyId: cmd.currencyId, baseVatInstanceList: vatList, baseVatInstanceTotal: vatList.totalCount]
	}

	def create = {
		def baseVatInstance = new BaseVat()
		baseVatInstance.properties = params
		return [baseVatInstance: baseVatInstance]
	}

	def save = {
		def baseVatInstance = new BaseVat(params)
		if (baseVatInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'baseVat.label', default: 'Vat'), baseVatInstance.vatId])}"
			redirect(action: "show", id: baseVatInstance.vatId)
		}
		else {
			render(view: "create", model: [baseVatInstance: baseVatInstance])
		}
	}

	def show = {
		def baseVatInstance = BaseVat.get(params.id)
		if (!baseVatInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'Vat'), params.id])}"
			redirect(action: "list")
		}
		else {
			[baseVatInstance: baseVatInstance]
		}
	}

	def edit = {
		def baseVatInstance = BaseVat.get(params.id)
		if (!baseVatInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'Vat'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [baseVatInstance: baseVatInstance]
		}
	}

	def update = {
		def baseVatInstance = BaseVat.get(params.id)
		if (baseVatInstance) {
			baseVatInstance.properties = params
			if (!baseVatInstance.hasErrors() && baseVatInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'baseVat.label', default: 'Vat'), baseVatInstance.vatId])}"
				redirect(action: "show", id: baseVatInstance.vatId)
			}
			else {
				render(view: "edit", model: [baseVatInstance: baseVatInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'Vat'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def baseVatInstance = BaseVat.get(params.id)
		if (baseVatInstance) {
			try {
				baseVatInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'baseVat.label', default: 'Vat'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), params.id])}"
			redirect(action: "list")
		}
	}
}

class VatSearchCommand {
	String name
	java.math.BigDecimal value
	Long currencyId
}