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

import ezmanager.Task;

class TaskController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def search = {TaskSearchCommand cmd ->
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def taskList

		def c = Task.createCriteria()
		taskList = c.list(params) {

			if (cmd.name) {
				ilike("name", "%" + cmd.name + "%")
			}
			if (cmd.startDate || cmd.endDate || cmd.positionMin || cmd.positionMax) {
				and {
					if (cmd.startDate) {
						ge("startDate", cmd.startDate)
					}

					if (cmd.endDate) {
						le("endDate", cmd.endDate)
					}

					if (cmd.positionMin) {
						ge("position", cmd.positionMin)
					}

					if (cmd.positionMax) {
						le("position", cmd.positionMax)
					}
				}
			}
		}

		return taskList
	}

	def list = { TaskSearchCommand cmd ->

		def taskList  = search(cmd)

		[name: cmd.name, startDate: cmd.startDate, endDate: cmd.endDate, positionMin: cmd.positionMin, positionMax:cmd.positionMax, taskInstanceList: taskList, taskInstanceTotal: taskList.totalCount]
	}

	def create = {
		def taskInstance = new Task()
		taskInstance.properties = params
		return [taskInstance: taskInstance]
	}

	def save = {
		def taskInstance = new Task(params)
		if (taskInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.taskId])}"
			redirect(action: "show", id: taskInstance.taskId)
		}
		else {
			render(view: "create", model: [taskInstance: taskInstance])
		}
	}

	def show = {
		def taskInstance = Task.get(params.id)
		if (!taskInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])}"
			redirect(action: "list")
		}
		else {
			[taskInstance: taskInstance]
		}
	}

	def edit = {
		def taskInstance = Task.get(params.id)
		if (!taskInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [taskInstance: taskInstance]
		}
	}

	def update = {
		def taskInstance = Task.get(params.id)
		if (taskInstance) {
			taskInstance.properties = params
			if (!taskInstance.hasErrors() && taskInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.taskId])}"
				redirect(action: "show", id: taskInstance.taskId)
			}
			else {
				render(view: "edit", model: [taskInstance: taskInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def taskInstance = Task.get(params.id)
		if (taskInstance) {
			try {
				taskInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'task.label', default: 'Task'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'task.label', default: 'Task'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])}"
			redirect(action: "list")
		}
	}
}

class TaskSearchCommand {
	String name
	Date startDate
	Date endDate
	Long positionMin
	Long positionMax
}