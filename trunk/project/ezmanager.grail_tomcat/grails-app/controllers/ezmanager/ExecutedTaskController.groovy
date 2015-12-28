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

import java.util.Date;

import ezmanager.ExecutedTask;

class ExecutedTaskController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {ExecutedTaskSearchCommand cmd ->
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def executedTaskList

		def c = ExecutedTask.createCriteria()
		executedTaskList = c.list(params) {

			if (cmd.totalHoursMin || cmd.totalHoursMax ||cmd.amountMin ||cmd.amountMax || cmd.startDate || cmd.endDate) {
				and {
					if (cmd.totalHoursMin) {
						ge("totalHours", cmd.totalHoursMin)
					}

					if (cmd.totalHoursMax) {
						le("totalHours", cmd.totalHoursMax)
					}

					if (cmd.amountMin) {
						ge("amount", cmd.amountMin)
					}

					if (cmd.amountMax) {
						le("amount", cmd.amountMax)
					}

					if (cmd.startDate) {
						ge("startDate", cmd.startDate)
					}

					if (cmd.endDate) {
						le("endDate", cmd.endDate)
					}
				}
			}
		}

		return executedTaskList
	}

	def list = { ExecutedTaskSearchCommand cmd ->

		def executedTaskList = search(cmd)

		[totalHoursMin: cmd.totalHoursMin, totalHoursMax: cmd.totalHoursMax, amountMin: cmd.amountMin, amountMax: cmd.amountMax, startDate: cmd.startDate, endDate: cmd.endDate, executedTaskInstanceList: executedTaskList, executedTaskInstanceTotal: executedTaskList.totalCount]
	}

	def create = {
		def executedTaskInstance = new ExecutedTask()
		executedTaskInstance.properties = params
		return [executedTaskInstance: executedTaskInstance]
	}

	def save = {
		def executedTaskInstance = new ExecutedTask(params)
		if (executedTaskInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), executedTaskInstance.executedTaskId])}"
			redirect(action: "show", id: executedTaskInstance.executedTaskId)
		}
		else {
			render(view: "create", model: [executedTaskInstance: executedTaskInstance])
		}
	}

	def show = {
		def executedTaskInstance = ExecutedTask.get(params.id)
		if (!executedTaskInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), params.id])}"
			redirect(action: "list")
		}
		else {
			[executedTaskInstance: executedTaskInstance]
		}
	}

	def edit = {
		def executedTaskInstance = ExecutedTask.get(params.id)
		if (!executedTaskInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [executedTaskInstance: executedTaskInstance]
		}
	}

	def update = {
		def executedTaskInstance = ExecutedTask.get(params.id)
		if (executedTaskInstance) {
			executedTaskInstance.properties = params
			if (!executedTaskInstance.hasErrors() && executedTaskInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), executedTaskInstance.executedTaskId])}"
				redirect(action: "show", id: executedTaskInstance.executedTaskId)
			}
			else {
				render(view: "edit", model: [executedTaskInstance: executedTaskInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def executedTaskInstance = ExecutedTask.get(params.id)
		if (executedTaskInstance) {
			try {
				executedTaskInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'executedTask.label', default: 'Executed Task'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'executedTask.label', default: 'ExecutedTask'), params.id])}"
			redirect(action: "list")
		}
	}
}

class ExecutedTaskSearchCommand {
	java.math.BigDecimal totalHoursMin
	java.math.BigDecimal totalHoursMax
	Double amountMin
	Double amountMax
	Date startDate
	Date endDate
}