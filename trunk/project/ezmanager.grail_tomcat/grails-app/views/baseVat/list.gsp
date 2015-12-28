<%@page import="ezmanager.*"%>
 
<html>
    <head>
    <g:javascript library="prototype"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'baseVat.label', default: 'Vat')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
               <g:form method="post" >
                   <div class="dialog">
                      <table>
                         <tr>
                            <td>Vat:</td>
                            <td><g:textField name="name" value="${name}"/></td>
                         </tr>
                         <tr>
                            <td>Percentage:</td>
                            <td><g:textField name="value" value="${value}"/></td>
                         </tr>
						 <tr>
						 	<td>Currency:</td>
						 	<td><g:select name="currencyId" value="${currencyId}" from="${BaseCurrency.listOrderByName()}" optionKey="currencyId" optionValue="name" noSelection="['':'All']"/></td>
						 </tr>
                      </table>
                   </div>
                   <div class="buttons">
                      <span class="button"><g:actionSubmit class="search" action="list" value="${message(code: 'default.button.search.label', default: 'Search')}" /></span>
                   </div>
               </g:form>

                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="vatId" title="${message(code: 'baseVat.vatId.label', default: 'Vat Id')}" width="8%" params="${params}"/>
                        
                            <g:sortableColumn property="name" title="${message(code: 'baseVat.name.label', default: 'Name')}" params="${params}"/>
                        
                            <g:sortableColumn property="value" title="${message(code: 'baseVat.value.label', default: 'Value')}" params="${params}"/>
                            
                            <th><g:message code="baseVat.currency.label" default="Currency" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${baseVatInstanceList}" status="i" var="baseVatInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${baseVatInstance.vatId}">${fieldValue(bean: baseVatInstance, field: "vatId")}</g:link></td>
                        
                            <td>${fieldValue(bean: baseVatInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: baseVatInstance, field: "value")}</td>
                        
                            <td>${fieldValue(bean: baseVatInstance, field: "currency.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${baseVatInstanceTotal}" params="${params}"/>
            </div>
        </div>
    </body>
</html>