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

class BaseCurrencyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [baseCurrencyInstanceList: BaseCurrency.list(params), baseCurrencyInstanceTotal: BaseCurrency.count()]
    }

    def create = {
        def baseCurrencyInstance = new BaseCurrency()
        baseCurrencyInstance.properties = params
        return [baseCurrencyInstance: baseCurrencyInstance]
    }

    def save = {
        def baseCurrencyInstance = new BaseCurrency(params)
        if (baseCurrencyInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), baseCurrencyInstance.currencyId])}"
            redirect(action: "show", id: baseCurrencyInstance.currencyId)
        }
        else {
            render(view: "create", model: [baseCurrencyInstance: baseCurrencyInstance])
        }
    }

    def show = {
        def baseCurrencyInstance = BaseCurrency.get(params.id)
        if (!baseCurrencyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), params.id])}"
            redirect(action: "list")
        }
        else {
            [baseCurrencyInstance: baseCurrencyInstance]
        }
    }

    def edit = {
        def baseCurrencyInstance = BaseCurrency.get(params.id)
        if (!baseCurrencyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [baseCurrencyInstance: baseCurrencyInstance]
        }
    }

    def update = {
        def baseCurrencyInstance = BaseCurrency.get(params.id)
        if (baseCurrencyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (baseCurrencyInstance.version > version) {
                    
                    baseCurrencyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'baseCurrency.label', default: 'BaseCurrency')] as Object[], "Another user has updated this BaseCurrency while you were editing")
                    render(view: "edit", model: [baseCurrencyInstance: baseCurrencyInstance])
                    return
                }
            }
            baseCurrencyInstance.properties = params
            if (!baseCurrencyInstance.hasErrors() && baseCurrencyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), baseCurrencyInstance.currencyId])}"
                redirect(action: "show", id: baseCurrencyInstance.currencyId)
            }
            else {
                render(view: "edit", model: [baseCurrencyInstance: baseCurrencyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def baseCurrencyInstance = BaseCurrency.get(params.id)
        if (baseCurrencyInstance) {
            try {
                baseCurrencyInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'baseCurrency.label', default: 'BaseCurrency'), params.id])}"
            redirect(action: "list")
        }
    }
}
