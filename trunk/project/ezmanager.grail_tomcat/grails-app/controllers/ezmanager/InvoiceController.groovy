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
 
class InvoiceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [invoiceInstanceList: Invoice.list(params), invoiceInstanceTotal: Invoice.count()]
    }

    def create = {
        def invoiceInstance = new Invoice()
        invoiceInstance.properties = params
        return [invoiceInstance: invoiceInstance]
    }

    def save = {
        def invoiceInstance = new Invoice(params)
        if (invoiceInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'invoice.label', default: 'Invoice'), invoiceInstance.invoiceId])}"
            redirect(action: "show", id: invoiceInstance.invoiceId)
        }
        else {
            render(view: "create", model: [invoiceInstance: invoiceInstance])
        }
    }

    def show = {
        def invoiceInstance = Invoice.get(params.id)
        if (!invoiceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invoice.label', default: 'Invoice'), params.id])}"
            redirect(action: "list")
        }
        else {
            [invoiceInstance: invoiceInstance]
        }
    }

    def edit = {
        def invoiceInstance = Invoice.get(params.id)
        if (!invoiceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invoice.label', default: 'Invoice'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [invoiceInstance: invoiceInstance]
        }
    }

    def update = {
        def invoiceInstance = Invoice.get(params.id)
        if (invoiceInstance) {
            invoiceInstance.properties = params
            if (!invoiceInstance.hasErrors() && invoiceInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'invoice.label', default: 'Invoice'), invoiceInstance.invoiceId])}"
                redirect(action: "show", id: invoiceInstance.invoiceId)
            }
            else {
                render(view: "edit", model: [invoiceInstance: invoiceInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invoice.label', default: 'Invoice'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def invoiceInstance = Invoice.get(params.id)
        if (invoiceInstance) {
            try {
                invoiceInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'invoice.label', default: 'Invoice'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'invoice.label', default: 'Invoice'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'invoice.label', default: 'Invoice'), params.id])}"
            redirect(action: "list")
        }
    }
}
