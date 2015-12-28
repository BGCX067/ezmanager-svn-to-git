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

class CostController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [costInstanceList: Cost.list(params), costInstanceTotal: Cost.count()]
    }

    def create = {
        def costInstance = new Cost()
        costInstance.properties = params
        return [costInstance: costInstance]
    }

    def save = {
        def costInstance = new Cost(params)
        if (costInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'cost.label', default: 'Cost'), costInstance.costId])}"
            redirect(action: "show", id: costInstance.costId)
        }
        else {
            render(view: "create", model: [costInstance: costInstance])
        }
    }

    def show = {
        def costInstance = Cost.get(params.id)
        if (!costInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cost.label', default: 'Cost'), params.id])}"
            redirect(action: "list")
        }
        else {
            [costInstance: costInstance]
        }
    }

    def edit = {
        def costInstance = Cost.get(params.id)
        if (!costInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cost.label', default: 'Cost'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [costInstance: costInstance]
        }
    }

    def update = {
        def costInstance = Cost.get(params.id)
        if (costInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (costInstance.version > version) {
                    
                    costInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cost.label', default: 'Cost')] as Object[], "Another user has updated this Cost while you were editing")
                    render(view: "edit", model: [costInstance: costInstance])
                    return
                }
            }
            costInstance.properties = params
            if (!costInstance.hasErrors() && costInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cost.label', default: 'Cost'), costInstance.costId])}"
                redirect(action: "show", id: costInstance.costId)
            }
            else {
                render(view: "edit", model: [costInstance: costInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cost.label', default: 'Cost'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def costInstance = Cost.get(params.id)
        if (costInstance) {
            try {
                costInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cost.label', default: 'Cost'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cost.label', default: 'Cost'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cost.label', default: 'Cost'), params.id])}"
            redirect(action: "list")
        }
    }
}
