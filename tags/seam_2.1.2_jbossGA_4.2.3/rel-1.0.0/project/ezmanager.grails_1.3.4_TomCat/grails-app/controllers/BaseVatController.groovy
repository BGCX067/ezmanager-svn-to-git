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

class BaseVatController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [baseVatInstanceList: BaseVat.list(params), baseVatInstanceTotal: BaseVat.count()]
    }

    def create = {
        def baseVatInstance = new BaseVat()
        baseVatInstance.properties = params
        return [baseVatInstance: baseVatInstance]
    }

    def save = {
        def baseVatInstance = new BaseVat(params)
        if (baseVatInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), baseVatInstance.vatId])}"
            redirect(action: "show", id: baseVatInstance.vatId)
        }
        else {
            render(view: "create", model: [baseVatInstance: baseVatInstance])
        }
    }

    def show = {
        def baseVatInstance = BaseVat.get(params.id)
        if (!baseVatInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), params.id])}"
            redirect(action: "list")
        }
        else {
            [baseVatInstance: baseVatInstance]
        }
    }

    def edit = {
        def baseVatInstance = BaseVat.get(params.id)
        if (!baseVatInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [baseVatInstance: baseVatInstance]
        }
    }

    def update = {
        def baseVatInstance = BaseVat.get(params.id)
        if (baseVatInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (baseVatInstance.version > version) {
                    
                    baseVatInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'baseVat.label', default: 'BaseVat')] as Object[], "Another user has updated this BaseVat while you were editing")
                    render(view: "edit", model: [baseVatInstance: baseVatInstance])
                    return
                }
            }
            baseVatInstance.properties = params
            if (!baseVatInstance.hasErrors() && baseVatInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), baseVatInstance.vatId])}"
                redirect(action: "show", id: baseVatInstance.vatId)
            }
            else {
                render(view: "edit", model: [baseVatInstance: baseVatInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def baseVatInstance = BaseVat.get(params.id)
        if (baseVatInstance) {
            try {
                baseVatInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'baseVat.label', default: 'BaseVat'), params.id])}"
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
